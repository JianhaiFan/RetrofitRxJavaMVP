package com.xiaofan.retrofitrxjavamvp.application;

import android.app.Application;
import android.content.Context;

/**
 * @author: 范建海
 * @createTime: 2017/3/26 19:08
 * @className:  App
 * @description:
 * @changed by:
 */
public class App extends Application {
    public static Context app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = getApplicationContext();

    }
}