package com.github.ajalt.autobundle.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View v) {
        startActivity(SecondActivity.createIntent(this,
                new SecondActivity.Arguments(123, "a bundle argument")));
    }
}
