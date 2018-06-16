package com.blogmaster.demo.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BlogMasterController {
    /**
     * the mapping to the homepage
     */
    @RequestMapping(value = {"/", "/homepage"}, method = RequestMethod.GET)
    public String homePage() {
        return "homepage";
    }
}