package com.swufestu.myarithmetic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class WrongAnswer extends AppCompatActivity {
    String TAG = "WrongansPage";
    String[] question;//题
    String[] correct_result;//正确答案
    String[] answers;//提交答案
    String text_list = "";
    TextView showcase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_answer);
        showcase = findViewById(R.id.showcase);

        //获取传来的参数，计算正确率
        Intent wrong_ans =getIntent();
        question = wrong_ans.getStringArrayExtra("questions");
        correct_result = wrong_ans.getStringArrayExtra("correct_result");
        answers = wrong_ans.getStringArrayExtra("answers");

        //遍历获得错题
        for(int i = 0;i < question.length;i++){
            if (!(correct_result[i].equals(answers[i]))){
                String text = "";
                text = getResources().getString(R.string.wrong_question_no)+(i+1)+"\n";
                text = text +getResources().getString(R.string.wrong_question)+question[i]+"\n";
                text = text +getResources().getString(R.string.correct_answer)+correct_result[i]+"\n";
                text = text +getResources().getString(R.string.your_answer)+answers[i]+"\n";
                text = text +"\n";
                text_list += text;
                Log.i(TAG, "onCreate: text="+text);
            }
        }

        showcase.setText(text_list);
    }

    public void finishQuit(View v){
//        Intent finish = new Intent(this,MainActivity.class);
//        startActivity(finish);
        finish();
    }
}

