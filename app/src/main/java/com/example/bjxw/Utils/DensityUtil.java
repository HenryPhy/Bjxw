package com.example.bjxw.Utils;

import android.content.Context;

//px和dp的相互转换工具
public class DensityUtil {
    //根据手机的分辨率 从dip的单位,转化为px(像素)
    public static  int dip2px(Context context,float dpValue){
        final  float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);

    }

    //根据手机的分辨率 从px(像素)的单位转化成dp
    public static  int px2dip(Context context,float pxValue){
        final  float scale=context.getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale+0.5f);






    }
}
