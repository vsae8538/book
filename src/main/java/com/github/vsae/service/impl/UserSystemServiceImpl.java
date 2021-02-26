package com.github.vsae.service.impl;


import com.github.vsae.interceptor.AuthInterceptor;
import com.github.vsae.mapper.UserMapper;
import com.github.vsae.model.LoginParam;
import com.github.vsae.model.TokenResult;
import com.github.vsae.model.User;
import com.github.vsae.service.UserSystemService;
import com.github.vsae.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSystemServiceImpl implements UserSystemService {

    private static final Logger logger = LoggerFactory.getLogger(UserSystemServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    Md5TokenGenerator tokenGenerator;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public TokenResult doLogin(LoginParam loginParam){
        String userName = loginParam.getUsername();
        String password = loginParam.getPassword();
        User user = userMapper.getUserByUsername(userName);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        logger.info("user login: " + userName);

        if(user != null && encoder.matches(password, user.getPassword())){
            String token = (String) redisUtils.get(AuthInterceptor.ADMIN_ID_KEY + String.valueOf(user.getId()));
            if(StringUtils.isNotBlank(token)){
                logout(token);
                logger.info("Repeat login userName : {}", user.getUserName());
            }
            return createToken(user.getUserName(), user.getPassword(), user.getId());
        }

        return new TokenResult();
    }

    @Override
    public void logout(String token) {
        String adminId = (String) redisUtils.get(token);
        redisUtils.del(token);
        redisUtils.del(AuthInterceptor.ADMIN_ID_KEY + adminId);

        logger.info("user logout token: " + token);
    }

    private TokenResult createToken(String userName, String password, Integer adminId) {
        TokenResult tokenResult = new TokenResult();

        String token = tokenGenerator.generate(userName, password);
        redisUtils.set(token, adminId, ConstantSet.TOKEN_EXPIRE_TIME);
        redisUtils.set(AuthInterceptor.ADMIN_ID_KEY + adminId, token, ConstantSet.TOKEN_EXPIRE_TIME);

        tokenResult.setToken(token);
        tokenResult.setExpireTime(Long.valueOf(redisUtils.getExpire(token)));

        return tokenResult;
    }

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

    @Override
    public User query(Integer userId) {
        User user = userMapper.getUserById(userId);
        if(null == user){
            throw new ResponseException("No User.");
        }
        return user;
    }

    @Override
    public User edit(User user) {
        int result = userMapper.update(user);
        if(result == 0){
            throw new ResponseException("Update User error.");
        }
        return userMapper.getUserByUsername(user.getUserName());
    }
}
