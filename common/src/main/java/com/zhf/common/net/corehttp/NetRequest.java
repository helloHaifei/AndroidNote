package com.zhf.common.net.corehttp;

import java.util.HashMap;
import java.util.Map;

/**
 * 网络请求参数
 *
 * Create by haiFei on 2016/2/1
 */
public class NetRequest {

    public static final String GET = "GET";
    public static final String POST = "POST";

    private String url;
    private String method = GET;//方法类型 //GET POST

    private Map<String,Object> headers;
    private Map<String,Object> params;

    public NetRequest(){
    }
    public NetRequest(Builder builder){
        url = builder.url;
        method = builder.url;
        headers = builder.headers;
        params = builder.params;
    }

    public NetRequest(String url){
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public NetRequest setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public NetRequest setMethod(String method) {
        this.method = method;
        return this;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public NetRequest addHeader(String key, Object value){
        if(headers == null){
            headers = new HashMap<>();
        }
        headers.put(key, value);

        return this;
    }

    public NetRequest addParam(String key, Object value){
        if(params == null){
            params = new HashMap<>();
        }
        params.put(key, value);

        return this;
    }

    public NetRequest addParam(Map<String, Object> map){
        if (map == null) {
            return this;
        }

        if(params == null){
            params = new HashMap<>();
        }
        params.putAll(map);

        return this;
    }

    public static class Builder{
        private String url;
        private String method;//方法类型 //GET POST

        private Map<String,Object> headers;
        private Map<String,Object> params;

        public Builder(){
            headers = new HashMap<>();
            params = new HashMap<>();
        }
        public Builder url(String url){
            this.url = url;
            return this;
        }
        public Builder method(String method){
            this.method = method;
            return this;
        }
        public Builder addHeader(String name,String value){
            headers.put(name,value);
            return this;
        }
        public Builder addParams(String name,String value){
            params.put(name,value);
            return this;
        }

        public NetRequest build(){
            return new NetRequest(this);
        }
    }

}
