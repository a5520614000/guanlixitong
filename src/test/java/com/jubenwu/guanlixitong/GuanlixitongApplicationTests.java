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
        Integer integer = mTokenSecurity.tokenIsUseful("f08ca066-71f1-459b-a576-355464ee92acceshi2");
        System.out.println(""+integer);
    }

}
