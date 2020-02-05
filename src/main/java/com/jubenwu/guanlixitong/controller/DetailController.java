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
public class DetailController {

    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public Object doDetail(){
//        System.out.println("child:"+childId);

        return "detail";
    }
}
