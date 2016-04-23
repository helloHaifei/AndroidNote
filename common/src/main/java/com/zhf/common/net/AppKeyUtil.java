package com.zhf.common.net;

import android.text.TextUtils;

/**
 *
 * Created by zhf on 16/3/11.
 */
public class AppKeyUtil {

    // 获取接口签名用的appKey
    private static String appKey = null;

    public static synchronized String getAppKey() {
        if (TextUtils.isEmpty(appKey)) {
            CreateAppKey();
        }
        return appKey;
    }


    private static void CreateAppKey() {
        try {
//            Context context = ContextUtil.getApplicationContext();
//            View view = View.inflate(context, R.layout.error_item_layout, null);
//            View view2 = View.inflate(context, R.layout.item_login_layout, null);
//            View view3 = View.inflate(context, R.layout.net_sign_layout, null);
//            TextView tt1 = (TextView) view.findViewById(R.id.error_item_1);
//            TextView tt2 = (TextView) view.findViewById(R.id.error_item_2);
//            TextView tt3 = (TextView) view2.findViewById(R.id.item_login_3);
//            TextView tt4 = (TextView) view2.findViewById(R.id.item_login_4);
//            TextView tt5 = (TextView) view2.findViewById(R.id.item_login_5);
//            TextView tt6 = (TextView) view3.findViewById(R.id.tvtv6);
//            TextView tt7 = (TextView) view3.findViewById(R.id.tvtv7);
//            TextView tt8 = (TextView) view3.findViewById(R.id.tvtv8);
//            TextView tt9 = (TextView) view3.findViewById(R.id.tvtv9);
//
//
//            StringBuilder sb = new StringBuilder();
//            sb.append(tt1.getText());
//            sb.append(tt2.getText());
//            sb.append(tt3.getText());
//            sb.append(tt4.getText());
//            sb.append(tt5.getText());
//            sb.append(tt6.getText());
//            sb.append(tt7.getText());
//            sb.append(tt8.getText());
//            sb.append(tt9.getText());
            //appKey = sb.toString();
        } catch (Exception e) {
            appKey = null;
            e.printStackTrace();
        }
    }
}
