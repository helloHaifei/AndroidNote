package util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.zhf.common.BaseApplication;

import java.io.File;
import java.util.List;

/***
 * 获取application context、数据库中图片地址、获取资源、apk是否安装、获取app版本信息。
 */
public class ContextUtil {

    /**
     * 需要实现类似MyApplication.getInstance()，根据实际继承的Application子类，返回合适的对象。
     * @return Application Context
     */
    public static Context getApplicationContext() {
        // return MyApplication.getInstance();
        return BaseApplication.getMyApplication();
//        throw new NullPointerException();
    }

    /**
     * 把系统路径转换成物理路径
     * @param activity
     * @param contentUri
     */
    public static String getRealPathFromURI(Activity activity, Uri contentUri) {
        if ((contentUri.getScheme().equals("file")))
            return contentUri.getPath();
        // can post image
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = activity.getContentResolver().query(contentUri, proj, // Which
                // columns
                // to
                // return
                null, // WHERE clause; which rows to return (all rows)
                null, // WHERE clause selection arguments (none)
                null); // Order-by clause (ascending by name)
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String uri = cursor.getString(column_index);
        try {
            // 4.0以上的版本会自动关闭 (4.0--14;; 4.0.3--15)
            if (Build.VERSION.SDK_INT < 14) {
                cursor.close();
            }
        } catch (Exception e) {
            Log.w("",e);
        }
        return uri;
    }

    /**
     * 返回颜色值
     * @param resId R.color.xx
     * */
    public static String getStringByResId(@StringRes int resId){
        return getApplicationContext().getResources().getString(resId);
    }

    public static String getStringByResId(@StringRes int resId, Object... formatArgs){
        return getApplicationContext().getResources().getString(resId, formatArgs);
    }

    /**
     * 返回颜色值
     * @param resId R.color.xx
     * */
    public static int getColorByResId(@ColorRes int resId){
        return getApplicationContext().getResources().getColor(resId);
    }

    /**
     * 返回ColorStateList
     * @param resId R.color.xx
     * */
    public static ColorStateList getColorStateListByResId(@ColorRes int resId){
        return getApplicationContext().getResources().getColorStateList(resId);
    }


    /**
     * 拍照获取图片
     */
    public static Uri doTakePhoto(Activity activity, String dir, int requestCode) {
        try {
            File mCurrentPhotoFile = new File(dir, "club_"
                    + System.currentTimeMillis() + ".jpg");
            Uri originalUri = Uri.fromFile(mCurrentPhotoFile);
            // 获取这个图片的URI
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, originalUri);
            activity.startActivityForResult(intent, requestCode);
            return originalUri;
        } catch (ActivityNotFoundException e) {
            Toast.makeText(activity, "找不到该相片",
                    Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public boolean isRunningForeground() {
        boolean isForeGround = false;
        ActivityManager am = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        if (!TextUtils.isEmpty(currentPackageName)
                && currentPackageName.equals(getApplicationContext().getPackageName())) {
            isForeGround = true;
        }
        return isForeGround;
    }

    /**
     * 安装应用
     *
     * @param context
     * @param uri
     */
    public static void install(Context context, Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 启动App，如果已经安装，启动App
     *
     * @param packageName
     * @return
     */
    public static boolean launchApp(String packageName,
                                    String versionName) {
        Context context = getApplicationContext();
        if (isInstalled(packageName, versionName)) {
            try {
                Intent intent = context.getPackageManager()
                        .getLaunchIntentForPackage(packageName);
                if (intent != null) {
                    context.startActivity(intent);
                    return true;
                }
            } catch (Exception e) {
                Log.e("SysUtil", e.toString());
            }
        }
        return false;
    }

    /**
     * 是否安装此应用，校验版本号
     *
     * @param packageName
     * @param versionName
     * @return
     */
    public static boolean isInstalled(String packageName,
                                      String versionName) {
        Context context = getApplicationContext();
        if (!TextUtils.isEmpty(versionName)) {
            List<PackageInfo> appList = context.getPackageManager()
                    .getInstalledPackages(0);
            for (PackageInfo appInfo : appList) {
                if (appInfo.packageName.equals(packageName)
                        && appInfo.versionName.equals(versionName)) {
                    return true;
                }
            }
            return false;
        }
        return isInstalled(packageName);
    }

    /**
     * 是否安装此应用
     *
     * @param packageName
     * @return
     */
    private static boolean isInstalled(String packageName) {
        if (!TextUtils.isEmpty(packageName)) {
            List<PackageInfo> appList = getApplicationContext().getPackageManager()
                    .getInstalledPackages(0);
            for (PackageInfo appInfo : appList) {
                if (appInfo.packageName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**return app version name defined in manifest. */
    public static String getVersionName(){
        try {
            Context context = getApplicationContext();
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**return app version code defined in manifest. */
    public static int getVersionCode(){
        try {
            Context context = getApplicationContext();
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /*
    public static String getAssetFileStr(String fileName) {
        Context context = getApplicationContext();
        String cacheStr = null;
        AssetManager assetManager = context.getAssets();

        try {
            InputStream inputStream = assetManager.open(fileName);
            //获取文件的字节数
            int lenght = inputStream.available();
            //创建byte数组
            byte[] buffer = new byte[lenght];
            //将文件中的数据读到byte数组中
            inputStream.read(buffer);
            cacheStr = EncodingUtils.getString(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cacheStr;
    }
    */
}
