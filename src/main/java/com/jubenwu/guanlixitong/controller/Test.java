package com.jubenwu.guanlixitong.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author glsite.com
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
@RestController
public class Test {

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public Object doTest(@RequestBody String sto){
        System.out.println("sto:"+sto);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("data","ok");
//        System.out.println(""+sto);
        return map;
    }
}
