package com.example.arthurwang.helloworld.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.arthurwang.helloworld.R;
import com.socks.library.KLog;

import org.litepal.LitePal;

import java.util.List;

public class MySqlActivity extends Activity {

    private MyDatabaseHelper myDatabaseHelper;


    private Button mBtnCreateSql;
    private Button mBtnRetrieve;
    private Button mBtnUpdate;
    private Button mBtnDelete;
    private Button mBtnInsert;

    private void assignViews() {
        mBtnCreateSql = (Button) findViewById(R.id.btn_create_sql);
        mBtnRetrieve = (Button) findViewById(R.id.btn_retrieve);
        mBtnUpdate = (Button) findViewById(R.id.btn_update);
        mBtnDelete = (Button) findViewById(R.id.btn_delete);
        mBtnInsert = (Button) findViewById(R.id.btn_insert);
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sql);

        assignViews();

        mBtnCreateSql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();
            }
        });

        mBtnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("THe Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);

                book.save();
            }
        });

        mBtnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = LitePal.findAll(Book.class);

                for (Book book: books) {
                    KLog.e("book.getName() is " + book.getName());
                    KLog.e("book.getAuthor() is " + book.getAuthor());
                    KLog.e("book.getPages() is " + book.getPages());
                    KLog.e("book.getPrice() is " + book.getPrice());
                    KLog.e("book.getPress() is " + book.getPress());
                }
            }
        });

//        myDatabaseHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);
//
//        mBtnCreateSql.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myDatabaseHelper.getWritableDatabase();
//            }
//        });
//
//        mBtnInsert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
//                ContentValues values = new ContentValues();
//
//                values.put("name", "ThemedSpinnerAdapter Da Vinci Code");
//                values.put("author", "Dan Brown");
//                values.put("pages", 45);
//                values.put("price", 16.96);
//
//                db.insert("Book", null, values);
//
//                values.clear();
//                values.put("name", "The Lost Symbol");
//                values.put("author", "Dan Brown");
//                values.put("pages", 510);
//                values.put("price", 19.95);
//                db.insert("Book", null, values);
//            }
//        });
//
//        mBtnRetrieve.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
//
//                Cursor cursor = db.query("Book", null, null, null, null, null, null);
//
//                if (cursor.moveToFirst()) {
//                    do {
//                        String name = cursor.getString(cursor.getColumnIndex("name"));
//                        String author = cursor.getString(cursor.getColumnIndex("author"));
//                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
//                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
//
//                        KLog.e("name is " + name);
//                        KLog.e("author is " + author);
//                        KLog.e("pages is " + pages);
//                        KLog.e("price is " + price);
//                    } while (cursor.moveToNext());
//                }
//
//                cursor.close();
//            }
//        });

    }
}
