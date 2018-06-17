package com.blogmaster.demo.FormBean;

import com.blogmaster.demo.Databean.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class RegisterForm {
    @NotBlank(message = "email address cannot be empty")
    @Email
    private String email;
    @NotBlank(message = "password cannot be empty")
    @Length(min = 6, message = "password has to be longer than 6 characters")
    private String password;
    @NotBlank(message = "confirm password cannot be empty")
    @Length(min = 6, message = "confirm password has to be longer than 6 characters")
    private String confirmPassword;
    @NotBlank(message = "firstname cannot be empty")
    private String firstname;
    @NotBlank(message = "lastname cannot be empty")
    private String lastname;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public boolean checkPasswordMatch() {
        if (password.equals(confirmPassword)) {
            return true;
        }
        return false;
    }

    public User convertToUser() {
        User result = new RegisterFormConvert().convert(this);
        return result;
    }

    private class RegisterFormConvert implements FormConvert<RegisterForm, User> {
        @Override
        public User convert(RegisterForm registerForm) {
            User user = new User();
            BeanUtils.copyProperties(registerForm, user);
            return user;
        }
    }
}
