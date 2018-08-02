package com.example.bjxw.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.Window;

import com.example.bjxw.R;
import com.example.bjxw.view.fragment.ContentFragment;
import com.example.bjxw.view.fragment.LeftmenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


public class MainActivity extends SlidingFragmentActivity {
    private int srceeWinth;
    private int screeHeight;
    public static final String LEFTMENU_TAG="leftmenu_tag";
    public static final String MAIN_CONTENT_TAG="main_content_tag";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //设置没有标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        initSlindingFragment();
        //初始化Fragment
        initFragment();
    }

    private void initFragment() {
        //得到FragmentMananger
        FragmentManager fm=getSupportFragmentManager();
        //开启事务
        FragmentTransaction ft=fm.beginTransaction();
        //替换
        ft.replace(R.id.fl_main_content,new ContentFragment(),MAIN_CONTENT_TAG);//主页
        ft.replace(R.id.fl_leftmenu,new LeftmenuFragment(),LEFTMENU_TAG);//左侧菜单
        //提交
        ft.commit();

    }

    private void initSlindingFragment() {
        //1.设置主页面
        setContentView(R.layout.activity_main);
        //2.设置左侧菜单
        setBehindContentView(R.layout.activity_leftmenu);
        //3.设置右侧菜单
        SlidingMenu slidingMenu=getSlidingMenu();
//        slidingMenu.setSecondaryMenu();
        //4.设置显示模式:左菜单+主页 ,左侧菜单+主页+右侧菜单,主页+右侧菜单
        slidingMenu.setMode(SlidingMenu.LEFT);
        //5设置滑动模式:滑动边缘,全屏滑动,不可以滑动
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);


        DisplayMetrics outmetrucs=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outmetrucs);

        srceeWinth=outmetrucs.widthPixels;
        screeHeight=outmetrucs.heightPixels;
        //6设置占据主屏幕的宽度
        slidingMenu.setBehindOffset((int)(srceeWinth*0.625));
    }
    //得到左边菜单的Fragment
    public LeftmenuFragment getLeftmenuFragment(){
        return  (LeftmenuFragment)getSupportFragmentManager().findFragmentByTag(LEFTMENU_TAG);
    }
    //得到正文的Fragment
    public ContentFragment getContentFragment(){
        return  (ContentFragment)getSupportFragmentManager().findFragmentByTag(MAIN_CONTENT_TAG);
    }
}
