package com.github.vsae.utils;

/**
 *  系統返回碼
 */
public enum ResponseCode implements ErrorCode {

    EMAIL_IS_REGISTERED(1, "The email is registered"),
    INSERT_DB_FAILED(2, "Insert Database error"),
    TOKEN_IS_EMPTY(3, "token is empty"),
    TRENDING_PRODUCT_IS_EMPTY(4, "Trending product is empty"),
    LASTEST_PRODUCT_IS_EMPTY(5, "Lastest product is empty"),
    BEST_PRODUCT_IS_EMPTY(6, "Best product is empty");

    private long code;

    private String message;

    private ResponseCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
