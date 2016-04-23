package com.zhf.common.net.corehttp;

import java.util.HashMap;
import java.util.Map;


/**
 * Create by user haiFei on 2016/2/24
 */
public class NetResponse {
    private final NetRequest request;
    private final int code;
    private final String message;
    private final String body;
    private final Map<String, String> headers;

    private NetResponse(Builder builder) {
        request = builder.request;
        code = builder.code;
        message = builder.message;
        body = builder.body;
        headers = builder.headers;

    }

    /**
     * Returns the HTTP status code.
     */
    public int code() {
        return code;
    }

    /**
     * Returns true if the code is in [200..300), which means the request was successfully received,
     * understood, and accepted.
     */
    public boolean isSuccessful() {
        return code >= 200 && code < 300;
    }

    /**
     * Returns the HTTP status message or null if it is unknown.
     */
    public String message() {
        return message;
    }

    public Map<String, String> headers() {
        return headers;
    }

    public String header(String name){
        return headers.get(name);
    }

    public String body(){
        return body;
    }

    /*public Builder newBuilder() {
        return new Builder(this);
    }*/

    public static class Builder {
        private NetRequest request;
        private int code;
        private String message;
        private String body;
        private Map<String, String> headers;

        public Builder() {
            headers = new HashMap<>();
        }

        public Builder request(NetRequest request) {
            this.request = request;
            return this;
        }

        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder addHeader(String name, String value) {
            headers.put(name, value);
            return this;
        }

        public NetResponse build() {
            if (request == null) throw new IllegalStateException("request == null");
            if (code < 0) throw new IllegalStateException("code < 0: " + code);
            return new NetResponse(this);
        }

    }


}
