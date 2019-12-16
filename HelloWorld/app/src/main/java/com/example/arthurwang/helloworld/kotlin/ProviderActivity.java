package com.example.arthurwang.helloworld.kotlin;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.example.arthurwang.helloworld.R;
import com.example.arthurwang.helloworld.nov.Book;
import com.example.arthurwang.helloworld.nov.User;
import com.socks.library.KLog;

import static com.example.arthurwang.helloworld.kotlin.BookProvider.BOOK_CONTENT_URI;
import static com.example.arthurwang.helloworld.kotlin.BookProvider.USER_CONTENT_URI;

public class ProviderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);

        ContentResolver contentResolver = getContentResolver();

        if (null == contentResolver) {
            KLog.e("wyn", "contentResolver is null.");
            return;
        }

        Uri bookUri = BOOK_CONTENT_URI;
        ContentValues values = new ContentValues();
        values.put("_id", 6);
        values.put("name", "程序设计的艺术");
        contentResolver.insert(bookUri, values);

        Cursor bookCursor = contentResolver.query(bookUri, new String[]{"_id", "name"}, null, null, null);
        if (null != bookCursor) {
            while (bookCursor.moveToNext()) {
                Book book = new Book();
                book.bookId = bookCursor.getInt(0);
                book.bookName = bookCursor.getString(1);

                KLog.e("wyn", "query book: " + book.toString());
            }
            bookCursor.close();
        }

        Uri userUri = USER_CONTENT_URI;
        Cursor userCursor = contentResolver.query(userUri, new String[]{"_id", "name", "sex"}, null, null, null);
        if (null != userCursor) {
            while (userCursor.moveToNext()) {
                User user = new User();
                user.userId = userCursor.getInt(0);
                user.userName = userCursor.getString(1);
                user.isMale = userCursor.getInt(2) == 1;

                KLog.e("wyn", "query user: " + user.toString());
            }
            userCursor.close();
        }

    }
}
