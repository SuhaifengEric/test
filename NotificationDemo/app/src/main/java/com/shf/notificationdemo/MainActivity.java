package com.shf.notificationdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btOpen;
    private Button btClose;
    private Context mContext;
    private NotificationManager manager;
    private Notification.Builder builder;
    private Bitmap bitmap;
    private Notification notify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btOpen = (Button) findViewById(R.id.bt_open);
        btClose = (Button) findViewById(R.id.bt_close);
        initData();
    }

    private void initData() {
        mContext = MainActivity.this;
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        builder = new Notification.Builder(this);
        btClose.setOnClickListener(this);
        btOpen.setOnClickListener(this);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_open:
                // TODO: 2016/10/7 打开notification
                Intent intent = new Intent(mContext,NotificationActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(mContext,0,intent,PendingIntent.FLAG_ONE_SHOT);
                builder.setContentTitle("叶良辰");
                builder.setContentText("我有一百种方法让你活不下去");
                builder.setLargeIcon(bitmap);
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setTicker("收到叶良辰发送过来的信息");
                builder.setSubText("---记住我叫叶良辰");
                builder.setWhen(System.currentTimeMillis());
                builder.setAutoCancel(true);//点击后自动取消
                builder.setContentIntent(pendingIntent);
                notify = builder.build();
                manager.notify(1,notify);
                break;
            case R.id.bt_close:
                // TODO: 2016/10/7 关闭notification
                manager.cancel(1);
                break;
        }
    }
}
