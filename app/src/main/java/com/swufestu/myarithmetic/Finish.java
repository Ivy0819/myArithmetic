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
    String[] question;//题
    String[] correct_result;//正确答案
    String[] answers;//提交答案
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
        question = score.getStringArrayExtra("questions");
        correct_result = score.getStringArrayExtra("correct_result");
        answers = score.getStringArrayExtra("answers");

        String correct_rate = get_rate(correct_num);

        //获取控件
        grades = findViewById(R.id.score);
        right_rate = findViewById(R.id.right_rate);
        time_cost = findViewById(R.id.time_cost);

        //显示参数
        Log.i(TAG, "onCreate: fenshu"+correct_count*10+"/"+correct_num.length*10);
        Log.i(TAG, "onCreate:zhengquelv"+correct_rate);
        Log.i(TAG, "onCreate: shijian"+cost_time);
        grades.setText(getResources().getString(R.string.marks)+correct_count*10+"/"+correct_num.length*10);
        right_rate.setText(getResources().getString(R.string.correct_rate)+correct_rate);
        time_cost.setText(getResources().getString(R.string.cost_time)+cost_time+"s");


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

    //跳转到WrongAnswerPage
    public void toWAPage(View button){

        Log.i(TAG, "toFinishPage: cost_time="+cost_time);
        Intent wrong_ans = new Intent(this,WrongAnswer.class);

        wrong_ans.putExtra("questions",question);
        wrong_ans.putExtra("correct_result", correct_result);
        wrong_ans.putExtra("answers", answers);

        startActivity(wrong_ans);

        Log.i("TestPage", "打开wrong_answer窗口");

    }
}