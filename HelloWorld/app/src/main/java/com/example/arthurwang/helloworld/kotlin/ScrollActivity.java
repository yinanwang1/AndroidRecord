package com.example.arthurwang.helloworld.kotlin;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arthurwang.helloworld.R;
import com.example.arthurwang.helloworld.nov.utils.MyUtils;
import com.socks.library.KLog;

import java.util.ArrayList;

public class ScrollActivity extends Activity {

    private HorizontalScrollViewEx2 mListContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        initView();
    }


    private void initView() {
        LayoutInflater inflater = getLayoutInflater();
        mListContainer = findViewById(R.id.container);
        final int screenWidth = MyUtils.getScreenMetrics(this).widthPixels;
        final int screenHeight = MyUtils.getScreenMetrics(this).heightPixels;
        for (int i = 0; i < 3; i++) {
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.content_layout2, mListContainer, false);
            layout.getLayoutParams().width = screenWidth;
            TextView textView = layout.findViewById(R.id.title);
            textView.setText("page " + (i + 1));
            textView.setBackgroundColor(Color.rgb(255/(1+1), 255/(i + 1), 0));
            createList(layout);
            mListContainer.addView(layout);
        }
    }

    private void createList(ViewGroup layout) {
        ListViewEx listView = layout.findViewById(R.id.list);
        ArrayList<String> datas = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            datas.add("name " + i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.content_list_item, R.id.name, datas);
        listView.setAdapter(adapter);
        listView.setmHorizontalScrollViewEx2(mListContainer);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ScrollActivity.this, "click item", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        KLog.e("wyn", "dispatchTouch action: " + ev.getAction());

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        KLog.e("wyn", "onTouchEvent action: " + event.getAction());

        return super.onTouchEvent(event);
    }
}
