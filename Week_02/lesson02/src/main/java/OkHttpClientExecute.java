import okhttp3.*;
import okhttp3.Request.Builder;

import java.io.IOException;

/**
 * 使用OKHttp访问地址
 *
 * @author lihongjian
 * @since 2021/1/20
 */
public class OkHttpClientExecute {


    public static void main(String[] args) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("arg1" , "参数1");
            Request request = new Builder().url("http://localhost:8088/api/hello").get().build();
            Response response = okHttpClient.newCall(request).execute();
            if(response.code() == 200){
                System.out.println(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
