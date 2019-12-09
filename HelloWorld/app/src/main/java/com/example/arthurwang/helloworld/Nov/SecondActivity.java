package com.example.arthurwang.helloworld.nov;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.arthurwang.helloworld.R;

public class SecondActivity extends AppCompatActivity {

    private Button mBtnJump;

    private void assignViews() {
        mBtnJump = findViewById(R.id.btn_jump);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        assignViews();

        mBtnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);

                startActivity(intent);
            }
        });
    }
}
