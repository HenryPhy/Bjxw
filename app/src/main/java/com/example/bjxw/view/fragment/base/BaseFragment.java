package com.example.bjxw.view.fragment.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
    public Activity context;//mainActivity

    //当Fragment被创建的时候回调的方法

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();
    }
    //当视图被创建的时候回调的方法

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    //让自己孩子实现自己的视图,达到自己特有的效果
    public abstract View initView();

    //当Activity被创建时回调

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    //1.如果自页面没有数据,联网请求数据,并且绑定到initView初始化视图上
    //2.绑定到initView初始化视图上
    public  void initData(){

    }
}
