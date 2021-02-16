package com.github.vsae.utils;

/**
 * Http返回碼
 */
public enum HttpCode implements ErrorCode {

    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失敗"),
    VALIDATE_FAILED(404, "參數校驗失敗"),
    UNAUTHORIZED(401, "未登入或token已經過期"),
    FORBIDDEN(403, "沒有相關權限");

    private long code;

    private String message;

    private HttpCode(long code, String message) {
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
