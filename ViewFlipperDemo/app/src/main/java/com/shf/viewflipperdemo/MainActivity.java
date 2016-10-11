package com.shf.viewflipperdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    public Context mContext;
    private int[] resId = {R.mipmap.ic_help_view_1,R.mipmap.ic_help_view_2,R.mipmap.ic_help_view_3,R.mipmap.ic_help_view_4};//轮播图片资源数组
    private ViewFlipper viewFlipper;

    private GestureDetector mDetector;
    private MyGestureListener mListener;
    private static final int MIN_MOVE = 200;//最小距离

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        initData();
    }

    private void initData() {
        //动态往viewFlipper里添加view对象
        for (int i =0; i< resId.length ; i++){
            viewFlipper.addView(getImageView(resId[i]));
        }
        //实例化SimpleOnGestureListener与GestureDetector对象
        mListener = new MyGestureListener();
        mDetector = new GestureDetector(this, mListener);
    }
    //返回轮播图的view对象
    private ImageView getImageView(int resId){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(resId);
        return imageView;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getX() > MIN_MOVE){
                viewFlipper.setInAnimation(mContext,R.anim.right_in);//图片从右边进来
                viewFlipper.setOutAnimation(mContext,R.anim.right_out);
                viewFlipper.showNext();//展示下一张
            }else if (e2.getX() - e1.getX() > MIN_MOVE){
                viewFlipper.setInAnimation(mContext,R.anim.left_in);
                viewFlipper.setOutAnimation(mContext,R.anim.left_out);
                viewFlipper.showPrevious();//展示上一张
            }
            return true;
        }
    }
}
