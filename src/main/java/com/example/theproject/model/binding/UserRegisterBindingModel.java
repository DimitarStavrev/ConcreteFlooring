package com.example.theproject.model.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {

    private String username;
    private String fullName;
    private String email;
    private Integer age;
    private String password;
    private String confirmPassword;

    public UserRegisterBindingModel() {
    }

    //TODO: implement in html validation for max size
    @Size(min = 3, max = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Size(min = 5, max = 30)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //TODO: validation max years old
    @Positive
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Size(min = 3, max = 30)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Size(min = 3, max = 30)
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
