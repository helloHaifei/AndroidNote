package com.zhf.sampleapp.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;

import com.zhf.sampleapp.R;

/**
 * JAVA 与 html通过javascript 相互调用的例子
 * 
 * @author: Haifei
 * @data：2014-3-25
 * @blog:http://blog.csdn.net/hellohaifei
 * 
 */
public class JavascriptInterface extends Activity {
	private WebView webView;

	@android.webkit.JavascriptInterface
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.javascriptinterface);
		// 加载页面
		webView = (WebView) findViewById(R.id.webView);
		// 允许JavaScript执行
		webView.getSettings().setJavaScriptEnabled(true);
		// 找到Html文件，也可以用网络上的文件
		webView.loadUrl("file:///android_asset/javascriptinterface.html");
		// 添加一个对象, 让JS可以访问该对象的方法, 该对象中可以调用JS中的方法
		webView.addJavascriptInterface(new Contact(), "contact");
		webView.addJavascriptInterface(new MyContact(), "mycontact");
	}

	private class Contact {
		// JavaScript调用此方法拨打电话
		@android.webkit.JavascriptInterface
		public void call(String phone) {
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