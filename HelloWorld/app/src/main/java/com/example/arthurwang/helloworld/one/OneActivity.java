package com.example.arthurwang.helloworld.one;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arthurwang.helloworld.R;
import com.socks.library.KLog;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OneActivity extends AppCompatActivity {

    private TextView mTvTextView;
    private Button mBtnPush;
    private Button mBtnWeb;

    private void assignViews() {
        mTvTextView = findViewById(R.id.tv_text_view);
        mBtnPush = findViewById(R.id.btn_push);
        mBtnWeb = findViewById(R.id.btn_web);
    }



    private Observer<Long> observer = new Observer<Long>() {
        Disposable disposable;

        @Override
        public void onSubscribe(Disposable d) {
            Log.e("wyn", "onSubscribe");

            Log.e("wyn", "onSubscribe thread is " + Thread.currentThread().getName());

            disposable = d;
        }

        @Override
        public void onNext(Long s) {
            Log.d("wyn", "onNext is " + s);

            Log.e("wyn", "onNext thread is " + Thread.currentThread().getName());

            mTvTextView.setText(s + "");

            if (s == 10) {
                disposable.dispose();
            }
        }

        @Override
        public void onError(Throwable e) {
            Log.e("wyn", "onError");

            Log.e("wyn", "onError thread is " + Thread.currentThread().getName());
        }

        @Override
        public void onComplete() {
            Log.e("wyn", "onComplete");

            Log.e("wyn", "onComplete thread is " + Thread.currentThread().getName());
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        assignViews();

//        Cartoon<Sheep, Wolf> play = new Cartoon<>();
//        Sheep xiyangyang = new Sheep();
//        Wolf huitailang = new Wolf();
//
//        play.role(xiyangyang, huitailang);


//        LinkedList<String> list1 = new LinkedList<>();
//        list1.add("张三");
//        list1.add("李四");
//        list1.add("王斌");
//
//        display(list1);
//
//
//        LinkedList<Integer> list2 = new LinkedList<>();
//        list2.add(new Integer(398));
//        list2.add(123);
//
//        display(list2);
//
//        LinkedList<?> list3 = new LinkedList<>();
//        list3.removeFirst();


        mBtnPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.arthurwang.helloworld.one.ACTION_START");

                startActivity(intent);
            }
        });

        mBtnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });

//        testJust();

//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//
//                testCreate();
//            }
//        }.start();

//        testCreate();

//        testInterval();


        testTimer();
    }


//    private void testJust() {
//        Observable.just("123", "222", "1243")
//            .subscribe(this.observer);
//    }
//
//    private void testCreate() {
//        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                Log.e("wyn", "ObservableEmitter");
//
//                Log.e("wyn", "ObservableEmitter thread is " + Thread.currentThread().getName());
//
//
//                long a = 1;
//                for (int i = 0; i < 1000000000; i++) {
//                    a = a + (a + 1);
//                }
//
//                Log.e("wyn", "a is " + a);
//
//                emitter.onNext("wang" + a);
//                emitter.onNext("益南");
//
//                emitter.onComplete();
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(this.observer);
//    }

    private void testInterval() {
        Log.e("wyn", "111");
        Observable.interval(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this.observer);

        Log.e("wyn", "222");
    }

    private void testTimer() {
        Log.e("wyn", "333");

        Observable.timer(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this.observer);

        Log.e("wyn", "444");
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
            {
                Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
            }
                break;

            case R.id.remove_item:
            {
                Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
            }
            break;

            default:
                break;
        }

        return true;
    }

    public void display(LinkedList<?> l) {
        for (Object object : l) {
            KLog.e(object);
        }
    }
}
