package com.example.bjxw.view.fragment.base;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bjxw.R;
import com.example.bjxw.view.activity.MainActivity;

public class BasePager {
    //上下文
    public final Context context;//MainActivity
    //视图,代表各个不同的页面
    public View rootView;
    //点击侧滑
    public ImageButton ib_menu;
    //显示标题
    public TextView tv_title;
    //加载的各个子页面
    public FrameLayout fl_content;

    public ImageButton ib_swich_list_grid;

    public Button btn_cart;
    public BasePager(Context context){
        this.context=context;
        rootView=initView();
    }
    //用于初始化公共部分视图,并且初始化加载子视图FragmentLayout


    public View initView(){
        View view=View.inflate(context, R.layout.base_pager,null);
        tv_title=view.findViewById(R.id.tv_title);
        ib_swich_list_grid=view.findViewById(R.id.ib_swich_list_grid);
        ib_menu=view.findViewById(R.id.ib_menu);
        fl_content=view.findViewById(R.id.fl_content);
        btn_cart=view.findViewById(R.id.btn_cart);
        ib_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity=(MainActivity)context;
                mainActivity.getSlidingMenu().toggle();//关<-->开
            }
        });
        return view;
    }
    //初始化数据;当孩子需要资源初始化数据,或者绑定数据,联网请求数据并绑定,重写该方法
    public void initData(){}
}