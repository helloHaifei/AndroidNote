package util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class NetUtil {
	public static final String TAG="NetUtil";
	/**
	 * 检查网络状态
	 */
	public static boolean checkNetState() {
		NetworkInfo info = getNetworkInfo();
		return  (info != null && info.isAvailable() && info.isConnected());

	}

	/**
	 * 获取连接网络的对象
	 *
	 * @return
	 */
	public static NetworkInfo getNetworkInfo() {
		ConnectivityManager connectMgr = (ConnectivityManager) ContextUtil.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectMgr.getActiveNetworkInfo();
		return info;
	}

	/**
	 * 判断是否是手机网络
	 *
	 * @return
	 */
	public static boolean isMobile() {
		boolean flag = false;
		NetworkInfo info = getNetworkInfo();
		// 非wifi网络
		if (info != null) {
			if (!info.getTypeName().equals("WIFI")) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 判断是否是WIFI
	 *
	 * @return
	 */
	public static boolean isWifi() {
		boolean flag = false;
		NetworkInfo info = getNetworkInfo();
		// 非wifi网络
		if (info != null) {
			if (info.getTypeName().equals("WIFI")) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * ping 获取域名对应ip地址
	 * @param m_strForNetAddress
	 * @return
	 */
	public static String pingHost(String m_strForNetAddress) {
		String result="";
		Process p;
		try {
			p = Runtime.getRuntime().exec("/system/bin/ping -c 1 " + m_strForNetAddress);
			int status = p.waitFor();

	        if (status == 0) {
	            result="success";
	        }
	        else
	        {
	            result="failed";
	            return "";
	        }
	        String ipStr = "";
	        BufferedReader buf = new BufferedReader(new InputStreamReader(p.getInputStream()));

	        String str = null;
	        while((str=buf.readLine())!=null){
			    if (str.startsWith("PING")) {
			    	ipStr=str;
			    	break;
				}
			    Log.d(TAG, str);
			}
	        String[] strArr=str.split("\\(|\\)");
	        if (strArr.length==5) {
	        	Log.d(TAG, strArr[1]);
				return strArr[1];
			}
		} catch (IOException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		} catch (InterruptedException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		}
		return "";

	}

	/**得到字符串形式的ip地址*/
	public static String getHostIp(String m_strForNetAddress) {
		InetAddress x;
		String ip_devdiv="";
        try {
                x = InetAddress.getByName(m_strForNetAddress);
                ip_devdiv = x.getHostAddress();
        } catch (UnknownHostException e) {
                e.printStackTrace();
        }
        return ip_devdiv;
	}

	/**
	 * 获取当然网络连接类型
	 * @return
	 */
	public static String getNetWorkType(){
		String netStatue = "unknown";
		NetworkInfo info = getNetworkInfo();
		if (info != null && info.isAvailable()) {
			String extraInfo = info.getExtraInfo();
			if (extraInfo != null && extraInfo.contains("wap")) {
				netStatue = "2g";
			} else {
				if (PhoneHelper.isNetWorkType3G()) {
					netStatue = "3g";
				} else if (PhoneHelper.isNetWorkType4G()) {
					netStatue = "4g";
				} else {
					netStatue = "wifi";
				}
			}
		}

		return netStatue;
	}

	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					String hostAddress = inetAddress.getHostAddress();
					return hostAddress;
				}
			}
		} catch (SocketException ex) {
			ex.printStackTrace();
		}
		return "";
	}
}
