package first.cc.meishitianxia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private ViewPager vp_fragment_viewpage;
    private LinearLayout linear_shouye, linear_huati, linear_wo,linear_shicai,linear_fenlei;
    private ImageButton img_shouye, img_huati, img_wo,img_shicai,img_fenlei;
    private View one_page, two_page, three_page,four_page,five_page;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragments = new ArrayList<Fragment>();
        InitView();

    }



    private void InitView() {

        // 初始化 tabs
        vp_fragment_viewpage = (ViewPager) findViewById(R.id.vp_fragment_viewpage);
        linear_shouye = (LinearLayout) findViewById(R.id.linear_shouye);
        linear_fenlei=(LinearLayout)findViewById(R.id.linear_fenlei);
        linear_shicai=(LinearLayout)findViewById(R.id.linear_shicai) ;
        linear_huati = (LinearLayout) findViewById(R.id.linear_huati);
        linear_wo = (LinearLayout) findViewById(R.id.linner_wo);

        img_shouye = (ImageButton) findViewById(R.id.img_shouye);
        img_fenlei=(ImageButton)findViewById(R.id.img_fenlei);
        img_shicai=(ImageButton)findViewById(R.id.img_shicai);
        img_huati = (ImageButton) findViewById(R.id.img_huati);
        img_wo = (ImageButton) findViewById(R.id.img_wo);


        // 初始化fragment 数据
        PageShouye pageShouye = new PageShouye();
        fragments.add(pageShouye);
        PageFenlei pageFenlei = new PageFenlei();
        fragments.add(pageFenlei);
        PageShicai pageShicai = new PageShicai();
        fragments.add(pageShicai);
        PageHuati pageHuati = new PageHuati();
        fragments.add(pageHuati);
        PageUser pageUser = new PageUser();
        fragments.add(pageUser);


        /**
         * 这里Activity 只有继承 FragmentActivity 的时候 ，才会 getSupportFragmentManager()
         */

        // 设置适配器
        FragmentViewPageAdapter fragmentViewPageAdapter = new FragmentViewPageAdapter(
                getSupportFragmentManager(), fragments);
        vp_fragment_viewpage.setAdapter(fragmentViewPageAdapter);


        //给tabs 设置点击监听事件
        linear_shouye.setOnClickListener(this);
        linear_fenlei.setOnClickListener(this);
        linear_shicai.setOnClickListener(this);
        linear_huati.setOnClickListener(this);
        linear_wo.setOnClickListener(this);


        //设置ViewPage 切换效果
        vp_fragment_viewpage.setOnPageChangeListener(new vpOnChangeListener());
        vp_fragment_viewpage.setOffscreenPageLimit(5);

    }


    @Override
    public void onClick(View v) {

        ResetTabsImg();
        switch (v.getId()) {
            case R.id.linear_shouye:
                SetTabsSelectedImg(0);
                break;
            case R.id.linear_fenlei:
                SetTabsSelectedImg(1);
                break;
            case R.id.linear_shicai:
                SetTabsSelectedImg(2);
                break;
            case R.id.linear_huati:
                SetTabsSelectedImg(3);
                break;
            case R.id.linner_wo:
                SetTabsSelectedImg(4);
                break;

        }

    }
    private void SetTabsSelectedImg(int i) {
        switch (i) {
            case 0:
                img_shouye.setImageResource(R.drawable.ic_menu_home_on);
                break;
            case 1:
                img_fenlei.setImageResource(R.drawable.ic_menu_fenlei_on);
                break;
            case 2:
                img_shicai.setImageResource(R.drawable.ic_menu_shicai_on);
                break;
            case 3:
                img_huati.setImageResource(R.drawable.ic_menu_talk_on);
                break;
            case 4:
                img_wo.setImageResource(R.drawable.ic_menu_user_on);
                break;
        }
        //切换 viewpage item
        vp_fragment_viewpage.setCurrentItem(i);
    }
    private void ResetTabsImg() {
        // 重置tab 图片

        img_shouye.setImageResource(R.drawable.ic_menu_home_off);
        img_fenlei.setImageResource(R.drawable.ic_menu_fenlei_off);
        img_shicai.setImageResource(R.drawable.ic_menu_shicai_off);
        img_huati.setImageResource(R.drawable.ic_menu_talk_off);
        img_wo.setImageResource(R.drawable.ic_menu_user_off);

    }
    class vpOnChangeListener extends ViewPager.SimpleOnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            //设置 tab 背景
            ResetTabsImg();
            SetTabsSelectedImg(position);
        }
    }
}

