package com.jubenwu.guanlixitong;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GuanlixitongApplicationTests {

    @Test
    void contextLoads() {
        String str = "5,50,150,";
        String remove = StringUtils.removeEnd(str, ",");
        System.out.println("str:"+remove);
    }

}
