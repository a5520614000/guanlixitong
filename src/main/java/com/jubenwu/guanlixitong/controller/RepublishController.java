package com.jubenwu.guanlixitong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author glsite.com
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
@Controller
public class RepublishController {

    @GetMapping("/republish")
    public Object doRepublish(){
        return "republish";
    }
}
