package com.xiaofan.retrofitrxjavamvp.http.cookie;

import android.util.Log;

import com.xiaofan.retrofitrxjavamvp.http.service.HttpPostService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * @author: 范建海
 * @createTime: 2017/4/12 18:33
 * @className:  TokenInterceptor
 * @description: 拦截器
 * @changed by:
 */
public class TokenInterceptor implements Interceptor {

    static final String TAG = "fanjianhai";
    public static final String BASE_URL = "";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        Log.e(TAG,"response.code=" + response.code());

        if (isTokenExpired(response)) {//根据和服务端的约定判断token过期
            Log.e(TAG,"静默自动刷新Token,然后重新请求数据");
            //同步请求方式，获取最新的Token
            String newSession = getNewToken();
            //使用新的Token，创建新的请求
            Request newRequest = chain.request()
                    .newBuilder()
                    .header("Cookie", "JSESSIONID=" + newSession)
                    .build();
            //重新请求
            return chain.proceed(newRequest);
        }
        return response;
    }

    /**
     * 根据Response，判断Token是否失效
     *
     * @param response
     * @return
     */
    private boolean isTokenExpired(Response response) {
        if (response.code() == 404) {
            return true;
        }
        return false;
    }

    /**
     * 同步请求方式，获取最新的Token
     *
     * @return
     */
    private String getNewToken() throws IOException {
        // 通过一个特定的接口获取新的token，此处要用到同步的retrofit请求
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new TokenInterceptor());

        /*创建retrofit对象*/
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(BASE_URL)
                .build();
        HttpPostService service = retrofit.create(HttpPostService.class);
        Call<String> call =  service.getToken("18210836561","a123456");
        return call.execute().body();
    }
}
