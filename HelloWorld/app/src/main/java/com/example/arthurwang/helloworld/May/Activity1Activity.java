import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.arthurwang.helloworld.Activity2Activity;
import com.example.arthurwang.helloworld.R;
import com.socks.library.KLog;

public class Activity1Activity extends Activity {

    TextView textview2;
    EditText editText1;
    Button   button1;
    EditText editText2;
    Button   button2;
    TextView textView3;


    private static final String ext_name = "name";

    String str = new String("good");
    char[] ch = {'a','b','c'};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity1);

        // 初始化视图
        textview2 = (TextView) findViewById(R.id.textview2);
        editText1 = (EditText) findViewById(R.id.editText1);
        button1 = (Button) findViewById(R.id.clickMe);
        editText2 = (EditText) findViewById(R.id.editText2);
        button2 = (Button) findViewById(R.id.clickMeToJump);
        textView3 = (TextView) findViewById(R.id.textview3);

        // 添加事件
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                textview2.setText(editText1.getText());
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText2.setText(editText1.getText());
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KLog.e("editText2.getText() is " + editText2.getText());

                Intent intent = new Intent();
                intent.putExtra(ext_name, editText2.getText().toString());
                intent.setClass(Activity1Activity.this, Activity2Activity.class);

                startActivityForResult(intent, 1);
            }
        });

        change(str, ch);

        KLog.e("测试下哈：" + str + " and " + ch[0] + ch[1] + ch[2]);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        KLog.e("data is " + data.toString());

        if (null != data) {
            Bundle bundle = data.getExtras();
            String name = bundle.getString(ext_name);

            textView3.setText(name);
        }

    }


    public void change(String str, char ch[]){
        str = "test ok";
        ch[0]= 'g';
    }


}
