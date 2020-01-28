package com.jubenwu.guanlixitong.controller;

import com.alibaba.fastjson.JSON;
import com.jubenwu.guanlixitong.dto.*;
import com.jubenwu.guanlixitong.enums.ResultEnums;
import com.jubenwu.guanlixitong.model.Building;
import com.jubenwu.guanlixitong.model.BuildingForm;
import com.jubenwu.guanlixitong.security.TokenSecurity;
import com.jubenwu.guanlixitong.service.BuildingFormService;
import com.jubenwu.guanlixitong.service.BuildingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private TokenSecurity mTokenSecurity;

    /**
     * 上报报表
     *
     * @param buildingFormDTO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/forms/publish", method = RequestMethod.POST)
    public Object addForm(@RequestBody AddBuildingFormDTO buildingFormDTO) {
        String token = buildingFormDTO.getToken();
        Integer parentId = mTokenSecurity.tokenIsUseful(token);
        if (token.length() > 2) {
            List<Integer> childIds = mBuildingService.insertBuildingList(buildingFormDTO, parentId);
            mBuildingFormService.insertBuildingFormDTO(buildingFormDTO, childIds, parentId);
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
        System.out.println("token:" + token);
        Integer userId = mTokenSecurity.tokenIsUseful(token.getToken());
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
     * @param fromDTO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/forms/update", method = RequestMethod.POST)
    public Object updateChildForms(@RequestBody ChildRequestFromDTO fromDTO) {

        return null;
    }

}
