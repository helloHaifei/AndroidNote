package com.zhf.common.util;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

/**
 * Android手机的唯一序列号
 * 
 * @author haifei.zhang
 * @date 2013-9-9
 */
public class AndroidSerialNumber {

	static int index = 1;

	Context mContext;

	private static AndroidSerialNumber mAndroidSerialNumber;
	public static AndroidSerialNumber getInstance(Context context){
		if(mAndroidSerialNumber == null){
			mAndroidSerialNumber = new AndroidSerialNumber(context);
		}
		return mAndroidSerialNumber;
	}
	private AndroidSerialNumber(Context context) {
		mContext = context;
	}

	/**
	 * Android手机唯一序列号
	 * 
	 * @return
	 */
	public String getAndroidSerialNumber() {
		String serialNumber = IMEI() + AndroidId() + WlanMacId();
		serialNumber = HfMd5.getMD532(serialNumber);
		return serialNumber;
	}

	/**
	 * The IMEI: 需要android.permission.READ_PHONE_STATE
	 * 
	 * @return
	 */
	public String IMEI() {
		String szImei = "";
		try {
			
			TelephonyManager TelephonyMgr = (TelephonyManager) mContext
					.getSystemService(Context.TELEPHONY_SERVICE);
			szImei = TelephonyMgr.getDeviceId();
		} catch (Exception e) {
		}
		return szImei;
	}

	/**
	 * The Android ID
	 * 
	 * @return
	 */
	public String AndroidId() {

		String m_szAndroidID = Secure.getString(mContext.getContentResolver(),
				Secure.ANDROID_ID);
		return m_szAndroidID;
	}

	/**
	 * The WLAN MAC Address string
	 * 
	 * 需要：android.permission.ACCESS_WIFI_STATE
	 * 
	 * @return
	 */
	public String WlanMacId() {

		WifiManager wm = (WifiManager) mContext
				.getSystemService(Context.WIFI_SERVICE);
		String m_szWLANMAC = wm.getConnectionInfo().getMacAddress();
		return m_szWLANMAC;
	}

}
