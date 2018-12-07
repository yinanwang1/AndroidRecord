package com.example.arthurwang.helloworld.nov;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arthurwang.helloworld.R;
import com.example.arthurwang.helloworld.nov.ui.CircleView;
import com.example.arthurwang.helloworld.nov.ui.CustomDrawable;
import com.example.arthurwang.helloworld.nov.ui.HorizontalScrollViewEx;
import com.example.arthurwang.helloworld.nov.utils.MyUtils;

import java.util.ArrayList;

public class DemoActivity extends Activity {

    private static final String TAG = "DemoActivity_1";

    private HorizontalScrollViewEx mListContainer;

    private HorizontalScrollViewEx mContainer;
    private CircleView mCircleView1;
    private Button mBtnRemoteView;
    private ImageView mIvLevel;

    private void assignViews() {
        mContainer = (HorizontalScrollViewEx) findViewById(R.id.container);
        mCircleView1 = (CircleView) findViewById(R.id.circleView1);
        mBtnRemoteView = (Button) findViewById(R.id.btn_remote_view);
        mIvLevel = (ImageView) findViewById(R.id.iv_level);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        Log.d(TAG, "onCreate");
//        initView();

        assignViews();


        CustomDrawable customDrawable = new CustomDrawable(Color.RED);
        mIvLevel.setBackground(customDrawable);

        mBtnRemoteView.setBackgroundResource(R.drawable.frame_animation);
        AnimationDrawable animationDrawable = (AnimationDrawable) mBtnRemoteView.getBackground();
        animationDrawable.start();


        mBtnRemoteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Rotate3dAnimation rotate3dAnimation = new Rotate3dAnimation(0, 100, 0, 100, 100, true);
//
//                mIvLevel.startAnimation(rotate3dAnimation);

//                Intent intent = new Intent(DemoActivity.this, TestActivity.class);
//
//                startActivity(intent);
//
//                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);

                    testAnimation();



//                Intent intent = new Intent(DemoActivity.this, DemoActivity.class);
//                PendingIntent pendingIntent = PendingIntent.getActivity(DemoActivity.this,
//                        0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_notification);
//                remoteViews.setTextViewText(R.id.msg, "chapter_5");
//                remoteViews.setImageViewResource(R.id.icon, R.drawable.bomb8);
//                PendingIntent openActivity2PendingIntent = PendingIntent.getActivity(DemoActivity.this,
//                        0,
//                        new Intent(DemoActivity.this, TestActivity.class),
//                        PendingIntent.FLAG_UPDATE_CURRENT);
//                remoteViews.setOnClickPendingIntent(R.id.open_activity2, openActivity2PendingIntent);
//
//                Notification notification = new NotificationCompat.Builder(DemoActivity.this, "customer")
//                        .setSmallIcon(R.drawable.ic_launcher)
//                        .setContentTitle("Hello world!")
//                        .setContentText("我就是这么完美，你会不会害怕")
//                        .setContentIntent(pendingIntent)
//                        .setDefaults(Notification.DEFAULT_SOUND)
//                        .setAutoCancel(true)
//                        .setCustomContentView(remoteViews)
//                        .build();
//
//
//
//
//
//                NotificationManager notificationManager = (NotificationManager) DemoActivity.this.getSystemService(Context.NOTIFICATION_SERVICE);
//
//                notificationManager.notify(1, notification);

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



    private void testAnimation() {
        // 步骤1：设置动画属性的初始值 & 结束值
        ValueAnimator anim = ValueAnimator.ofInt(100, 500);
        // ofInt（）作用有两个
        // 1. 创建动画实例
        // 2. 将传入的多个Int参数进行平滑过渡:此处传入0和1,表示将值从0平滑过渡到1
        // 如果传入了3个Int参数 a,b,c ,则是先从a平滑过渡到b,再从b平滑过渡到C，以此类推
        // ValueAnimator.ofInt()内置了整型估值器,直接采用默认的.不需要设置，即默认设置了如何从初始值 过渡到 结束值
        // 关于自定义插值器我将在下节进行讲解
        // 下面看看ofInt()的源码分析 ->>关注1

// 步骤2：设置动画的播放各种属性
        anim.setDuration(500);
        // 设置动画运行的时长

        anim.setStartDelay(500);
        // 设置动画延迟播放时间

        anim.setRepeatCount(ValueAnimator.INFINITE);
        // 设置动画重复播放次数 = 重放次数+1
        // 动画播放次数 = infinite时,动画无限重复

        anim.setRepeatMode(ValueAnimator.REVERSE);
        // 设置重复播放动画模式
        // ValueAnimator.RESTART(默认):正序重放
        // ValueAnimator.REVERSE:倒序回放

// 步骤3：将改变的值手动赋值给对象的属性值：通过动画的更新监听器
        // 设置 值的更新监听器
        // 即：值每次改变、变化一次,该方法就会被调用一次
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                int currentValue = (Integer) animation.getAnimatedValue();
                // 获得改变后的值

                System.out.println(currentValue);
                // 输出改变后的值

                // 步骤4：将改变后的值赋给对象的属性值，下面会详细说明
                mBtnRemoteView.getLayoutParams().width = currentValue;

                // 步骤5：刷新视图，即重新绘制，从而实现动画效果
                mBtnRemoteView.requestLayout();


            }
        });

        anim.start();
        // 启动动画
    }



}
