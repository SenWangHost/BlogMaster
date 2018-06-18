package com.blogmaster.demo.Controller;


import com.blogmaster.demo.Databean.User;
import com.blogmaster.demo.FormBean.LoginForm;
import com.blogmaster.demo.FormBean.RegisterForm;
import com.blogmaster.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class BlogMasterController {
    @Autowired
    private UserService userService;
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
    public String loginPage(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }
    /**
     * the method to handle the login form
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, @Valid LoginForm loginForm, BindingResult result) {
        model.addAttribute("loginForm", loginForm);
        if (result.hasErrors()) {
            return "login";
        }
        User user = userService.findUserByEmail(loginForm.getEmail());
        if (user == null) {
            result.rejectValue("email", "email", "This email address doesn't exist!");
            return "login";
        }
        if (!user.getPassword().equals(loginForm.getPassword())) {
            result.rejectValue("password", "password", "The password is incorrect!");
            return "login";
        }
        return "homepage";
    }
    /**
     * the mapping to the register page
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }
    /**
     * the method to handle the register form
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model, @Valid RegisterForm registerForm, BindingResult result) {
        model.addAttribute("registerForm", registerForm);
        boolean valid = true;
        if (userService.findUserByEmail(registerForm.getEmail()) != null) {
            result.rejectValue("email", "email", "This email address has already been registered!");
            // model.addAttribute("exist", "This email address has already been registered!");
            valid = false;
        }
        if (!registerForm.checkPasswordMatch()) {
            result.rejectValue("confirmPassword", "confirmPassword", "Confirm password doesn't match!");
            valid = false;
        }
        if (result.hasErrors()) {
//            List<FieldError> errors = result.getFieldErrors();
//            for (FieldError error : errors) {
//                System.out.println(error.getField() + " : " + error.getDefaultMessage() + " : " + error.getCode());
//            }
            valid = false;
        }
        if (!valid) {
            return "register";
        }
        User user = registerForm.convertToUser();
        userService.save(user);
        return "redirect:/login";
    }
}
