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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author glsite.com
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */

@RestController
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
    @RequestMapping(value = "/api/forms/publish", method = RequestMethod.POST)
    public Object addForm(@RequestBody AddBuildingFormDTO buildingFormDTO) {
        System.out.println("dto:"+buildingFormDTO);
        //获取token
        String token = buildingFormDTO.getToken();
        //查询token是否存在对应的user
        Integer userId = mUserService.selectIdByToken(token);
        //如果存在那么>0
        if (userId> 0) {
            //把子表插入数据库，并返回子表所在数据库的ID
            List<Integer> childIds = mBuildingService.insertBuildingList(buildingFormDTO,userId);
            //把主表插入数据库，并返回主表所在数据库的ID
            Integer parentId = mBuildingFormService.insertBuildingFormDTO(buildingFormDTO, childIds, userId);
            //更新子表的parentId
            mBuildingService.updateParentIdById(parentId,childIds);
            //返回成功信息
            return returnErrorMessage(ResultEnums.ADD_FORM_SUCCESS);
        } else {//如果不存在，返回错误信息
            return returnErrorMessage(ResultEnums.ADD_FORM_FAILED);
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
            return returnErrorMessage(ResultEnums.UNKOWN_ERROR);
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
//        System.out.println("查询附表userId:" + userId);
        Integer locker = 0;
        if (userId > 0) {
            String[] split = StringUtils.split(fromDTO.getChildId(), ",");
            List<Building> buildingList = new ArrayList<>();
            for (String s : split) {
//                System.out.println("s:" + s);
                Building building = mBuildingService.selectByPrimaryKey(Integer.parseInt(s));
                locker = mBuildingFormService.selectLockerById(building.getParentId());
//                System.out.println("查询附表："+building.toString()+"。主表的locker："+locker);
                buildingList.add(building);
            }
            ResultDetailDTO<Building> objectResultDTO = new ResultDetailDTO<>();
            objectResultDTO.setData(buildingList);
            objectResultDTO.setCode(ResultEnums.QUERY_SUCCESS.getCode());
            objectResultDTO.setLocker(locker);
            String s = JSON.toJSONString(objectResultDTO);
            return s;
        } else {
            return null;
        }


    }

    /**
     * 修改报表
     *
     * @param buildingFormDTO DTO
     * @return 成功或者失败信息
     */
    @Transactional
    @ResponseBody
    @RequestMapping(value = "/api/forms/update", method = RequestMethod.POST)
    public Object updateChildForms(@RequestBody UpdateBuildingFormDTO buildingFormDTO) {
        //获取token
        String token = buildingFormDTO.getToken();
        //获取用户ID
        Integer userId = mUserService.selectIdByToken(token);
        //如果用户ID存在
        if (userId> 0) {
            //更新副表内容
            Integer parentId = mBuildingService.updateByPrimaryKey(buildingFormDTO,userId);
            //更新主表内容
            mBuildingFormService.updateBuildingFormDTO(buildingFormDTO, parentId);
            //返回信息
            return returnErrorMessage(ResultEnums.UPDATE_SUCCESS);
        } else {
            //失败返回信息
            return returnErrorMessage(ResultEnums.UPDATE_FAILED);
        }
    }

    /**
     * 返回错误信息
     * @param updateFailed 错误信息代码
     * @return 错误信息JSON
     */
    private Object returnErrorMessage(ResultEnums updateFailed) {
        ResultDTO<Object> resultDTO = new ResultDTO<>();
        resultDTO.setCode(updateFailed.getCode());
        resultDTO.setMessage(updateFailed.getMessage());
        resultDTO.setData("");
        return resultDTO;
    }

    /**
     * 删除主报表
     *
     * @param deleteFormDTO
     * @return
     */
    @Transactional
    @ResponseBody
    @RequestMapping(value = "/api/forms/delete", method = RequestMethod.POST)
    public Object deleteForms(@RequestBody DeleteFormDTO deleteFormDTO) {
        //确认该问题归属
        Integer userId1 = mUserService.selectIdByToken(deleteFormDTO.getToken());
        Integer userId2 = mBuildingFormService.selectUserIdById(deleteFormDTO.getId());
        if (userId1.equals(userId2)){
            mBuildingFormService.deleteByPrimaryKey(deleteFormDTO.getId());
            return returnErrorMessage(ResultEnums.DELETE_SUCCESS);
        }else {
            return returnErrorMessage(ResultEnums.DELETE_FAILED);
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
    @RequestMapping(value = "/api/childForms/delete", method = RequestMethod.POST)
    public Object deleteChildForms(@RequestBody DeleteFormDTO deleteFormDTO) {
        //根据token，查询userId
        Integer userId1 = mUserService.selectIdByToken(deleteFormDTO.getToken());
        //根据附表ID，查询userId
        Integer userId2 = mBuildingService.selectUserIdById(deleteFormDTO.getId());
        //判断问题归属
        if (userId1.equals(userId2)){
            //是所属人，则删除(实则更新该问题userId为-2)
            mBuildingService.deleteByPrimaryKey(deleteFormDTO.getId());
            return returnErrorMessage(ResultEnums.DELETE_SUCCESS);
        }else {
            return returnErrorMessage(ResultEnums.DELETE_FAILED);
        }
    }

}
