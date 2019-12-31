package com.example.arthurwang.helloworld.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arthurwang.helloworld.R;
import com.socks.library.KLog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileSaveActivity extends Activity {

    private EditText mEdit;

    private void assignViews() {
        mEdit = (EditText) findViewById(R.id.edit);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_save);

        assignViews();

        String inputText = load();
        if (!TextUtils.isEmpty(inputText)) {
            mEdit.setText(inputText);
            mEdit.setSelection(inputText.length());

            Toast.makeText(this, "Restoring successed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        save(mEdit.getText().toString());
    }

    private void save(String inputText) {
        KLog.e("wyn save");

        FileOutputStream outputStream;
        BufferedWriter writer = null;
        try {
            outputStream = openFileOutput("data", Context.MODE_APPEND);
            writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String load() {
        FileInputStream inputStream = null;
        BufferedReader reader = null;
        StringBuffer content = new StringBuffer();

        try
        {
            inputStream = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return content.toString();
    }
}
