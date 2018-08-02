package com.example.bjxw.view.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.example.bjxw.Utils.LogUtil;
import com.example.bjxw.view.fragment.base.BasePager;

public class SettingPager extends BasePager {
    public SettingPager(Context context) {
        super(context);
    }
    @Override
    public void initData() {
        super.initData();
        LogUtil.e("设置中心数据被初始化了");
        //设置标题
        tv_title.setText("设置中心");
        TextView tv=new TextView(context);
        tv.setText("设置中心内容");
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.RED);
        tv.setTextSize(25);
        fl_content.addView(tv);

    }
}
