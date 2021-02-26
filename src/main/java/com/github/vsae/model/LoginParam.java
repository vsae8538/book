package com.github.vsae.model;


import javax.validation.constraints.NotNull;

public class LoginParam {

    @NotNull
    //@Pattern(regexp = ConstantSet.USERNAME_REGEXP)
    private String username;

    @NotNull
    //@Pattern(regexp = ConstantSet.PASSWORD_REGEXP)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

