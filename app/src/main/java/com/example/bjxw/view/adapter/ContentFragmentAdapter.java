package com.example.bjxw.view.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.bjxw.view.fragment.base.BasePager;

import java.util.ArrayList;

public class ContentFragmentAdapter extends PagerAdapter {
    private final ArrayList<BasePager> basePagers;
    public ContentFragmentAdapter(ArrayList<BasePager> basePagers){
        this.basePagers=basePagers;
    }
    @Override
    public int getCount() {
        return basePagers.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        BasePager basePager=basePagers.get(position);
        View rootView=basePager.rootView;
        container.addView(rootView);
        return rootView;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
