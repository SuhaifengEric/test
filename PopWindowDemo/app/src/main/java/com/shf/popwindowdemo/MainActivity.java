package com.shf.popwindowdemo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btOpen;
    private View view;

    //1.定义不同颜色的菜单项的标识:
    final private int RED = 110;
    final private int GREEN = 111;
    final private int BLUE = 112;
    final private int YELLOW = 113;
    final private int GRAY= 114;
    final private int CYAN= 115;
    final private int BLACK= 116;
    private TextView tvChangeColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btOpen = (Button)findViewById(R.id.bt_open);
        tvChangeColor = (TextView)findViewById(R.id.tv_change_color);
        registerForContextMenu(tvChangeColor);//注册上下文菜单
        btOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow(v);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.blue:
                tvChangeColor.setTextColor(Color.BLUE);
                break;
            case R.id.green:
                tvChangeColor.setTextColor(Color.GREEN);
                break;
            case R.id.red:
                tvChangeColor.setTextColor(Color.RED);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu,menu);
        menu.add(1,RED,0,"红色");
        menu.add(1,GREEN,0,"绿色");
        menu.add(1,BLUE,0,"蓝色");
        menu.add(1,YELLOW,0,"黄色");
        menu.add(1,GRAY,0,"灰色");
        menu.add(1,CYAN,0,"蓝绿色");
        menu.add(1,BLACK,0,"黑色");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case RED:
                btOpen.setTextColor(Color.RED);
                break;
            case GREEN:
                btOpen.setTextColor(Color.GREEN);
                break;
            case BLUE:
                btOpen.setTextColor(Color.BLUE);
                break;
            case YELLOW:
                btOpen.setTextColor(Color.YELLOW);
                break;
            case GRAY:
                btOpen.setTextColor(Color.GRAY);
                break;
            case CYAN:
                btOpen.setTextColor(Color.CYAN);
                break;
            case BLACK:
                btOpen.setTextColor(Color.BLACK);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initPopupWindow(View v) {
        view = View.inflate(MainActivity.this, R.layout.item_pop, null);
        Button btn_xixi = (Button) view.findViewById(R.id.btn_xixi);
        Button btn_hehe = (Button) view.findViewById(R.id.btn_hehe);
        PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setAnimationStyle(R.anim.anim_pop);//设置动画
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));//一定要设置background才有效
        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
        //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popupWindow.showAsDropDown(v, 50, 100);
        btn_hehe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "你点击了呵呵~", Toast.LENGTH_SHORT).show();
            }
        });
        btn_xixi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "你点击了嘻嘻~", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
