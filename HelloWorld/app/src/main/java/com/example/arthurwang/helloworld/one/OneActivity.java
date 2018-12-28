package com.example.arthurwang.helloworld.one;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.arthurwang.helloworld.R;
import com.socks.library.KLog;

import java.util.LinkedList;

public class OneActivity extends AppCompatActivity {

    private Button mBtnPush;
    private Button mBtnWeb;

    private void assignViews() {
        mBtnPush = (Button) findViewById(R.id.btn_push);
        mBtnWeb = (Button) findViewById(R.id.btn_web);
    }



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
