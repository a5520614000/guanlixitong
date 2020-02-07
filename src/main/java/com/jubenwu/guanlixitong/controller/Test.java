package com.jubenwu.guanlixitong.controller;

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
public class Test {

    @RequestMapping(value = "/api/forms/delete1",method = RequestMethod.POST)
    public Object doTest(@RequestBody String sto){
        HashMap<Object, Object> map = new HashMap<>();
        map.put("data","ok");
        System.out.println(""+sto);
        return map;
    }
}
