package com.example.bjxw.view.fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;
import com.example.bjxw.R;
import com.example.bjxw.view.activity.MainActivity;
import com.example.bjxw.view.adapter.ContentFragmentAdapter;
import com.example.bjxw.view.fragment.base.BaseFragment;
import com.example.bjxw.view.fragment.base.BasePager;
import com.example.bjxw.view.pager.GovaffairPager;
import com.example.bjxw.view.pager.HomePager;
import com.example.bjxw.view.pager.NewsCenterPager;
import com.example.bjxw.view.pager.SettingPager;
import com.example.bjxw.view.pager.SmartServicePager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
import java.util.ArrayList;
public class ContentFragment extends BaseFragment {
    //初始化控件
@ViewInject(R.id.viewpager)
    private ViewPager viewpager;
@ViewInject(R.id.rg_main)
    private RadioGroup rg_main;
    //装5个页面的集合
    private ArrayList<BasePager> basePagers;
    @Override
    public View initView() {
        View view=View.inflate(context, R.layout.content_fragment,null);
        //1.把视图注入框架,让ContentFragment.this和View关联起来
        x.view().inject(ContentFragment.this,view);
        return view;
    }
    @Override
    public void initData() {
        super.initData();
        //初始化5个页面 并且放入集合中
        basePagers=new ArrayList<>();
        basePagers.add(new HomePager(context));//主页面
        basePagers.add(new NewsCenterPager(context));//新闻中心
        basePagers.add(new SmartServicePager(context));//商城
        basePagers.add(new GovaffairPager(context));//购物车
        basePagers.add(new SettingPager(context));//设置
        //设置适配器
        viewpager.setAdapter(new ContentFragmentAdapter(basePagers));

        //设置RadioGroup的点击事件
        rg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());

        //监听某个页面被选中,初始化对应页面的数据
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());
        //设置默认选中的首页
        rg_main.check(R.id.rb_home);
        basePagers.get(0).initData();

        //设置模式  SlidingMenu不可以滑动
        isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
    }
    //得到新闻中心的页面
    public NewsCenterPager getNewsCenterPager(){return  (NewsCenterPager)basePagers.get(1);}

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
        @Override
        public void onPageSelected(int position) {
            //调用被选中页面initData的方法
            basePagers.get(position).initData();
        }
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    class MyOnCheckedChangeListener implements  RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rb_home://主页面RadioButton的id
                    viewpager.setCurrentItem(0,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_newscenter://新闻中心RadioButton的id
                    viewpager.setCurrentItem(1,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_FULLSCREEN);
                    break;
                case R.id.rb_smartservice://商城RadioButton的id
                    viewpager.setCurrentItem(2,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_govaffair://购物车RadioButton的id
                    viewpager.setCurrentItem(3,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_setting://设置RadioButton的id
                    viewpager.setCurrentItem(4,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
            }
        }
    }
    //设置模式  SlidingMenu不可以滑动
    private void isEnableSlidingMenu(int touchmodeFullScreen) {
        MainActivity main=(MainActivity)context;
        main.getSlidingMenu().setTouchModeAbove(touchmodeFullScreen);
    }
}
