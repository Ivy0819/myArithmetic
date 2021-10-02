package com.swufestu.myarithmetic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Testing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        Intent test =getIntent();
        String[] question = test.getStringArrayExtra("question");
        TextView formula = findViewById(R.id.formula);
        formula.setText(question[0]);
    }
}