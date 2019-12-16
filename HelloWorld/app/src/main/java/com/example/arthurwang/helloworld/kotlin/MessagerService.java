package com.example.arthurwang.helloworld.kotlin;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import androidx.annotation.NonNull;

import com.example.arthurwang.helloworld.nov.MyConstants;
import com.socks.library.KLog;

public class MessagerService extends Service {
    private static final String TAG = "MessagerService";

    public MessagerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    private static class Messagerhandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            KLog.e("wyn", "222message");

            switch (msg.what) {
                case MyConstants.MSG_FROM_CLIENT: {
                    KLog.e("wyn", "receive msg from client:" + msg.getData().getString("msg"));

                    Messenger client = msg.replyTo;
                    Message relpyMessage = Message.obtain(null, MyConstants.MSG_FROM_SERVICE);
                    Bundle bundle = new Bundle();
                    bundle.putString("replay", "嗯，你的消息我已经收到，稍后会回复你。");
                    relpyMessage.setData(bundle);

                    try {
                        client.send(relpyMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    break;
                }
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private final Messenger mMessenger = new Messenger(new Messagerhandler());

}
