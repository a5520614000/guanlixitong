package com.jubenwu.guanlixitong.security;

import com.jubenwu.guanlixitong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author glsite.com
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
@Component
public class TokenSecurity {

    @Autowired
    private UserService mUserService;

    public Integer tokenIsUseful(String token){
        Integer id = mUserService.selectIdByToken(token);
        if (id!=null&&id!=0){
            return id;
        }else {
            return 0;
        }

    }
}
