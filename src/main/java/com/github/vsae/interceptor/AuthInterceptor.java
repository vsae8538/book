package com.github.vsae.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.github.vsae.utils.AuthToken;
import com.github.vsae.utils.RedisUtils;
import com.github.vsae.utils.ResponseMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    //public static final String AUTH_KEY = "AUTH-CODE-";
    public static final String ADMIN_ID_KEY = "ADMIN-ID-";

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        /* Token 驗證 */
        if (method.getAnnotation(AuthToken.class) != null || handlerMethod.getBeanType().getAnnotation(AuthToken.class) != null) {

            String token = request.getHeader(AUTHORIZATION);
            String adminId = StringUtils.EMPTY;

            if (!StringUtils.isEmpty(token)) {
                adminId = (String) redisUtils.get(token);
                request.setAttribute("adminId", adminId);
            }

            if (StringUtils.isEmpty(adminId)) {
                failAuth(response);
                return false;
            }
        }

        //this.crossDomainConfig(response);
        return true;
    }

    private void failAuth(HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        PrintWriter out = null;
        try {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            jsonObject.put("message", HttpStatus.UNAUTHORIZED);

            out = response.getWriter();
            out.println(ResponseMessage.failed(null, jsonObject));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
    }

}
