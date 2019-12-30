package com.example.arthurwang.helloworld.Art;

import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
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
import com.socks.library.KLog;

public class NotifyActivity extends Activity {

    private int flagId = 1;

    private Button mMBtnNotify;
    private TextView mButton;

    private void assignViews() {
        mMBtnNotify = (Button) findViewById(R.id.mBtnNotify);
        mButton = (TextView) findViewById(R.id.button);
    }

    @Override
    public void finish() {
        super.finish();

        overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
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

                doAnimation();



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

    private void doAnimation() {
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_test);
//        mMBtnNotify.startAnimation(animation);

//        Rotated3dAnimation rotated3dAnimation = new Rotated3dAnimation(0, 300, 200, 200, 100, false);
//        rotated3dAnimation.setDuration(10000);
//        mMBtnNotify.startAnimation(rotated3dAnimation);

//        mMBtnNotify.setBackgroundResource(R.drawable.frame_animation);
//        AnimationDrawable drawable = (AnimationDrawable) mMBtnNotify.getBackground();
//        drawable.start();

//        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(NotifyActivity.this, R.animator.property_animator);
//        set.setTarget(mMBtnNotify);
//        set.setDuration(1000);
//        set.start();

//        ViewWrapper viewWrapper = new ViewWrapper(mMBtnNotify);
//
//        ObjectAnimator.ofInt(viewWrapper, "width", 500)
//                .setDuration(2000).start();

        performAnimate(mMBtnNotify, mMBtnNotify.getWidth(), 500);

    }

    private static class ViewWrapper {
        private View mTarget;

        public ViewWrapper(View target) {
            mTarget = target;
        }

        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }
    }

    private void performAnimate(final View target, final int start, final int end) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            private IntEvaluator mEvaluator = new IntEvaluator();

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentValue = (Integer) animation.getAnimatedValue();
                KLog.e("currentValue is " + currentValue);

                float fraction = animation.getAnimatedFraction();
                target.getLayoutParams().width = mEvaluator.evaluate(fraction, start, end);
                target.requestLayout();
            }
        });

        valueAnimator.setDuration(5000).start();
    }


}
