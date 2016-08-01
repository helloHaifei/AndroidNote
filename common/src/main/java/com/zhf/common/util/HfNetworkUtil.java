package com.zhf.common.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class HfNetworkUtil {
	
	/**
	 * 判断是否接入 internet
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isConnectedNetwork(Context context) {

		ConnectivityManager connManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mWifi = connManager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		NetworkInfo mMobile = connManager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

		if (!mWifi.isConnected() && !mMobile.isConnected()) {
			return false;
		}
		return true;
	}

}
