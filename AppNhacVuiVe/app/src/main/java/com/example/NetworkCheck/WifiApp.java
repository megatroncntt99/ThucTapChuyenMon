package com.example.NetworkCheck;

import android.app.Application;
/**
 * Created by http://giasutinhoc.vn
 */
public class WifiApp extends Application {
    static WifiApp wifiInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        wifiInstance = this;
    }
    public static synchronized WifiApp getInstance() {
        return wifiInstance;
    }
}