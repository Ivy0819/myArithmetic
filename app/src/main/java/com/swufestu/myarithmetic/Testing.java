package com.swufestu.myarithmetic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class Testing extends AppCompatActivity implements Runnable{

    String TAG = "test";
    int time = 10;
    String[] question;
    String[] correct_result;
    String[] answers;
    Handler handler;//定义放外面，因为到处都会用
    EditText answer;
    EditText process;
    EditText formula;
    Button prior;
    Button next;
    int num = 0;
    TimerTask timertask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        answer = findViewById(R.id.answer);
        prior = findViewById(R.id.prior);
        next = findViewById(R.id.next);

        Intent test =getIntent();
        question = test.getStringArrayExtra("question");
        correct_result = test.getStringArrayExtra("correct_result");
        time = test.getIntExtra("time",10);
        answers = new String[question.length];
        Log.i(TAG, "onCreate: question"+question);
        Log.i(TAG, "onCreate: correct_result"+correct_result);
        Log.i(TAG, "onCreate: time"+time);
        process = findViewById(R.id.process);
        process.setText("1/"+(question.length-1));
        formula = findViewById(R.id.formula);
        formula.setText(question[0]);
        prior.setVisibility(View.INVISIBLE);

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
        timertask = new TimerTask() {
            @Override
            public void run() {
                time--;
                Message  message = handler.obtainMessage();
                message.arg1 = time;
                //6.发送消息给handlermessge进行操作
                handler.sendMessage(message);

            }
        };

        //7.等待1秒以后开始执行
        timer.schedule(timertask, 1000);

    }

    public void next(View v){
        answers[num] = answer.getText().toString();
        Log.i(TAG, "next: answer"+answers[num]);
        prior.setVisibility(View.VISIBLE);
        int marks = 0;
        int correct_num = 0;
        num = num + 1;
        if(num<(question.length-1)) {
            process.setText((num + 1) + "/" + question.length);
            formula.setText(question[num]);
            answer.setText("");
        }else if(num == (question.length-1)){
            next.setText("完成");
            process.setText((num + 1) + "/" + question.length);
            formula.setText(question[num]);
            answer.setText("");
        } else{
            for(int j=0;j<question.length;j++){
                if(answers[j].equals(correct_result[j])){
                    marks +=10;
                    correct_num  +=1;
                }
            }
            String correct_rate = String.format("%.2f", correct_num/(num+1));
            int cost_time = time-Integer.valueOf(String.valueOf(timertask)).intValue();
            Intent score = new Intent(this,Finish.class);
            score.putExtra("marks",marks);
            score.putExtra("cost_time",cost_time);
            score.putExtra("correct_rate",correct_rate);
            startActivity(score);
        }
    }

    public void prior(View v){
        num -=1;
        if(num==0){
            prior.setVisibility(View.INVISIBLE);
        }
        process.setText((num + 1) + "/" + (question.length - 1));
        formula.setText(question[num]);
        answer.setText(answers[num]);
    }

    public void back(View v){
        finish();
    }

}

