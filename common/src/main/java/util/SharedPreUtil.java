package util;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * 存在这里的value类型默认为String 如果为其他的必须标明类型
 */
public class SharedPreUtil {

    private final static String USERDATA = "userdata";
    private final static String USERDATA_UUID = "userdata_uuid";
    private static final String KEYBOARD_HEIGHT = "keyboard_height";
    private static final String KEY_TIMESTAMP = "key_timestamp";

    public final static String LOCATION_CITY="Location_City";//保存启动时调用定位返回的地址
    public final static String LOCATION_INFO="Location_Info";//保存启动时调用定位返回的信息
    public final static String CHANNEL_NAME="Channel_Name";//渠道名称

    public final static String GENERATE_SERIAL_NUMBER="Generate_Serial_Number";//没有权限时获取的唯一标识



    public static void saveUUID(Context context, String uuid){
        if(uuid == null){
            return;
        }

        SharedPreferences sp = context.getSharedPreferences(USERDATA, Context.MODE_PRIVATE | Context.MODE_APPEND);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(USERDATA_UUID, uuid);
        editor.commit();
    }

    public static String getUUID(Context context){
        SharedPreferences sp = context.getSharedPreferences(USERDATA, Context.MODE_PRIVATE | Context.MODE_APPEND);
        String uuid = sp.getString(USERDATA_UUID, "");
        return uuid;
    }

    /**
     * 保存键盘高度
     */
    public static void saveKeyboardHeight(Context context, int height){
        SharedPreferences sp = context.getSharedPreferences(USERDATA, Context.MODE_APPEND);
        sp.edit().putInt(KEYBOARD_HEIGHT, height).commit();
    }

    /**
     * 获取键盘高度
     */
    public static int getKeyboardHeight(Context context){
        SharedPreferences sp = context.getSharedPreferences(USERDATA, Context.MODE_APPEND);
        return sp.getInt(KEYBOARD_HEIGHT, 0);
    }

    /**
     *
     * 保存服务器返回时间戳
     *
     * @param timestamp 10位数
     */
    public static void setTimestamp( int timestamp){
        int difference = (int)(timestamp - System.currentTimeMillis()/1000 );

        SharedPreferences sp = ContextUtil.getApplicationContext().getSharedPreferences(USERDATA, Context.MODE_APPEND);
        sp.edit().putInt(KEY_TIMESTAMP, difference).commit();
    }

    /**
     * 获取服务器系统级别时间戳
     */
    public static int getTimestamp(){
        SharedPreferences sp = ContextUtil.getApplicationContext().getSharedPreferences(USERDATA, Context.MODE_APPEND);
        int timestamp = (int)(System.currentTimeMillis()/1000 + sp.getInt(KEY_TIMESTAMP, 0));
        return timestamp;
    }

    public static void putString(String key,String value){
        SharedPreferences sp = ContextUtil.getApplicationContext().getSharedPreferences(USERDATA, Context.MODE_PRIVATE | Context.MODE_APPEND);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public static String getString(String key){
         return getString(key, "");
    }
    public static String getString(String key,String defaultValue){
        SharedPreferences sp = ContextUtil.getApplicationContext().getSharedPreferences(USERDATA, Context.MODE_PRIVATE | Context.MODE_APPEND);
        SharedPreferences.Editor editor = sp.edit();

        return sp.getString(key, defaultValue);
    }

    public static void putInt(String key,int value){
        SharedPreferences sp = ContextUtil.getApplicationContext().getSharedPreferences(USERDATA, Context.MODE_PRIVATE | Context.MODE_APPEND);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }
    public static int getInt(String key){
        return getInt(key, -1);
    }

    public static int getInt(String key,int defaultValue){
        SharedPreferences sp = ContextUtil.getApplicationContext().getSharedPreferences(USERDATA, Context.MODE_PRIVATE | Context.MODE_APPEND);

        return sp.getInt(key, defaultValue);
    }

    public static void putBoolean(String key,boolean value){
        SharedPreferences sp = ContextUtil.getApplicationContext().getSharedPreferences(USERDATA, Context.MODE_PRIVATE | Context.MODE_APPEND);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getBoolean(String key,boolean defaultValue){
        SharedPreferences sp = ContextUtil.getApplicationContext().getSharedPreferences(USERDATA, Context.MODE_PRIVATE | Context.MODE_APPEND);
        return sp.getBoolean(key, defaultValue);
    }
    public static boolean getBoolean(String key){
        return getBoolean(key, false);
    }

}
