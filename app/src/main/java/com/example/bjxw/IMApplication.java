package com.example.bjxw;
import android.app.Application;
import android.content.Context;
public class IMApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化全局上下文对象
        mContext = this;
    }
    // 获取全局上下文对象
    public static Context getGlobalApplication(){
        return mContext;
    }
}