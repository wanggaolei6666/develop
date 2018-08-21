package com.color.util;

import com.color.bean.RequestResult;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wanggaolei on 2018/8/21.
 */

public class OkHttpManager {
    private OkHttpClient okHttpClient;
    private static OkHttpManager okHttpManager;
    public static OkHttpManager getInstance() {
        if (okHttpManager == null) {
            okHttpManager = new OkHttpManager();
        }
        return okHttpManager;
    }

    private OkHttpManager() {
        okHttpClient = new OkHttpClient();
    }

    public void requestMehtod(String url, final RequestResult requestResult){
        final Request request =new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                requestResult.requestFailed(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                requestResult.requestSuccess(response.body().string());
            }
        });
    }
}
