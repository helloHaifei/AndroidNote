package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * Json解析工具类
 *
 * Create by haiFei on 2016/2/2
 */
public class GsonUtil {

    /**
     * json解析成单个实体
     * 使用：parseJson(jsonStr, UserModel.class);
     * @param json
     * @param cls
     * @param <T>
     * @return  返回实体类型
     * @throws JsonSyntaxException
     */
    public static <T> T parseJson(String json, Class<T> cls) throws JsonSyntaxException{
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().fromJson(json, cls);
    }

    /**
     * json解析成单个实体
     * 使用：parseJson(jsonStr,new TypeToken<UserModel>(){}.getType() );
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T parseJson(String json, Type type) throws JsonSyntaxException {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().fromJson(json, type);

    }

    public static <T> T parseJson(String json) throws JsonSyntaxException {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().fromJson(json, new TypeToken<T>(){}.getType());

    }

    /**
     * json解析数组
     * 使用: parseJsonArray(jsonStr, UserModel[].class);
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> List<T> parseJsonArray(String json, Class<T[]> type) throws JsonSyntaxException {
        T[] list = new Gson().fromJson(json, type);
        return Arrays.asList(list);
    }

    /**
     *
     * json解析数组
     *
     * 使用：parseJsonArray(jsonStr,new TypeToken<List<UserModel>>(){}.getType() );
     *
     * @param jsonString
     * @param type
     * @param <T>
     * @return
     */
    public static <T> List<T> parseJsonArray(String jsonString, Type type) throws JsonSyntaxException {

        return new Gson().fromJson(jsonString,type);
    };

    /**
     * json反解析，将bean对象转成字符串
     */
    public static String parseObjectToJson(Object src) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        try {
            gsonBuilder.excludeFieldsWithModifiers(Modifier.TRANSIENT);
            return gsonBuilder.create().toJson(src);
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    public static void put(JSONObject json, String key, Object value){
        try {
            if (value == null){
                json.put(key, "");
            } else {
                json.put(key, value);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
