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

    String TAG ="test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_set){
            //openConfig();
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
        int time = Integer.valueOf(test_time.getText().toString()).intValue();
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
        test.putExtra("question",question);
        test.putExtra("correct_result",correct_result);
        test.putExtra("time",time*60);
        Log.i(TAG, "begintest: question"+question);
        Log.i(TAG, "begintest: correct_result"+correct_result);
        Log.i(TAG, "begintest: time"+time);
        startActivityForResult(test,1); //打开窗口
    }

}

