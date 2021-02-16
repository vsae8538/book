package com.github.vsae.utils;

import com.github.vsae.utils.ErrorCode;
import com.github.vsae.utils.HttpCode;

public class ResponseMessage<T> {
    private T data;
    private String message;
    private long code;

    public ResponseMessage() {
    }

    public ResponseMessage(T data, String message, long code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public ResponseMessage(String message, long code) {
        this(null, message, code);
    }

    public ResponseMessage(T data) {
        this(data, "Success.", 200);
    }

    public ResponseMessage(long code, String message, T data){
        this(data, message, code);
    }

    public ResponseMessage(long code, T data) {
        this(data, null, code);
    }

    /**
     * 成功返回結果
     * @param data 獲取資料
     */
    public static <T> ResponseMessage<T> success(T data) {
        return new ResponseMessage<T>(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回結果
     * @param data 獲取資料
     * @param message 提示訊息
     */
    public static <T> ResponseMessage<T> success(T data, String message) {
        return new ResponseMessage<T>(HttpCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失敗返回結果
     * @param fmErrorCode 錯誤碼
     */
    public static <T> ResponseMessage<T> failed(ErrorCode fmErrorCode) {
        return new ResponseMessage<T>(fmErrorCode.getCode(), fmErrorCode.getMessage(), null);
    }

    /**
     * 失敗返回結果
     * @param fmErrorCode 錯誤碼
     * @param message 錯誤訊息
     */
    public static <T> ResponseMessage<T> failed(ErrorCode fmErrorCode, String message) {
        return new ResponseMessage<T>(fmErrorCode.getCode(), message, null);
    }

    /**
     * 失敗返回結果
     * @param fmErrorCode 錯誤碼
     * @param data json物件
     */
    public static <T> ResponseMessage<T> failed(ErrorCode fmErrorCode, T data) {
        return new ResponseMessage<T>(fmErrorCode.getCode(), null, data);
    }

    /**
     * 失敗返回結果
     * @param message 提示訊息
     */
    public static <T> ResponseMessage<T> failed(String message) {
        return new ResponseMessage<T>(HttpCode.FAILED.getCode(), message, null);
    }

    /**
     * 失敗返回結果
     */
    public static <T> ResponseMessage<T> failed() {
        return failed(HttpCode.FAILED);
    }

    /**
     * 參數驗證失敗返回結果
     */
    public static <T> ResponseMessage<T> validateFailed() {
        return failed(HttpCode.VALIDATE_FAILED);
    }

    /**
     * 參數驗證失敗返回結果
     * @param message 提示信息
     */
    public static <T> ResponseMessage<T> validateFailed(String message) {
        return new ResponseMessage<T>(HttpCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登入返回結果
     */
    public static <T> ResponseMessage<T> unauthorized(T data) {
        return new ResponseMessage<T>(HttpCode.UNAUTHORIZED.getCode(), HttpCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授權返回結果
     */
    public static <T> ResponseMessage<T> forbidden(T data) {
        return new ResponseMessage<T>(HttpCode.FORBIDDEN.getCode(), HttpCode.FORBIDDEN.getMessage(), data);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }
}
