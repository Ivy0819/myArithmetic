package com.swufestu.myarithmetic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Finish extends AppCompatActivity {

    String TAG = "FinishPage";
    int correct_count = 0;
    int cost_time = 0;
    boolean[] correct_num;
    TextView grades;
    TextView right_rate;
    TextView time_cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        //获取传来的参数，计算正确率
        Intent score =getIntent();
        correct_num = score.getBooleanArrayExtra("correct_num");
        cost_time = score.getIntExtra("cost_time",0);
        String correct_rate = get_rate(correct_num);

        //获取控件
        grades = findViewById(R.id.score);
        right_rate = findViewById(R.id.right_rate);
        time_cost = findViewById(R.id.time_cost);

        //显示参数
        Log.i(TAG, "onCreate: fenshu"+correct_count*10+"/"+correct_num.length*10);
        Log.i(TAG, "onCreate:zhengquelv"+correct_rate);
        Log.i(TAG, "onCreate: shijian"+cost_time);
        grades.setText("分数："+correct_count*10+"/"+correct_num.length*10);
        right_rate.setText("正确率："+correct_rate);
        time_cost.setText("花费时间："+cost_time+"秒");


    }

    private String get_rate(boolean[] correct_num) {

        for(int i=0;i<correct_num.length;i++){
            if(correct_num[i] == true) correct_count++;
        }
        String correct_rate = String.format("%.2f", ((double)correct_count/correct_num.length)*100)+"%";
        Log.i(TAG, "onCreate: correct_rate="+correct_rate);
        return correct_rate;
    }

    public void finishquit(View v){
        Intent finish = new Intent(this,MainActivity.class);
        startActivity(finish);
    }
}