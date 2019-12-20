package com.example.arthurwang.helloworld.Art;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.example.arthurwang.helloworld.R;
import com.socks.library.KLog;

public class NotifyActivity extends Activity {

    private Button mMBtnNotify;

    private void assignViews() {
        mMBtnNotify = (Button) findViewById(R.id.mBtnNotify);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        assignViews();

        mMBtnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotifyActivity.this, NotifyActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(NotifyActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_notification);
                remoteViews.setTextViewText(R.id.msg, "chapter_5");
                remoteViews.setImageViewResource(R.id.icon, R.drawable.icon1);
                remoteViews.setOnClickPendingIntent(R.id.open_activity2, pendingIntent);

                Notification.Builder builder = new Notification.Builder(NotifyActivity.this);
                builder.setSmallIcon(R.drawable.ic_launcher);
                builder.setTicker("显示通知");
                builder.setContentTitle("通知");
                builder.setContentText("点击查看详情内容");
                builder.setWhen(System.currentTimeMillis());
                builder.setDefaults(Notification.DEFAULT_ALL);
                builder.setAutoCancel(true);
                builder.setContent(remoteViews);
                builder.setContentIntent(pendingIntent);
                Notification notification = builder.build();

                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                if (null != manager) {
                    manager.notify(2, notification);
                    KLog.e("111");
                }


            }
        });
    }
}
