package com.example.arthurwang.helloworld.nov;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.arthurwang.helloworld.R;
import com.socks.library.KLog;

public class MessagerActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG  = " MessengerActivity";

    private Messenger mService;

    private Button mBt;
    private LinearLayout mLl;
    private Button mBtn;

    private void assignViews() {
        mBt = findViewById(R.id.bt);
        mLl = findViewById(R.id.ll);
        mBtn = findViewById(R.id.btn);
    }



    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = new Messenger(service);
            Message msg = android.os.Message.obtain(null, MyConstants.MSG_FROM_CLIENT);
            Bundle data = new Bundle();
            data.putString("msg", "Hello, this is client");
            msg.setData(data);
            msg.replyTo = mGetReplyMessenger;

            try {
                mService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    private Messenger mGetReplyMessenger = new Messenger(new MessengerHandler());

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MyConstants.MSG_FROM_SERVICE:
                    KLog.a("receive msg from service:" + msg.getData().getString("reply"));
                    break;

                    default:
                        super.handleMessage(msg);
                        break;
            }
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messager);

        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        assignViews();

        mBt.setOnClickListener(this);
        mLl.setOnClickListener(this);
        mBtn.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        unbindService(mConnection);

        super.onDestroy();
    }

    // View.OnClickListener

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt:
                mBt.scrollTo(-100, -100);
                break;

            case R.id.ll:
                mLl.scrollBy(-10, -10);
                break;

            case R.id.btn:
                Toast.makeText(this, "点击了按钮二", Toast.LENGTH_SHORT).show();

                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mBtn.getLayoutParams();
                params.width += 10;
                params.leftMargin += 10;

                mBtn.requestLayout();

                break;
        }
    }
}
