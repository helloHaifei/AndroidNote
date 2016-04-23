package com.zhf.common.net.corehttp;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 *
 * <p/>
 * 对于UI层来说，不应该关心用的是哪种网络库（Volley,OkHttp）,也不应该使用网络库的任何类文件。介于网络库上面的一层，不对网络库产生依赖。
 * 主要目的：1.使用简单，封装网络库  2.扩展性，方便日后可以替换网络库。
 * <p/>
 * Create by haiFei on 2016/2/1
 */
public class NetHttpClient {

    OkHttpClient mOkHttpClient;

    private static NetHttpClient mNetDataManager;

    public NetHttpClient() {
        mOkHttpClient = new OkHttpClient.Builder().build();
    }

    /**
     * request
     *
     * @param requestParams
     * @param requestListener
     */
    public void request(final NetRequest requestParams, final NetRequestListener requestListener) {
        if (requestParams == null) {
            throw new IllegalArgumentException("request is null");
        }

        Request.Builder requestBuilder = new Request.Builder().url(requestParams.getUrl());
        //增加header
        if (requestParams.getHeaders() != null && requestParams.getHeaders().size() > 0) {
            for (Map.Entry<String, Object> key : requestParams.getHeaders().entrySet()) {
                requestBuilder.addHeader(key.getKey(), String.valueOf(key.getValue()));
            }
        }

        if (requestParams.getMethod().equals("POST")){
            if (requestParams.getParams() != null && requestParams.getParams().size() > 0) {
                FormBody.Builder formBuilder = new FormBody.Builder();
                for (Map.Entry<String, Object> key : requestParams.getParams().entrySet()) {
                    formBuilder.add(key.getKey(), String.valueOf(key.getValue()));
                }
                requestBuilder.post(formBuilder.build());
            } else {
                requestBuilder.post(null);
            }
        } else { // GET
            requestBuilder.get();
        }

        Request request = requestBuilder.build();

        Call call = mOkHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (requestListener != null) {
                    requestListener.onFailure(requestParams, e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String htmlStr = response.body().string();


                NetResponse.Builder netResponse = new NetResponse.Builder().code(response.code()).message(response.message()).body(htmlStr);
                Headers header = response.headers();
                if(header != null) {
                    for (int i = 0; i < header.size(); i++) {
                        netResponse.addHeader(header.name(i), header.value(i));
                    }
                }

                //待优化
                netResponse.request(requestParams);

                if (requestListener != null) {
                    requestListener.onSuccess(requestParams, netResponse.build());
                }

            }
        });
    }

    /**
     * request
     *
     * @param requestParams
     */
    public Response syncRequest(final NetRequest requestParams) throws IOException {
        if (requestParams == null) {
            throw new IllegalArgumentException("request is null");
        }

        Request.Builder requestBuilder = new Request.Builder().url(requestParams.getUrl());
        //增加header
        if (requestParams.getHeaders() != null && requestParams.getHeaders().size() > 0) {
            for (Map.Entry<String, Object> key : requestParams.getHeaders().entrySet()) {
                requestBuilder.addHeader(key.getKey(), String.valueOf(key.getValue()));
            }
        }

        if (requestParams.getMethod().equals("POST")){
            if (requestParams.getParams() != null && requestParams.getParams().size() > 0) {
                FormBody.Builder formBuilder = new FormBody.Builder();
                for (Map.Entry<String, Object> key : requestParams.getParams().entrySet()) {
                    formBuilder.addEncoded(key.getKey(), URLEncoder.encode(String.valueOf(key.getValue()), "utf-8"));
                }
                requestBuilder.post(formBuilder.build());
            } else {
                requestBuilder.post(null);
            }
        } else { // GET
            requestBuilder.get();
        }

        Request request = requestBuilder.build();

        Call call = mOkHttpClient.newCall(request);

        return call.execute();
    }

}
