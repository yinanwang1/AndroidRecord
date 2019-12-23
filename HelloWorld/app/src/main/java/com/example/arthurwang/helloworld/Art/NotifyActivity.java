package com.example.arthurwang.helloworld.Art;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.example.arthurwang.helloworld.R;
import com.example.arthurwang.helloworld.nov.MyConstants;

public class NotifyActivity extends Activity {

    private int flagId = 1;

    private Button mMBtnNotify;
    private TextView mButton;

    private void assignViews() {
        mMBtnNotify = (Button) findViewById(R.id.mBtnNotify);
        mButton = (TextView) findViewById(R.id.button);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        assignViews();

        mMBtnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(NotifyActivity.this, NotifyActivity.class);
//                PendingIntent pendingIntent = PendingIntent.getActivity(NotifyActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_notification);
//                remoteViews.setTextViewText(R.id.msg, "chapter_5");
//                remoteViews.setImageViewResource(R.id.icon, R.drawable.icon1);
//                remoteViews.setOnClickPendingIntent(R.id.open_activity2, pendingIntent);
//
//                Notification.Builder builder = new Notification.Builder(NotifyActivity.this);
//                builder.setSmallIcon(R.drawable.ic_launcher);
//                builder.setTicker("显示通知");
//                builder.setContentTitle("通知");
//                builder.setContentText("点击查看详情内容");
//                builder.setWhen(System.currentTimeMillis());
//                builder.setDefaults(Notification.DEFAULT_ALL);
//                builder.setAutoCancel(true);
//                builder.setContent(remoteViews);
//                builder.setContentIntent(pendingIntent);
//                Notification notification = builder.build();
//
//                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                if (null != manager) {
//                    manager.notify(flagId, notification);
//                    KLog.e("111");
//
//                    flagId++;
//                }

//                justDoIt();



            }
        });

        int colorValue = Color.parseColor("#00FF00");
        CustomDrawable customDrawable = new CustomDrawable(colorValue);
        mButton.setBackground(customDrawable);
    }


    private void justDoIt() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_simulated_notification);
        remoteViews.setTextViewText(R.id.msg, "msg from process:" + Process.myPid());
        remoteViews.setImageViewResource(R.id.icon, R.drawable.icon1);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, NotifyActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent openActivity2PendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, RemoteViewActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.item_holder, pendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.open_activity2, openActivity2PendingIntent);
        Intent intent = new Intent(MyConstants.REMOTE_ACTION);
        intent.putExtra(MyConstants.EXTRA_REMOTE_VIEWS, remoteViews);
        sendBroadcast(intent);
    }
}
