package com.github.vsae.model;

import javax.validation.constraints.NotNull;

public class Admin {

    @NotNull
    private Integer id;

    @NotNull
    //@Pattern(regexp = ConstantSet.USERNAME_REGEXP)
    private String userName;

    @NotNull
    //@Pattern(regexp = ConstantSet.PASSWORD_REGEXP)
    private String password;

    @NotNull
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
