package netty;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

/**
 * Http简单工具类
 *
 * @author lihongjian
 * @since 2021/1/24
 */
public class OkhttpHttpUtils {
    /**
     * post请求 form表单
     * @param params 参数
     * @param url 请求地址
     * @return 响应体
     */
    public static Response sendPost(String url , Map<String, String> params){
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : params.keySet()) {
            builder.add(key , params.get(key));
        }
        Response response = null;
        try {
            Request request = new Request.Builder().url("http://localhost:8088/api/hello").post(builder.build()).build();
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * get请求
     * @param url 请求地址，参数自己拼装好
     * @return 响应体
     */
    public static Response sendGet(String url){
        OkHttpClient okHttpClient = new OkHttpClient();
        Response response = null;
        try {
            Request request = new Request.Builder().url(url).get().build();
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


}
