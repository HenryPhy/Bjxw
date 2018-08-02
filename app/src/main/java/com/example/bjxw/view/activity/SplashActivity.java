package com.example.bjxw.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.example.bjxw.R;
import com.example.bjxw.Utils.SpUtils;

//加载页
public class SplashActivity extends Activity {
    private RelativeLayout rl_splashs_root;
    public static final  boolean START_MAIN=false;//没有进入   true已经进入
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rl_splashs_root=findViewById(R.id.rl_splashs_root);




        //渐变  缩放  旋转
        AlphaAnimation  aa=new AlphaAnimation(0,1);
//        aa.setDuration(500);
        aa.setFillAfter(true);

        ScaleAnimation  sa=new ScaleAnimation(0,1,0,1,ScaleAnimation.RELATIVE_TO_SELF,0.5f,ScaleAnimation.RELATIVE_TO_SELF,0.5f);
//        sa.setDuration(500);
        sa.setFillAfter(true);
        RotateAnimation ra=new RotateAnimation(0,360,ScaleAnimation.RELATIVE_TO_SELF,0.5f,ScaleAnimation.RELATIVE_TO_SELF,0.5f);
//        ra.setDuration(500);
        ra.setFillAfter(true);

        AnimationSet  set=new AnimationSet(false);
        set.addAnimation(ra);
        set.addAnimation(sa);
        set.addAnimation(aa);
        set.setDuration(2000);

        rl_splashs_root.startAnimation(set);
        set.setAnimationListener(new MyAnimationListener());

    }
    class MyAnimationListener implements Animation.AnimationListener{


        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
                //判断是否进入过主页面
            boolean isStartMain= SpUtils.getInstance().getBoolean("start_main",false);
            Intent intent;
            if (isStartMain){
                //跳转到主页面
                intent=new Intent(SplashActivity.this,MainActivity.class);
                SpUtils.getInstance().save("start_main",true);
            }else{
                //跳转到引导页
                intent=new Intent(SplashActivity.this,GuideActivity.class);
            }
            startActivity(intent);
            //结束掉
            finish();

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
