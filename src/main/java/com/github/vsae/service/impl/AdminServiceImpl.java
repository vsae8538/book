package com.github.vsae.service.impl;


import com.github.vsae.interceptor.AuthInterceptor;
import com.github.vsae.mapper.UserMapper;
import com.github.vsae.model.LoginParam;
import com.github.vsae.model.TokenResult;
import com.github.vsae.model.User;
import com.github.vsae.service.AdminService;
import com.github.vsae.utils.BCryptPasswordEncoder;
import com.github.vsae.utils.ConstantSet;
import com.github.vsae.utils.Md5TokenGenerator;
import com.github.vsae.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger logger = LoggerFactory.getLogger(com.github.vsae.service.impl.AdminServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    Md5TokenGenerator tokenGenerator;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public TokenResult doLogin(LoginParam loginParam){
        String userName = loginParam.getUserName();
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
}
