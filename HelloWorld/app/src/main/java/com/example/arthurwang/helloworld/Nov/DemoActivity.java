package com.example.arthurwang.helloworld.nov;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arthurwang.helloworld.R;
import com.example.arthurwang.helloworld.nov.ui.CircleView;
import com.example.arthurwang.helloworld.nov.ui.HorizontalScrollViewEx;
import com.example.arthurwang.helloworld.nov.utils.MyUtils;

import java.util.ArrayList;

public class DemoActivity extends Activity {

    private static final String TAG = "DemoActivity_1";

    private HorizontalScrollViewEx mListContainer;

    private HorizontalScrollViewEx mContainer;
    private CircleView mCircleView1;
    private Button mBtnRemoteView;

    private void assignViews() {
        mContainer = (HorizontalScrollViewEx) findViewById(R.id.container);
        mCircleView1 = (CircleView) findViewById(R.id.circleView1);
        mBtnRemoteView = (Button) findViewById(R.id.btn_remote_view);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        Log.d(TAG, "onCreate");
//        initView();

        assignViews();

        mBtnRemoteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DemoActivity.this, DemoActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(DemoActivity.this,
                        0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_notification);
                remoteViews.setTextViewText(R.id.msg, "chapter_5");
                remoteViews.setImageViewResource(R.id.icon, R.drawable.bomb8);
                PendingIntent openActivity2PendingIntent = PendingIntent.getActivity(DemoActivity.this,
                        0,
                        new Intent(DemoActivity.this, TestActivity.class),
                        PendingIntent.FLAG_UPDATE_CURRENT);
                remoteViews.setOnClickPendingIntent(R.id.open_activity2, openActivity2PendingIntent);

                Notification notification = new NotificationCompat.Builder(DemoActivity.this, "customer")
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Hello world!")
                        .setContentText("我就是这么完美，你会不会害怕")
                        .setContentIntent(pendingIntent)
                        .setDefaults(Notification.DEFAULT_SOUND)
                        .setAutoCancel(true)
                        .setCustomContentView(remoteViews)
                        .build();





                NotificationManager notificationManager = (NotificationManager) DemoActivity.this.getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(1, notification);

            }
        });
    }

    private void initView() {
        LayoutInflater inflater = getLayoutInflater();
        mListContainer = (HorizontalScrollViewEx) findViewById(R.id.container);
        final int screenWidth = MyUtils.getScreenMetrics(this).widthPixels;
        final int screenHeight = MyUtils.getScreenMetrics(this).heightPixels;
        for (int i = 0; i < 3; i++) {
            ViewGroup layout = (ViewGroup) inflater.inflate(
                    R.layout.content_layout, mListContainer, false);
            layout.getLayoutParams().width = screenWidth;
            TextView textView = (TextView) layout.findViewById(R.id.title);
            textView.setText("page " + (i + 1));
            layout.setBackgroundColor(Color.rgb(255 / (i + 1), 255 / (i + 1), 0));
            createList(layout);
            mListContainer.addView(layout);
        }
    }

    private void createList(ViewGroup layout) {
        ListView listView = (ListView) layout.findViewById(R.id.list);
        ArrayList<String> datas = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            datas.add("name " + i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.content_list_item, R.id.name, datas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(DemoActivity.this, "click item",
                        Toast.LENGTH_SHORT).show();

            }
        });
    }
}
