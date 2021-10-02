package com.swufestu.myarithmetic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

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
        int number_max = 100;
        int question_number = 10;
        String[] question = new String[10];
        for (int i = 0; i < question_number; i++) {
            bTree = new BinaryTree(2);
            bTree.createBTree(number_max);
            String result = bTree.CalAndVal();
            question[i] = bTree.toString() + "=" + result;
        }
        Intent test = new Intent(this,Testing.class);
        test.putExtra("question",question);
        startActivityForResult(test,1); //打开可返回窗口
    }

}

