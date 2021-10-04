package com.swufestu.myarithmetic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    String TAG ="MainPage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent finish = getIntent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_set){
            Intent intro = new Intent(this,Intro.class);
            startActivity(intro);
        }
        return super.onOptionsItemSelected(item);
    }

    public void begintest(View v){
        BinaryTree bTree;
        EditText question_count = findViewById(R.id.question_count);
        EditText calculate_range = findViewById(R.id.calculate_range);
        EditText test_time = findViewById(R.id.test_time);
        int question_number = Integer.valueOf(question_count.getText().toString()).intValue();
        int number_max = Integer.valueOf(calculate_range.getText().toString()).intValue();
        float time = Float.valueOf(test_time.getText().toString()).floatValue();
        String[] question = new String[question_number];
        String[] correct_result = new String[question_number];
        for (int i = 0; i < question_number; i++) {
            bTree = new BinaryTree(2);
            bTree.createBTree(number_max);
            String result = bTree.CalAndVal();
            question[i] = bTree.toString();
            correct_result[i] = result;
        }
        Intent test = new Intent(this,Testing.class);
        test.putExtra("question",question);//获得question数组
        test.putExtra("correct_result",correct_result);//获得正确答案数组
        test.putExtra("time",Math.round(time*60));
        Log.i(TAG, "get question"+question);
        Log.i(TAG, "get correct_result"+correct_result);
        Log.i(TAG, "get time"+time);
        startActivity(test); //打开窗口
    }

}

