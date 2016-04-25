package util;

import android.widget.Toast;


/**
 */
public class ToastUtil {
    public static void showSystemToast(String message){
        Toast.makeText(ContextUtil.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    public static void showErrorToast(String message){
        //Toast.makeText(ContextUtil.getApplicationContext(), TextUtils.isEmpty(message) ? ContextUtil.getStringByResId(R.string.server_error_msg) : message, Toast.LENGTH_SHORT).show();
    }

}
