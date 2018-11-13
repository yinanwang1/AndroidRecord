package com.example.arthurwang.helloworld.Nov;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.arthurwang.helloworld.R;
import com.socks.library.KLog;

public class NovActivity extends AppCompatActivity {


    private EditText mEtTest;
    private Button mBtnCreate;

    private void assignViews() {
        mEtTest = (EditText) findViewById(R.id.et_test);
        mBtnCreate = (Button) findViewById(R.id.btn_create);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nov);

        assignViews();

        mBtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NovActivity.this, SecondActivity.class);
                intent.putExtra("time", System.currentTimeMillis());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        KLog.a("onSaveInstanceState");

        outState.putString("extra_test", mEtTest.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String test = savedInstanceState.getString("extra_test");

        KLog.a("onRestoreInstanceState test is " + test);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        KLog.a("onConfigurationChanged, new Orientation :" + newConfig.orientation);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        KLog.a("onNewIntent, time=" + intent.getLongExtra("time",0));
    }
}
