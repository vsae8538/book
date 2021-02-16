package com.github.vsae.utils;

public class ConstantSet {

    /**
     * redis存儲token 設置的過期時間, 180分鐘
     */
    public static final Integer TOKEN_EXPIRE_TIME = 60 * 180;

    public static final String TELEPHONE_REGEXP = "^(([0\\+]\\d{1,3} )?(\\d{3}) )?(\\d{3})( (\\d{3,4}))?$";
    public static final String ID_REGEXP = "[0-9a-fA-F]{24}";
    public static final String URI_REGEXP = "(\\/?)[^\\s]+";
    public static final String USERNAME_REGEXP = "^[0-9a-zA-Z_]{6,20}$";
    public static final String PASSWORD_REGEXP = "^[0-9a-zA-Z_]{6,20}$";

}
