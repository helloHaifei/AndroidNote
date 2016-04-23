package com.zhf.common.net.corehttp;

/**
 * 网络请求回调
 *
 *
 * Create by haiFei on 2016/2/1
 */
public interface NetRequestListener {
    /**
     * 接口返回数据并且解析成功后回调
     * @param response
     */
    void onSuccess(NetRequest request, NetResponse response);
    /**
     * 失败时回调
     */
    void onFailure(NetRequest request, Exception e);


}
