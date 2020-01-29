package com.jubenwu.guanlixitong.controller;

import com.alibaba.fastjson.JSON;
import com.jubenwu.guanlixitong.dto.*;
import com.jubenwu.guanlixitong.enums.ResultEnums;
import com.jubenwu.guanlixitong.model.Building;
import com.jubenwu.guanlixitong.model.BuildingForm;
import com.jubenwu.guanlixitong.security.TokenSecurity;
import com.jubenwu.guanlixitong.service.BuildingFormService;
import com.jubenwu.guanlixitong.service.BuildingService;
import com.jubenwu.guanlixitong.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author glsite.com
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */

@Controller
public class BuildingController {

    @Autowired
    private BuildingService mBuildingService;

    @Autowired
    private BuildingFormService mBuildingFormService;

    @Autowired
    private UserService mUserService;

    @Autowired
    private TokenSecurity mTokenSecurity;

    /**
     * 上报报表
     *
     * @param buildingFormDTO
     * @return
     */
    @Transactional
    @ResponseBody
    @RequestMapping(value = "/forms/publish", method = RequestMethod.POST)
    public Object addForm(@RequestBody AddBuildingFormDTO buildingFormDTO) {
        String token = buildingFormDTO.getToken();
        Integer userId = mUserService.selectIdByToken(token);
        if (userId> 0) {
            List<Integer> childIds = mBuildingService.insertBuildingList(buildingFormDTO,userId);
            Integer parentId = mBuildingFormService.insertBuildingFormDTO(buildingFormDTO, childIds, userId);
            mBuildingService.updateParentIdById(parentId,childIds);
            ResultDTO<Object> resultDTO = new ResultDTO<>();
            resultDTO.setCode(ResultEnums.ADD_FORM_SUCCESS.getCode());
            resultDTO.setMessage(ResultEnums.ADD_FORM_SUCCESS.getMessage());
            resultDTO.setData("");
            return resultDTO;
        } else {
            ResultDTO<Object> resultDTO = new ResultDTO<>();
            resultDTO.setCode(ResultEnums.ADD_FORM_FAILED.getCode());
            resultDTO.setMessage(ResultEnums.ADD_FORM_FAILED.getMessage());
            resultDTO.setData("");
            return resultDTO;
        }

    }

    /**
     * 查询主报表
     *
     * @param token
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/forms/query", method = RequestMethod.POST)
    public Object getMyForms(@RequestBody TokenDTO token) {
        String userToken = token.getToken();
        Integer userId = mUserService.selectIdByToken(userToken);
        System.out.println("userid:" + userId);
        if (userId > 0) {
            List<BuildingForm> buildingForms = mBuildingFormService.selectByUserId(userId);
            ResultDTO<Object> resultDTO = new ResultDTO<>();
            resultDTO.setCode(ResultEnums.QUERY_SUCCESS.getCode());
            resultDTO.setMessage(ResultEnums.QUERY_SUCCESS.getMessage());
            resultDTO.setData(buildingForms);
            return resultDTO;
        } else {
            ResultDTO<Object> resultDTO = new ResultDTO<>();
            resultDTO.setCode(ResultEnums.UNKOWN_ERROR.getCode());
            resultDTO.setMessage(ResultEnums.UNKOWN_ERROR.getMessage());
            resultDTO.setData("");
            return resultDTO;
        }
    }

    /**
     * 查询副报表
     *
     * @param fromDTO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/childforms/query", method = RequestMethod.POST)
    public Object getChildForms(@RequestBody ChildRequestFromDTO fromDTO) {
        Integer userId = mTokenSecurity.tokenIsUseful(fromDTO.getToken());
        System.out.println("userId:" + userId);
        if (userId > 0) {
            String[] split = StringUtils.split(fromDTO.getChildId(), ",");

            List<Building> buildingList = new ArrayList<>();
            for (String s : split) {
                System.out.println("s:" + s);
                Building building = mBuildingService.selectByPrimaryKey(Integer.parseInt(s));
                buildingList.add(building);
            }
            ResultListDTO<Building> objectResultDTO = new ResultListDTO<>();
            objectResultDTO.setData(buildingList);
            objectResultDTO.setCode(ResultEnums.QUERY_SUCCESS.getCode());
            objectResultDTO.setMessage(ResultEnums.QUERY_SUCCESS.getMessage());
            String s = JSON.toJSONString(objectResultDTO);
            return s;
        } else {
            return null;
        }


    }

    /**
     * 修改报表
     *
     * @param buildingFormDTO
     * @return
     */
    @Transactional
    @ResponseBody
    @RequestMapping(value = "/forms/update", method = RequestMethod.POST)
    public Object updateChildForms(@RequestBody UpdateBuildingFormDTO buildingFormDTO) {
        String token = buildingFormDTO.getToken();
        Integer parentId = buildingFormDTO.getId();
        Integer userId = mUserService.selectIdByToken(token);
        if (parentId> 0) {
            //更新副表内容
            List<Integer> childIds = mBuildingService.updateByPrimaryKey(buildingFormDTO, parentId,userId);
            mBuildingFormService.updateBuildingFormDTO(buildingFormDTO, childIds, parentId);
            ResultDTO<Object> resultDTO = new ResultDTO<>();
            resultDTO.setCode(ResultEnums.UPDATE_SUCCESS.getCode());
            resultDTO.setMessage(ResultEnums.UPDATE_SUCCESS.getMessage());
            resultDTO.setData("");
            return resultDTO;
        } else {
            ResultDTO<Object> resultDTO = new ResultDTO<>();
            resultDTO.setCode(ResultEnums.ADD_FORM_FAILED.getCode());
            resultDTO.setMessage(ResultEnums.ADD_FORM_FAILED.getMessage());
            resultDTO.setData("");
            return resultDTO;
        }
    }

