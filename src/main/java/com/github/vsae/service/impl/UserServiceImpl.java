package com.github.vsae.service.impl;


import com.github.vsae.mapper.UserMapper;
import com.github.vsae.model.User;
import com.github.vsae.service.UserService;
import com.github.vsae.utils.ResponseException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User add(User user) {
        User userReponse = userMapper.getUserByUsername(user.getUserName());
        if(null != userReponse) {
            throw new ResponseException("The username already be used.");
        }

        int result = userMapper.insert(user);
        if(result  == 0){
            throw new ResponseException("Insert User error.");
        }

       return userMapper.getUserByUsername(user.getUserName());
    }

}
