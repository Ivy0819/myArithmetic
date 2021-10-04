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

    String TAG = "TestPage";
    int time, time_all;
    String[] question;//题
    String[] correct_result;//正确答案
    String[] answers;//提交答案
    boolean[] correct_num;
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
        time_all = test.getIntExtra("time",10);
        //初始化answer与correct_num
        answers = new String[question.length];
        correct_num = new boolean[question.length];
        for(int i = 0;i < question.length;i++){
            answers[i] = "null";
            correct_num[i] = false;
        }

        Log.i(TAG, "onCreate: question"+question);
        Log.i(TAG, "onCreate: correct_result"+correct_result);
        Log.i(TAG, "onCreate: time"+time);
        process = findViewById(R.id.process);
        process.setText("1/"+question.length);
        formula = findViewById(R.id.formula);
        formula.setText(question[0]);
        answer.setText("");
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

//跳转到FinishPage
    private void toFinishPage(){

        int cost_time = time_all-time;
        Log.i(TAG, "toFinishPage: cost_time="+cost_time);
        Intent score = new Intent(this,Finish.class);finish();

        score.putExtra("correct_num",correct_num);
        score.putExtra("cost_time",cost_time);
        score.putExtra("questions",question);
        score.putExtra("correct_result", correct_result);
        score.putExtra("answers", answers);
        startActivity(score);

        Log.i("TestPage", "打开Finish窗口");

    }

//倒计时线程
    public void run() {
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

//处理下一题&&完成
    public void next(View v){
        answers[num] = answer.getText().toString();//获取上一题的answer
        correct_num[num]=answers[num].equals(correct_result[num])?true:false;
        Log.i(TAG, "next: correct_num:"+correct_num[num]);


        num = num + 1;
        if(num<(question.length-1)) {
            prior.setVisibility(View.VISIBLE);
            process.setText((num + 1) + "/" + question.length);
            formula.setText(question[num]);
            answer.setText("");
        }else if(num == (question.length-1)){
            prior.setVisibility(View.VISIBLE);
            next.setText("完成");
            process.setText((num + 1) + "/" + question.length);
            formula.setText(question[num]);
            answer.setText("");
        } else{
            Log.i(TAG, "next: begin to finish");
            toFinishPage();

        }
    }

//处理上一题
    public void prior(View v){
        num -=1;

        if(num==0) {
            prior.setVisibility(View.INVISIBLE);
            process.setText((num + 1) + "/" + (question.length));
            formula.setText(question[num]);
            answer.setText(answers[num]);
        }else{
            prior.setVisibility(View.VISIBLE);
            process.setText((num + 1) + "/" + (question.length));
            formula.setText(question[num]);
            answer.setText(answers[num]);
            next.setText("下一题");
        }
    }

    //回到MainPage
    public void back(View v){
        finish();
    }

}

