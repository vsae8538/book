package com.github.vsae.service;


import com.github.vsae.model.LoginParam;
import com.github.vsae.model.TokenResult;

public interface AdminService {
    public TokenResult doLogin(LoginParam hrLoginVO);
    public void logout(String token);
}
