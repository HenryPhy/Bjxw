package com.example.bjxw.view.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

//自定义不能滑动的ViewPager
public class NoScrollViewPager extends ViewPager {
    public NoScrollViewPager(Context context){
        super(context);
    }
    //在布局文件中使用该类的时候,实例化该类用该构造方法.这个方法不能少,少了的话会崩溃
    public NoScrollViewPager(Context context, AttributeSet atts){
        super(context,atts);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
