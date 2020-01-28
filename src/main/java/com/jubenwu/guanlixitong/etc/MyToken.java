package com.jubenwu.guanlixitong.etc;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author glsite.com
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class MyToken {

    public static String getToken(String accountId){
        String s = UUID.randomUUID().toString();
        String token = s+accountId;
        return token;
    }
}
