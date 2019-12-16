package com.example.arthurwang.helloworld.kotlin;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.socks.library.KLog;

import static com.example.arthurwang.helloworld.kotlin.DbOpenHelper.BOOK_TABLE_NAME;
import static com.example.arthurwang.helloworld.kotlin.DbOpenHelper.USER_TABLE_NAME;

/**
 * Created by arthurwang on 2019-12-13
 */
public class BookProvider extends ContentProvider {
    private static final String AUTHORITY = "com.example.arthurwang.helloworld.provider";
    public static final Uri BOOK_CONTENT_URI  = Uri.parse("content://" + AUTHORITY + "/book");
    public static final Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/user");

    private static final int BOOK_URI_CODE = 0;
    private static final int USER_URI_CODE = 1;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private Context mContext;
    private SQLiteDatabase mDb;

    static {
        sUriMatcher.addURI(AUTHORITY, "book", BOOK_URI_CODE);
        sUriMatcher.addURI(AUTHORITY, "user", USER_URI_CODE);
    }

    @Override
    public boolean onCreate() {
        KLog.e("wyn", "onCreate, current thread: " + Thread.currentThread().getName());

        mContext = getContext();

        initProviderData();

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        KLog.e("wyn", "query, current thread: " + Thread.currentThread().getName());

        String table = getTableName(uri);
        if (null == table) {
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        return mDb.query(table, projection, selection, selectionArgs, null, null, sortOrder, null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        KLog.e("wyn", "getType, current thread: " + Thread.currentThread().getName());
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        KLog.e("wyn", "insert, current thread: " + Thread.currentThread().getName());

        String table = getTableName(uri);
        if (table == null) {
            throw  new IllegalArgumentException("Unsupported URI: " + uri);
        }

        mDb.insert(table, null, values);
        mContext.getContentResolver().notifyChange(uri, null);

        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        KLog.e("wyn", "delete, current thread: " + Thread.currentThread().getName());

        String table = getTableName(uri);
        if (table == null) {
            throw  new IllegalArgumentException("Unsupported URI: " + uri);
        }

        int count = mDb.delete(table, selection, selectionArgs);
        if (count > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        KLog.e("wyn", "update, current thread: " + Thread.currentThread().getName());

        String table = getTableName(uri);
        if (table == null) {
            throw  new IllegalArgumentException("Unsupported URI: " + uri);
        }

        int row = mDb.update(table, values, selection, selectionArgs);
        if (row > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }


        return row;
    }


    private void initProviderData() {
        mDb = new DbOpenHelper(mContext).getWritableDatabase();
        mDb.execSQL("delete from " + BOOK_TABLE_NAME);
        mDb.execSQL("delete from " + USER_TABLE_NAME);
        mDb.execSQL("insert into book values(3, 'Android');");
        mDb.execSQL("insert into book values(4, 'iOS');");
        mDb.execSQL("insert into book values(5, 'Html5');");
        mDb.execSQL("insert into user values(1, 'jake', 1);");
        mDb.execSQL("insert into user values(2, 'jasmine', 0);");
    }

    private String getTableName(Uri uri) {
        String tableName = null;
        switch (sUriMatcher.match(uri)) {
            case BOOK_URI_CODE:
                tableName = BOOK_TABLE_NAME;
                break;
            case USER_URI_CODE:
                tableName = USER_TABLE_NAME;
                break;

                default:
                    break;
        }

        return tableName;
    }
}
