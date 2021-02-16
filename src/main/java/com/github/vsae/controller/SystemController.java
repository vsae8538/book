package com.github.vsae.controller;


import com.github.vsae.model.LoginParam;
import com.github.vsae.model.TokenResult;
import com.github.vsae.model.User;
import com.github.vsae.service.AdminService;
import com.github.vsae.service.UserService;
import com.github.vsae.utils.ResponseMessage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
public class SystemController {

    private static final Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseMessage login(@Valid @RequestBody LoginParam loginParam){
        TokenResult tokenResult = adminService.doLogin(loginParam);
        if(StringUtils.isNotBlank(tokenResult.getToken())){
            return ResponseMessage.success(tokenResult, "OK.");
        }else{
            return ResponseMessage.failed("No user or password failed.");
        }
    }

    @ApiOperation(value = "註冊", response = ResponseMessage.class, authorizations = @Authorization(value = "Authorization"))
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseMessage addUser(@ApiParam(required = true, value = "使用者資訊") @Valid @RequestBody User user){
        ResponseMessage responseMessage;
        try{
            User userResponse = userService.add(user);
            responseMessage = new ResponseMessage(200,"register success.", userResponse);
        }catch (Exception e){
            logger.error("Request occurred error: {}", e.getMessage());
            responseMessage = new ResponseMessage(400, e.getMessage(), null);
        }
        return  responseMessage;
    }



}
