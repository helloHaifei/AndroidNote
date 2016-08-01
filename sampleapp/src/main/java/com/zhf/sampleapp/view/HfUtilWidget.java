package com.zhf.sampleapp.view;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

public class HfUtilWidget {

	private static AlertDialog mAlertDlg;
	private static ProgressDialog mProgressDlg;
	
	public static void showToast(Context context, String message) {
		showToast(context, message, Toast.LENGTH_SHORT);
	}

	public static void showToast(Context context, String message, int duration) {
		
		TextView contentView = new TextView(context);
		contentView.setTextSize(18);
		contentView.setPadding(15, 5, 15, 5);
		contentView.setText(message);
		
		Toast toast = new Toast(context);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setView(contentView);
		toast.setDuration(duration);
		
		toast.show();
	}

	public static void showToast(Context context, int resId) {
		///Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
		showToast(context,context.getString(resId),Toast.LENGTH_SHORT);
	}
	public static void showToast(Context context, int resId, int duration) {
		///Toast.makeText(context, resId, duration).show();
		showToast(context,context.getString(resId),duration);
	}
	
	 
	public static Dialog showProgressDialog(Context context,int res,boolean cancelable){
		return showProgressDialog(context, context.getString(res),cancelable);
	}
	public static Dialog showProgressDialog(Context context,String message,boolean cancelable){
		if(context == null || ( context instanceof Activity && ((Activity) context).isFinishing())){
			return null;
		}
			
		if(mProgressDlg !=null){
			mProgressDlg.cancel(); //防止执行两次showProgressDialog()
			mProgressDlg = null;
		}
		mProgressDlg = new ProgressDialog(context); //每次用时确保是新的
		
		mProgressDlg.setMessage(message);
		mProgressDlg.setCancelable(cancelable);
		mProgressDlg.show();
		return mProgressDlg;
		
		
	} 
	
	public static void updateProgressDlg(String message){
		if(mProgressDlg != null){
			mProgressDlg.setMessage(message);
		}
	}
	
	
	public static void cancelProgressDialog(){
		if(mProgressDlg!=null){
			mProgressDlg.cancel();
			mProgressDlg = null;
		}
	}
	public static Dialog showAlertDialog(Context context, int messageRes) {
		return showAlertDialog(context, context.getString(messageRes));
	}
	public static Dialog showAlertDialog(Context context, String message) {
		
		mAlertDlg = new AlertDialog.Builder(context)
				.setTitle("提示")
				
				.setMessage(message)
				.setPositiveButton("确定", new Dialog.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						mAlertDlg.cancel();
					}
				})
				.create();
		mAlertDlg.setIcon(android.R.drawable.ic_dialog_alert);
		mAlertDlg.show();

		return mAlertDlg;
	}
	
	public static void cancelAlertDialog(){
		if(mAlertDlg!=null){
			mAlertDlg.cancel();
			mAlertDlg = null;
		}
	}
	
	
}
