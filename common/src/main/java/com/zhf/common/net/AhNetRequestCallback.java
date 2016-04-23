package com.zhf.common.net;

/**
 * Create by user haiFei on 2016/2/24
 */
public interface AhNetRequestCallback<T> {
    /**
     * 接口返回数据并且解析成功后回调
     */
    void onSuccess(AhNetRequest request, T t);
    /**
     * 失败时回调
     */
    void onFailure(AhNetRequest request, AhNetException e);
}
