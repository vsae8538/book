package com.github.vsae.utils;

public class ResponseException extends RuntimeException{

    private String responseMessage;

    private Integer responseCode;

    private Object data;

    public ResponseException(String message) {
        super(message);
    }

    public ResponseException(String message, String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public ResponseException(String message, String responseMessage, Integer responseCode) {
        this.responseMessage = responseMessage;
        this.responseCode = responseCode;
    }

    public ResponseException(String message, String responseMessage, Integer responseCode, Object data) {
        this.responseMessage = responseMessage;
        this.responseCode = responseCode;
        this.data = data;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
