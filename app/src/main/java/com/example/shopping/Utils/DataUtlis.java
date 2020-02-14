package com.example.shopping.Utils;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class DataUtlis {

    private static String json;

    public static String getBean(Context con){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new  Request.Builder()
                .get()
                .url("https://cdwan.cn/api/index/index")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull okhttp3.Call call, @NotNull Response response) throws IOException {
                json = response.body().string();
            }

            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {

            }
        });
        return json;
    }
}
