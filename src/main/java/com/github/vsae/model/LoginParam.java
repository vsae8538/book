package com.github.vsae.model;


import javax.validation.constraints.NotNull;

public class LoginParam {

    @NotNull
    //@Pattern(regexp = ConstantSet.USERNAME_REGEXP)
    private String userName;

    @NotNull
    //@Pattern(regexp = ConstantSet.PASSWORD_REGEXP)
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

