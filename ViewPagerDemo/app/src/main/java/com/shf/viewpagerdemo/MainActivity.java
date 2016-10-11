package com.shf.viewpagerdemo;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private TextView tvOne;
    private TextView tvTwo;
    private TextView tvThree;
    private ImageView imgCursor;
    private ViewPager viewPager;
    private ArrayList<View> listViews;
    private int screenWidth;//手机屏幕分辨率宽度
    private int offset;//移动条图片的偏移量
    private int one;//移动条滑动一页的距离
    private int two;//移动条滑动两页的距离
    private int imgWidth;// 移动条图片的长度
    private int currentIndex = 0;//记录当前页面的编号


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        initData();
    }
    private void bindViews() {
        tvOne = (TextView)findViewById(R.id.tv_one);
        tvTwo = (TextView)findViewById(R.id.tv_two);
        tvThree = (TextView)findViewById(R.id.tv_three);
        imgCursor = (ImageView)findViewById(R.id.img_cursor);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
    }

    private void initData() {
        //获取下划线图片宽度
        imgWidth = BitmapFactory.decodeResource(getResources(), R.mipmap.line).getWidth();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //手机屏幕分辨率宽度
        screenWidth = dm.widthPixels;
        //计算偏移量
        offset = ((screenWidth / 3) - imgWidth) / 2;
        //移动的距离
        // 移动一页的偏移量,比如1->2,或者2->3
        one = offset * 2 + imgWidth;
        //计算两页的偏移量，比如1→3
        two = one * 2;
        //往ViewPager填充View，同时设置点击事件与页面切换事件
        listViews = new ArrayList<View>();
        listViews.add(getLayoutInflater().inflate(R.layout.view_one,null,false));
        listViews.add(getLayoutInflater().inflate(R.layout.view_two,null,false));
        listViews.add(getLayoutInflater().inflate(R.layout.view_three,null,false));
        MyPagerAdapter mAdapter = new MyPagerAdapter(listViews);
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(0);//设置当前页，从0页开始
        tvOne.setOnClickListener(this);
        tvTwo.setOnClickListener(this);
        tvThree.setOnClickListener(this);
        viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_one:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv_two:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tv_three:
                viewPager.setCurrentItem(2);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Animation animation = null;
        switch (position){
            case 0:
                if (currentIndex == 1){
                    animation = new TranslateAnimation(one,0,0,0);
                }else if (currentIndex ==2){
                    animation = new TranslateAnimation(two,0,0,0);
                }
                break;
            case 1:
                if (currentIndex == 0){
                    animation = new TranslateAnimation(offset,one,0,0);
                }else if (currentIndex ==2){
                    animation = new TranslateAnimation(two,one,0,0);
                }
                break;
            case 2:
                if (currentIndex == 0){
                    animation = new TranslateAnimation(offset,two,0,0);
                }else if (currentIndex == 1){
                    animation = new TranslateAnimation(one,two,0,0);
                }
                break;
        }
        currentIndex = position;
        animation.setFillAfter(true);
        animation.setDuration(300);
        imgCursor.startAnimation(animation);//开始动画
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class MyPagerAdapter extends PagerAdapter{
        private ArrayList<View> listViews;
        public MyPagerAdapter(){

        }
        public MyPagerAdapter(ArrayList<View> listViews){
            this.listViews = listViews;
        }

        @Override
        public int getCount() {
            return listViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(listViews.get(position));
            return listViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(listViews.get(position));
        }
    }
}
