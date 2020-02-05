package com.jubenwu.guanlixitong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author glsite.com
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
@Controller
public class Test {

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public Object doTest(){
//        System.out.println("sto:"+sto);
//        HashMap<Object, Object> map = new HashMap<>();
//        map.put("data","ok");
//        System.out.println(""+sto);
        return "temp";
    }
}
