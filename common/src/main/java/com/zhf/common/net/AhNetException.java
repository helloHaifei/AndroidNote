package com.zhf.common.net;

import java.io.IOException;

/**
 * 网络层面的异常
 * 统一处理一下异常情况，在UI层面Toast message, 需要特殊处理，case code,或者直接处理Exception
 *
 * Created by zhf on 16/3/12.
 */
public class AhNetException extends IOException{

    public static final int CODE_DEFAULT = -1;
    public static final int CODE_NETWORK_ERR= 1000;
    public static final int CODE_PARSE_JSON_ERR= 1001;

    public static final String MSG_DEFAULT= "网络错误，请稍后重试";
    public static final String MSG_NETWORK_ERR= "网络错误，请稍后重试";
    public static final String MSG_PARSE_JSON_ERR= "解析数据出错";


    /**
     * code 码
     */
    private int code;

    /**
     * 错误信息
     */
    private String message;
    /**
     * 真正的异常
     */
    private Exception exception;

    public AhNetException(Exception e){
        this(CODE_DEFAULT,MSG_DEFAULT,e);
    }

    public AhNetException(int code, String message){
        this(code, message, null);
    }
    public AhNetException(int code, Exception e){
        this(CODE_DEFAULT,MSG_DEFAULT, e);
    }

    public AhNetException(int code, String message, Exception e){
        this.code = code;
        this.message = message;
        this.exception = e;
    }

    public AhNetException setCode(int code) {
        this.code = code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public AhNetException setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getMessage() {
        //TODO 需要根据code exception 处理一下message
        if(message== null){
            message = MSG_NETWORK_ERR;
        }
        return message;
    }

    public AhNetException setException(Exception exception) {
        this.exception = exception;
        return this;
    }

    public Exception getException() {
        return exception;
    }
}