    /**
     * 删除主报表
     *
     * @param deleteFormDTO
     * @return
     */
    @Transactional
    @ResponseBody
    @RequestMapping(value = "/forms/delete", method = RequestMethod.POST)
    public Object deleteForms(@RequestBody DeleteFormDTO deleteFormDTO) {
        Integer userId1 = mUserService.selectIdByToken(deleteFormDTO.getToken());
        Integer userId2 = mBuildingFormService.selectUserIdById(deleteFormDTO.getId());
        if (userId1.equals(userId2)){
            mBuildingFormService.deleteByPrimaryKey(deleteFormDTO.getId());
            ResultDTO<Object> resultDTO = new ResultDTO<>();
            resultDTO.setCode(ResultEnums.DELETE_SUCCESS.getCode());
            resultDTO.setMessage(ResultEnums.DELETE_SUCCESS.getMessage());
            resultDTO.setData("");
            return resultDTO;
        }else {
            ResultDTO<Object> resultDTO = new ResultDTO<>();
            resultDTO.setCode(ResultEnums.DELETE_FAILED.getCode());
            resultDTO.setMessage(ResultEnums.DELETE_FAILED.getMessage());
            resultDTO.setData("");
            return resultDTO;
        }

    }
    /**
     * 删除附报表
     *
     * @param deleteFormDTO
     * @return
     */
    @Transactional
    @ResponseBody
    @RequestMapping(value = "/childforms/delete", method = RequestMethod.POST)
    public Object deleteChildForms(@RequestBody DeleteFormDTO deleteFormDTO) {
        Integer userId1 = mUserService.selectIdByToken(deleteFormDTO.getToken());
        Integer userId2 = mBuildingService.selectUserIdById(deleteFormDTO.getId());
        if (userId1.equals(userId2)){
            mBuildingService.deleteByPrimaryKey(deleteFormDTO.getId());
            ResultDTO<Object> resultDTO = new ResultDTO<>();
            resultDTO.setCode(ResultEnums.DELETE_SUCCESS.getCode());
            resultDTO.setMessage(ResultEnums.DELETE_SUCCESS.getMessage());
            resultDTO.setData("");
            return resultDTO;
        }else {
            ResultDTO<Object> resultDTO = new ResultDTO<>();
            resultDTO.setCode(ResultEnums.DELETE_FAILED.getCode());
            resultDTO.setMessage(ResultEnums.DELETE_FAILED.getMessage());
            resultDTO.setData("");
            return resultDTO;
        }
    }

}
