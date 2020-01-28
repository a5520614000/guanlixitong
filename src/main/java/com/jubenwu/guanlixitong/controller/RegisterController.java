package com.jubenwu.guanlixitong.controller;

import com.jubenwu.guanlixitong.dto.ResultDTO;
import com.jubenwu.guanlixitong.enums.ResultEnums;
import com.jubenwu.guanlixitong.model.User;
import com.jubenwu.guanlixitong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @author glsite.com
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
@Controller
public class RegisterController {

    @Autowired
    private UserService mUserService;

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object registerUser(@RequestBody User user) {
        String token = mUserService.registerUser(user);
        if (token.length() > 2) {
            ResultDTO<Object> resultDTO = new ResultDTO<>();
            resultDTO.setCode(ResultEnums.REGISTER_SUCCESS.getCode());
            resultDTO.setMessage(ResultEnums.REGISTER_SUCCESS.getMessage());
            HashMap<String, Object> map = new HashMap<>();
            map.put("token", token);
            return resultDTO;
        }else {
            ResultDTO<Object> resultDTO = new ResultDTO<>();
            resultDTO.setCode(ResultEnums.ACCOUNT_EXIST.getCode());
            resultDTO.setMessage(ResultEnums.ACCOUNT_EXIST.getMessage());
            resultDTO.setData("");

            return resultDTO;
        }
    }
}
