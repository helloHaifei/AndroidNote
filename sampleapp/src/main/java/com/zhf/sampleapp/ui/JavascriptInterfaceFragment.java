package com.zhf.sampleapp.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zhf.sampleapp.R;
import com.zhf.sampleapp.view.FixedGridLayout;


/**
 * 
 * @author: Haifei
 * @data：2016-8-9
 * @blog:http://blog.csdn.net/hellohaifei
 * 
 */
public class JavascriptInterfaceFragment extends BaseFragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.javascriptinterface,null);
        // 加载页面
        webView = (WebView) rootView.findViewById(R.id.webView);
        // 允许JavaScript执行
        webView.getSettings().setJavaScriptEnabled(true);
        // 找到Html文件，也可以用网络上的文件
        webView.loadUrl("file:///android_asset/javascriptinterface.html");
        // 添加一个对象, 让JS可以访问该对象的方法, 该对象中可以调用JS中的方法
        webView.addJavascriptInterface(new Contact(), "contact");
        webView.addJavascriptInterface(new MyContact(), "mycontact");

		return rootView;
	}

    private WebView webView;

    @android.webkit.JavascriptInterface
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private class Contact {
        // JavaScript调用此方法拨打电话
        @android.webkit.JavascriptInterface
        public void call(String phone) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
                    + phone)));
        }

        // Html调用此方法传递数据
        @android.webkit.JavascriptInterface
        public void showcontacts() {
            String json = "[{\"name\":\"zxx\", \"amount\":\"9999999\", \"phone\":\"10010\"}]";
            // 调用JS中的方法
            webView.loadUrl("javascript:show('" + json + "')");
        }
    }

    class MyContact extends Contact{
        // JavaScript调用此方法拨打电话
        @android.webkit.JavascriptInterface
        public void mycall(String phone) {
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone)));

        }

        // Html调用此方法传递数据
        @android.webkit.JavascriptInterface
        public void showcontacts() {
            String json = "[{\"name\":\"zxx\", \"amount\":\"9999999\", \"phone\":\"10010\"}]";
            // 调用JS中的方法
            webView.loadUrl("javascript:show('" + json + "')");
        }
    }

}