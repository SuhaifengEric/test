package com.shf.testsocket;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private EditText etIP,etText;
    private TextView tvContent;
    private Button btConnect;
    private Button btSend;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }
    private void initView() {
        etIP = (EditText) findViewById(R.id.et_IP);
        etText = (EditText) findViewById(R.id.et_send_content);
        btConnect = (Button) findViewById(R.id.bt_connect);
        btSend = (Button) findViewById(R.id.bt_send);
        tvContent = (TextView) findViewById(R.id.tv_show_content);
    }
    private void initData(){
        btConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connect();
            }
        });
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send();
            }
        });
    }
//---------------------------------------------------------------------------------------------------
    private Socket socket = null;
    private BufferedWriter writer;
    private BufferedReader reader;

    private void connect() {
            AsyncTask<Void,String,Void> read = new AsyncTask<Void, String, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    try{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    socket = new Socket(etIP.getText().toString(),12345);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    Toast.makeText(MainActivity.this,"连接失败",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        publishProgress("@success");
                    }catch (Exception e){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,"连接失败",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                    String line = null;
                    try {
                        while ((line = reader.readLine())!=null){
                            publishProgress(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onProgressUpdate(String... values) {
                    if (values[0].equals("@success")){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,"连接成功",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    tvContent.append(values[0]);
                    super.onProgressUpdate(values);
                }
            };
            read.execute();//执行异步线程
    }

    private void send() {
        try {
            writer.write(etText.getText().toString());
            etText.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
