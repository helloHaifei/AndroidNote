package com.zhf.common.net;


import com.zhf.common.net.corehttp.NetRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;

/**
 * 请参考 接口文档 http://wiki.corpautohome.com/pages/viewpage.action?pageId=60263558
 * <p>
 * Create by user haiFei on 2016/2/24
 */
public abstract class AhNetRequest<T> extends NetRequest {


    /**
     * 生成请求数据，会在真正请求接口前，调用一次
     * <p>
     * #setUrl
     * #setMethod
     * #addHeader
     * #addParams
     */
    public abstract void generateParams();

    /**
     * 处理数据 需要result结果的需要重写
     *
     * @param bodyStr
     * @return
     */
    public abstract T processData(Map<String, String> header, String bodyStr);

    @Override
    public String getUrl() {
        String url = super.getUrl();
        /*
        if (getMethod().equals(GET) && getParams() != null && getParams().size() > 0) {
            if (!url.contains("?")) {
                url += "?";
            }
            ArrayList<String> arrayList = new ArrayList<>();
            for (Map.Entry<String, Object> key : getParams().entrySet()) {
                try {
                    arrayList.add(key.getKey() + "=" + URLEncoder.encode(key.getValue().toString(), "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            url = url + StringUtil.join(arrayList, "&");
        }
*/
        return url;
    }


}
