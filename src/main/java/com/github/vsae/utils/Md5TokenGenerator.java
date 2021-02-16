package com.github.vsae.utils;

import com.github.vsae.utils.TokenGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
public class Md5TokenGenerator implements TokenGenerator {

    @Override
    public String generate(String... strings) {
        String tokenMeta = "";
        for (String s : strings) {
            tokenMeta = tokenMeta + s;
        }
        tokenMeta = tokenMeta + System.currentTimeMillis();
        String token = DigestUtils.md5DigestAsHex(tokenMeta.getBytes());
        return token;
    }
}
