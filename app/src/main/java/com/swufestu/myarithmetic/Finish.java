package com.swufestu.myarithmetic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class Finish extends AppCompatActivity {

    String TAG = "test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        Intent score =getIntent();
        String marks = score.getStringExtra("marks");
        String cost_time = score.getStringExtra("cost_time");
        String correct_rate = score.getStringExtra("corresct_rate");
        Log.i(TAG, "onCreate: marks"+marks);
    }
}