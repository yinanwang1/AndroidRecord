package com.example.arthurwang.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.socks.library.KLog;

public class Activity2Activity extends Activity {

    private static final String ext_name = "name";

    private TextView textView;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);

        // 初始化视图
        textView = (TextView) findViewById(R.id.textview);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.backBtn);


        // 设置发送过来的内容
        final Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString(ext_name);
        KLog.e("name is " + name);

        textView.setText(name);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                KLog.e("editText.getText() is " + editText.getText());

                Intent myIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString(ext_name, editText.getText().toString());

                myIntent.putExtras(bundle);

                setResult(1, myIntent);

                finish();
            }
        });
    }

}


