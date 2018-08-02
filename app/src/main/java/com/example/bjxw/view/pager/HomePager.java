package com.example.bjxw.view.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.bjxw.Utils.LogUtil;
import com.example.bjxw.view.fragment.base.BasePager;
public class HomePager extends BasePager {
    public HomePager(Context context) {
        super(context);
    }
    @Override
    public View initView() {
        return super.initView();
    }
    @Override
    public void initData() {
        super.initData();
        LogUtil.e("主页面数据被初始化了");
        //设置标题
        tv_title.setText("主页面");
        //联网请求,得到数据,创建视图
        TextView textView=new TextView(context);
        textView.setText("主页面内容");
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        fl_content.addView(textView);
    }
}