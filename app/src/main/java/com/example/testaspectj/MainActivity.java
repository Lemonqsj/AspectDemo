package com.example.testaspectj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.testaspectj.annotion.BehaviorTrace;
import com.example.testaspectj.annotion.LoginFilter;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @LoginFilter
    @BehaviorTrace("wave")
    public void wave(View view) {


    }
}
