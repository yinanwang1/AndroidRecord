package com.example.arthurwang.helloworld.kotlin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.arthurwang.helloworld.R;
import com.example.arthurwang.helloworld.nov.utils.MyUtils;
import com.socks.library.KLog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TCPClientActivity extends Activity implements View.OnClickListener {

    private static final int MESSAGE_RECEIVE_NEW_MSG = 1;
    private static final int MESSAGE_SOCKET_CONNECTED = 2;

    private EditText mEtMessage;
    private Button mBtnMessage;
    private TextView mTvMessage;

    private PrintWriter mPrintWriter;
    private Socket mClientSocket;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case MESSAGE_RECEIVE_NEW_MSG:
                    mTvMessage.setText(mTvMessage.getText() + (String)msg.obj);
                    break;
                case MESSAGE_SOCKET_CONNECTED:
                    mBtnMessage.setEnabled(true);
                    break;
                    default:
                        break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcpclient);

        mEtMessage = (EditText) findViewById(R.id.et_message);
        mBtnMessage = (Button) findViewById(R.id.btn_message);
        mTvMessage = (TextView) findViewById(R.id.tv_message);

        mBtnMessage.setOnClickListener(this);
        Intent service = new Intent(this, TCPServerService.class);
        startService(service);

        new Thread() {
            @Override
            public void run() {
                connectTCPServer();
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        if (null != mClientSocket) {
            try {
                mClientSocket.shutdownInput();
                mClientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        super.onDestroy();
    }

    // View.OnClickListener

    @Override
    public void onClick(View v) {
        if (v == mBtnMessage) {
            final String msg = mEtMessage.getText().toString();
            if (!TextUtils.isEmpty(msg) && mPrintWriter != null) {
                new Thread() {
                    @Override
                    public void run() {
                        mPrintWriter.println(msg);
                        mEtMessage.setText("");
                        String time = formatDateTime(System.currentTimeMillis());
                        final String showedMsg = "self " + time + ":" + msg + "\n";

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTvMessage.setText(mTvMessage.getText()+showedMsg);
                            }
                        });
                    }
                }.start();
            }
        }
    }


    private String formatDateTime(long time) {
        return new SimpleDateFormat("(HH:mm:ss)", Locale.CHINA).format(new Date(time));
    }


    private void connectTCPServer() {
        Socket socket = null;
        while (null == socket) {
            try {
                socket = new Socket("localhost", 8688);
                mClientSocket = socket;
                mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                mHandler.sendEmptyMessage(MESSAGE_SOCKET_CONNECTED);
                KLog.e("wyn", "connect server success");
            } catch (IOException e) {
                SystemClock.sleep(1000);
                KLog.e("wyn", "connect tcp server failed, retry...");
            }
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!TCPClientActivity.this.isFinishing()) {
                String msg = bufferedReader.readLine();
                KLog.e("wyn", "receive :" + msg);
                if (null != msg) {
                    String time = formatDateTime(System.currentTimeMillis());
                    final String showedMsg = "server " + time + ":" + msg + "\n";
                    mHandler.obtainMessage(MESSAGE_RECEIVE_NEW_MSG, showedMsg).sendToTarget();
                }
            }

            KLog.e("wyn", "quit...");
            MyUtils.close(mPrintWriter);
            MyUtils.close(bufferedReader);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
