package com.shf.alertdialogdemo;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
    public Button bt_dialog1;
    public Button bt_dialog2;
    public Button bt_dialog3;
    public Button bt_dialog4;

    private Context mContext;
    private AlertDialog alertDialog = null;
    private AlertDialog.Builder builder = null;
    private boolean[] checkItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        bt_dialog1 = (Button) findViewById(R.id.bt_dialog1);
        bt_dialog2 = (Button) findViewById(R.id.bt_dialog2);
        bt_dialog3 = (Button) findViewById(R.id.bt_dialog3);
        bt_dialog4 = (Button) findViewById(R.id.bt_dialog4);
        bt_dialog1.setOnClickListener(this);
        bt_dialog2.setOnClickListener(this);
        bt_dialog3.setOnClickListener(this);
        bt_dialog4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_dialog1:
                // TODO: 2016/10/10 普通对话框
                alertDialog = null;
                builder = new AlertDialog.Builder(mContext);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("这是普通的对话框");
                builder.setMessage("这是普通的对话框，分别有中立，取消，确定");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext, "你点击了取消按钮~", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext, "你点击了确定按钮~", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNeutralButton("中立", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext, "你点击了中立按钮~", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();//显示对话框
                break;
            case R.id.bt_dialog2:
                alertDialog = null;
                final String[] items = {"语文", "数学", "英语", "化学", "生物", "物理", "体育"};
                builder = new AlertDialog.Builder(mContext);
                builder.setTitle("这是普通列表对话框");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext, "你点击了~" + items[which] + "按钮", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();
                break;
            case R.id.bt_dialog3:
                alertDialog = null;
                builder = new AlertDialog.Builder(mContext);


                final String[] fruits = {"苹果", "雪梨", "香蕉", "葡萄", "西瓜"};
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("这是单选列表对话框");
                builder.setSingleChoiceItems(fruits, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext, "你选择了~" + fruits[which] , Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();
                break;
            case R.id.bt_dialog4:
                alertDialog = null;
                builder = new AlertDialog.Builder(mContext);
                final String[] menu = {"水煮豆腐", "萝卜牛腩", "酱油鸡", "胡椒猪肚鸡"};
                //定义一个用来记录个列表项状态的boolean数组
                checkItems = new boolean[]{false, false, false, false};
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("这是多选列表对话框");
                builder.setMultiChoiceItems(menu, checkItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkItems[which] = isChecked;
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String result = "";
                        for (int i = 0; i < checkItems.length ; i ++){
                            if (checkItems[i]){
                                result += menu[i] + "";
                            }
                        }
                        Toast.makeText(mContext, "你选择了:" + result , Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();
                break;
        }
    }
}
