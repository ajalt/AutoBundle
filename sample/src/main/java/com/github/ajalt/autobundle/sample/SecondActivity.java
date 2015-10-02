package com.github.ajalt.autobundle.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.ajalt.autobundle.AutoBundle;
import com.github.ajalt.autobundle.BundleArgument;

public class SecondActivity extends AppCompatActivity {
    public static class Arguments {
        @BundleArgument
        public Integer intArg;
        @BundleArgument
        public String stringArg;

        public Arguments() {}

        public Arguments(Integer intArg, String stringArg) {
            this.intArg = intArg;
            this.stringArg = stringArg;
        }
    }

    public static Intent createIntent(Context context, Arguments args) {
        Intent intent = new Intent(context, SecondActivity.class);
        AutoBundle.packIntent(args, intent);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Arguments args = new Arguments();
        AutoBundle.unpackIntent(getIntent(), args);

        ((TextView) findViewById(R.id.text)).setText(args.intArg.toString());
        ((TextView) findViewById(R.id.text2)).setText(args.stringArg);
    }
}
