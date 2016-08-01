//package com.zhf.sampleapp.ui;
//
//import android.app.Activity;
//import android.app.Notification;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.webkit.*;
//import android.widget.Button;
//import android.widget.LinearLayout;
//
//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.zhf.sampleapp.R;
//
///**
// * 通知栏
// *
// * @author Administrator
// */
//public class NotificationActivity extends Activity {
//
//	private static int MOOD_NOTIFICATIONS = 999;
//	private NotificationManager mNotificationManager;
//	/**
//	 * ATTENTION: This was auto-generated to implement the App Indexing API.
//	 * See https://g.co/AppIndexing/AndroidStudio for more information.
//	 */
//	private GoogleApiClient mClient;
//
//	@android.webkit.JavascriptInterface
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		LinearLayout root = new LinearLayout(this);
//		setContentView(root);
//
//		Button btn1 = new Button(this);
//		btn1.setText("显示");
//		btn1.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				setMood(getString(R.string.hello_world));
//
//			}
//		});
//		root.addView(btn1);
//		Button btn2 = new Button(this);
//		btn2.setText("更新");
//		btn2.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				setMood("更新成功");
//
//			}
//		});
//		root.addView(btn2);
//		Button btn3 = new Button(this);
//		btn3.setText("取消");
//		btn3.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				mNotificationManager.cancel(MOOD_NOTIFICATIONS);
//
//			}
//		});
//		root.addView(btn3);
//		mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//		// ATTENTION: This was auto-generated to implement the App Indexing API.
//		// See https://g.co/AppIndexing/AndroidStudio for more information.
//		mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
//	}
//
//	private void setMood(String text) {
//		// In this sample, we'll use the same text for the ticker and the
//		// expanded notification
//		//CharSequence text = getText(textId);
//
//		// choose the ticker text
//		String tickerText = "ticker" + text;
//
//		// Set the icon, scrolling text and timestamp
//		Notification notification = new Notification(R.drawable.ic_launcher, tickerText,
//				System.currentTimeMillis());
//		// new Notification.Builder(this).addAction(icon, title, intent)
//		// Set the info for the views that show in the notification panel.
//		notification.setLatestEventInfo(this, getText(R.string.app_name), text,
//				makeMoodIntent());
//
//		// Send the notification.
//		// We use a layout id because it is a unique number. We use it later to
//		// cancel.
//		mNotificationManager.notify(MOOD_NOTIFICATIONS, notification);
//	}
//
//	private PendingIntent makeMoodIntent() {
//		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
//				new Intent(),
//				PendingIntent.FLAG_UPDATE_CURRENT);
//		return contentIntent;
//	}
//
//	@Override
//	public void onStart() {
//		super.onStart();
//
//		// ATTENTION: This was auto-generated to implement the App Indexing API.
//		// See https://g.co/AppIndexing/AndroidStudio for more information.
//		mClient.connect();
//		Action viewAction = Action.newAction(
//				Action.TYPE_VIEW, // TODO: choose an action type.
//				"Notification Page", // TODO: Define a title for the content shown.
//				// TODO: If you have web page content that matches this app activity's content,
//				// make sure this auto-generated web page URL is correct.
//				// Otherwise, set the URL to null.
//				Uri.parse("http://host/path"),
//				// TODO: Make sure this auto-generated app URL is correct.
//				Uri.parse("android-app://com.zhf.sampleapp.ui/http/host/path")
//		);
//		AppIndex.AppIndexApi.start(mClient, viewAction);
//	}
//
//	@Override
//	public void onStop() {
//		super.onStop();
//
//		// ATTENTION: This was auto-generated to implement the App Indexing API.
//		// See https://g.co/AppIndexing/AndroidStudio for more information.
//		Action viewAction = Action.newAction(
//				Action.TYPE_VIEW, // TODO: choose an action type.
//				"Notification Page", // TODO: Define a title for the content shown.
//				// TODO: If you have web page content that matches this app activity's content,
//				// make sure this auto-generated web page URL is correct.
//				// Otherwise, set the URL to null.
//				Uri.parse("http://host/path"),
//				// TODO: Make sure this auto-generated app URL is correct.
//				Uri.parse("android-app://com.zhf.sampleapp.ui/http/host/path")
//		);
//		AppIndex.AppIndexApi.end(mClient, viewAction);
//		mClient.disconnect();
//	}
//}