package com.jubenwu.guanlixitong;

import com.jubenwu.guanlixitong.security.TokenSecurity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GuanlixitongApplicationTests {
    @Autowired
    private  TokenSecurity mTokenSecurity;
    @Test
    void contextLoads() {

        Integer u= 1;

        if (3>u+1){
            int y = u+1;
            System.out.println("U:"+u+",Y:"+y);
        }
    }

}
