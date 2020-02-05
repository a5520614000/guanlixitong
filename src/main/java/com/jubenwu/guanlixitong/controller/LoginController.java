package com.jubenwu.guanlixitong.controller;

import com.jubenwu.guanlixitong.dto.LoginRequestDTO;
import com.jubenwu.guanlixitong.dto.ResultDTO;
import com.jubenwu.guanlixitong.enums.ResultEnums;
import com.jubenwu.guanlixitong.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author glsite.com
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
@RestController
public class LoginController {

    @Autowired
    private UserService mUserService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestBody LoginRequestDTO loginRequestDTO) {
        String token = mUserService.login(loginRequestDTO);
        if (StringUtils.isNotBlank(token)) {
            if (!StringUtils.equals(token, "0")) {
                ResultDTO<Object> resultDTO = new ResultDTO<>();
                resultDTO.setCode(ResultEnums.LOGIN_SUCCESS.getCode());
                resultDTO.setMessage(ResultEnums.LOGIN_SUCCESS.getMessage());
                HashMap<String, Object> map = new HashMap<>();
                map.put("token", token);
                resultDTO.setData(map);
                return resultDTO;
            }
            ResultDTO<Object> resultDTO = new ResultDTO<>();
            resultDTO.setCode(ResultEnums.PASSWORD_ERROR.getCode());
            resultDTO.setMessage(ResultEnums.PASSWORD_ERROR.getMessage());
            return resultDTO;
        }
        ResultDTO<Object> resultDTO = new ResultDTO<>();
        resultDTO.setCode(ResultEnums.LOGIN_FAILED.getCode());
        resultDTO.setMessage(ResultEnums.LOGIN_FAILED.getMessage());
        return resultDTO;
    }
}
