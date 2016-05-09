package com.mikhail.sportsnewshistoryrecords.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.mikhail.sportsnewshistoryrecords.fragments.SavedArticleRecycleView;


/**
 * Created by Mikhail on 5/6/16.
 */
public class SaveSQLiteHelper extends SQLiteOpenHelper implements BaseColumns {

    private static final String TAG = SaveSQLiteHelper.class.getCanonicalName();

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SAVED_DB";
    public static final String ARTICLES_TABLE_NAME = "ARTICLES_DB";


    public static final String COL_ID = "_id";
    public static final String COL_HTML = "HTML";
    public static final String COL_TITLE = "TITLE";
    public static final String COL_SNIPPET = "SNIPPET";
    public static final String COL_URL = "URL";
    public static final String COL_IMAGE = "IMAGE";
    public static final String COL_CODE = "CODE";


    public static final String[] ARTICLES_COLUMNS = {COL_ID, COL_HTML, COL_TITLE, COL_SNIPPET, COL_URL, COL_IMAGE, COL_CODE};

    private static SaveSQLiteHelper instance;


    private static final String CREATE_ARTICLES_TABLE =
            "CREATE TABLE " + ARTICLES_TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_HTML + " TEXT, " +
                    COL_TITLE + " TEXT, " +
                    COL_SNIPPET + " TEXT, " +
                    COL_URL + " TEXT, " +
                    COL_IMAGE + " TEXT, " +
                    COL_CODE + " LONG) ";


    public static SaveSQLiteHelper getInstance(Context context) {
        if (instance == null) {
            instance = new SaveSQLiteHelper(context);
        }
        return instance;
    }

    public SaveSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ARTICLES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ARTICLES_TABLE_NAME);
        this.onCreate(db);
    }


    public Cursor getAllSavedArticles(){

        SQLiteDatabase db = this.getReadableDatabase();
        String[] savedArticleNoHtml = {COL_ID, COL_TITLE, COL_SNIPPET, COL_URL, COL_IMAGE, COL_CODE};

        Cursor cursor = db.query(ARTICLES_TABLE_NAME, // a. table
                savedArticleNoHtml, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                COL_CODE + " DESC", // g. order by
                null); // h. limit
        return cursor;
    }

    public Cursor getArticleHtml(String Id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] query = {Id};

        Cursor cursor = db.query(ARTICLES_TABLE_NAME, // a. table
                ARTICLES_COLUMNS, // b. column names
                COL_ID + " = ?", // c. selections
                query, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        return cursor;
    }

    public static int checkURLforDuplicate(String url, Cursor cursor){
        int count = 0;
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            count++;
            if(url.equals(cursor.getString(cursor.getColumnIndex(SaveSQLiteHelper.COL_URL)))) {
                return count;
            }
            cursor.moveToNext();
        }
        if(count == SavedArticleRecycleView.savedArticleLimit){
            return -1;
        }
        return 0;
    }
}
