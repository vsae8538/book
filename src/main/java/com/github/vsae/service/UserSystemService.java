package com.github.vsae.service;


import com.github.vsae.model.LoginParam;
import com.github.vsae.model.TokenResult;
import com.github.vsae.model.User;

public interface UserSystemService {

    public TokenResult doLogin(LoginParam hrLoginVO);

    public void logout(String token);

    public User add(User user);

    public User query(Integer id);

    public User edit(User user);
}
