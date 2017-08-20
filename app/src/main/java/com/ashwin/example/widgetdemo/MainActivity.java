package com.ashwin.example.widgetdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            Log.d("debuglogging", TAG + " : saved instance state : " + savedInstanceState.toString());
        } else {
            Log.d("debuglogging", TAG + " : saved instance state : null");
        }

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Log.d("debuglogging", TAG + " : intent : " + bundle.toString());
        } else {
            Log.d("debuglogging", TAG + " : intent : null");
        }
    }
}
