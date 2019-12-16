package com.example.arthurwang.helloworld.kotlin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.arthurwang.helloworld.nov.utils.MyUtils;
import com.socks.library.KLog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Created by arthurwang on 2019-12-16
 */
public class TCPServerService extends Service {


    private boolean mIsServiceDestoryed = false;
    private String[] mDefineMessages = new String[] {
        "你好啊，好好",
        "请问你叫什么名字？",
        "今天杭州的天气怎么样？",
        "你知道吗？我可是可以和很多人聊天的哦",
        "给你讲一个笑话，据说爱笑的人运气都不会太差。"
    };


    @Override
    public void onCreate() {
        new Thread(new TcpService()).start();

        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        mIsServiceDestoryed = true;

        super.onDestroy();
    }

    private class TcpService implements Runnable {
        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(8688);

            } catch (IOException e) {
                KLog.e("wyn", "establish tcp server failed, port: 8688");
                e.printStackTrace();
                return;
            }

            while (!mIsServiceDestoryed) {
                try {
                    final Socket client = serverSocket.accept();
                    KLog.e("accept");
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void responseClient(Socket client) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
        out.println("欢迎来到聊天室");

        while (!mIsServiceDestoryed) {
            String str = in.readLine();
            KLog.e("wyn", "msg from client: " + str);
            if (null == str) {
                break;
            }

            int i = new Random().nextInt(mDefineMessages.length);
            String msg = mDefineMessages[i];
            out.println(msg);
            KLog.e("wyn", "send :" + msg);
        }

        KLog.e("wyn", "client quit.");

        MyUtils.close(out);
        MyUtils.close(in);
        client.close();
    }
}
