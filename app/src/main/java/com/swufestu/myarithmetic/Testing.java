package com.swufestu.myarithmetic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class Testing extends AppCompatActivity implements Runnable{
    int time_test = 10;
    Handler handler;//定义放外面，因为到处都会用

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        Intent test =getIntent();
        String[] question = test.getStringArrayExtra("question");
        TextView formula = findViewById(R.id.formula);
        formula.setText(question[0]);

        //开启线程

        Thread t = new Thread(this);
        this.run();//this.run()
        TextView show_count = findViewById(R.id.count_down);


        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                int show = msg.arg1;
                if(show > 0){
                    show_count.setText(show+"");
                    run();
                }
                else {
                    toFinishPage();
                }

            }
        };
    }

    private void toFinishPage(){

        Log.i("TestPage", "打开Finish窗口");
        Intent config = new Intent(this, Finish.class);

        startActivity(config);
    }


    public void run() {
        Log.i("TestPage", "开启线程");
        Timer timer = new Timer();
        TimerTask timertask = new TimerTask() {
            @Override
            public void run() {
                time_test--;
                Message  message = handler.obtainMessage();
                message.arg1 = time_test;
                //6.发送消息给handlermessge进行操作
                handler.sendMessage(message);

            }
        };

        //7.等待1秒以后开始执行
        timer.schedule(timertask, 1000);

    }


}

