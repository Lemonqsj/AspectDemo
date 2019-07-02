package com.example.testaspectj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.testaspectj.annotion.BehaviorTrace;


public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


    }

    @BehaviorTrace("wave")
    public void login(View view) {

    SharePreferenceUtil.setBooleanSp(SharePreferenceUtil.IS_LOGIN,true,this);
    finish();
    }
}
