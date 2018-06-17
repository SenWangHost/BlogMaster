package com.blogmaster.demo.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BlogMasterController {
    /**
     * the mapping to the homepage page
     */
    @RequestMapping(value = {"/", "/homepage"}, method = RequestMethod.GET)
    public String homePage() {
        return "homepage";
    }
    /**
     * the mapping to the login page
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }
    /**
     * the mapping to the register page
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage() {
        return "register";
    }
}
