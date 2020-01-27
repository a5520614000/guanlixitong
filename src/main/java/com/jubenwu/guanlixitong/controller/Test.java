package com.jubenwu.guanlixitong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author glsite.com
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
@Controller
public class Test {

    @ResponseBody
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Object doGet(){
        return "ok";
    }
}
