package com.example.bjxw.view.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.bjxw.R;
import com.example.bjxw.Utils.DensityUtil;
import com.example.bjxw.Utils.SpUtils;

import java.util.ArrayList;

//引导页
public class GuideActivity extends Activity {


    private ViewPager viewPager;
    private Button btn_start_main;
    private LinearLayout ll_point_group;
    private ImageView iv_red_point;
    private  int widthDpi;
    private int leftmax;

    private ArrayList<ImageView>  imageViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        //数据
        int[] ids=new int[]{R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};

        widthDpi= DensityUtil.dip2px(this,10);

        imageViews=new ArrayList<>();
        for (int i=0;i<ids.length;i++){
            ImageView imageView=new ImageView(this);
            //设置一下背景
            imageView.setBackgroundResource(ids[i]);
            //添加到集合中
            imageViews.add(imageView);

            //创建点
            ImageView point=new ImageView(this);
            point.setBackgroundResource(R.drawable.point_normal);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(widthDpi,widthDpi);
            if (i!=0){
                //不包括第0个,所有的点的距离左边有10个像素
                params.leftMargin=widthDpi;
            }
            point.setLayoutParams(params);
            //添加到线性布局上
            ll_point_group.addView(point);
        }
        viewPager.setAdapter(new MyPagerAdapter());
        //根据view的生命周期,当时图执行到onLayout或者onDraw的时候,视图的高度和边距都有了
        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutLintener());
        //得到屏幕滑动的百分比
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
        btn_start_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1.保存曾经进入过主页面
                SpUtils.getInstance().save("start_main",true);
                //2跳转到主页面
                Intent in=new Intent(GuideActivity.this,MainActivity.class);
                startActivity(in);
                //3.关闭我们的引导页
                finish();

            }
        });

    }
    class  MyOnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //两个点滑动距离对应的坐标=屏幕的百分比*间距
            int leftmargin=(int)(position*leftmax+(positionOffset*leftmax));
            RelativeLayout.LayoutParams  params=(RelativeLayout.LayoutParams)iv_red_point.getLayoutParams();
            params.leftMargin=leftmargin;
            iv_red_point.setLayoutParams(params);
        }

        @Override
        public void onPageSelected(int position) {
            if (position==imageViews.size()-1){
                //最后一个页面
                btn_start_main.setVisibility(View.VISIBLE);
            }else{
                //其他页面
                btn_start_main.setVisibility(View.GONE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    class MyOnGlobalLayoutLintener implements ViewTreeObserver.OnGlobalLayoutListener{

        @SuppressLint("NewApi")
        @Override
        public void onGlobalLayout() {
            //只执行一次
            iv_red_point.getViewTreeObserver().removeOnGlobalLayoutListener(MyOnGlobalLayoutLintener.this);
            //间距=第一个点距离左边点的距离-第0个点距离左边点的距离
            leftmax=ll_point_group.getChildAt(1).getLeft()-ll_point_group.getChildAt(0).getLeft();
        }
    }

    private void initView() {
        viewPager=findViewById(R.id.viewPager);
        btn_start_main=findViewById(R.id.btn_start_main);
        ll_point_group=findViewById(R.id.ll_point_group);
        iv_red_point=findViewById(R.id.iv_red_point);
    }



    class MyPagerAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView im=imageViews.get(position);
            container.addView(im);
            return im;

        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
