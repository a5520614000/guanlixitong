package com.jubenwu.guanlixitong.controller;

import com.jubenwu.guanlixitong.dto.ResultDTO;
import com.jubenwu.guanlixitong.enums.ResultEnums;
import com.jubenwu.guanlixitong.model.User;
import com.jubenwu.guanlixitong.service.UserService;
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
