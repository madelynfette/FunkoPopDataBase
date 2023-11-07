package com.mobile;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class PopDBProvider extends ContentProvider {

    public final static String DBNAME = "FunkoPOPDB";

    protected static final class MainDatabaseHelper extends SQLiteOpenHelper {
        MainDatabaseHelper(Context context) {
            super(context, DBNAME, null, 2);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_MAIN);
        }
        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        }
    };


    public final static String TABLE_NAMESTABLE = "Pops";
    public final static String FIRSTCOLUMN = "name";
    public final static String SECONDCOLUMN = "number";
    public final static String THIRDCOLUMN = "type";
    public final static String FOURTHCOLUMN = "fandom";
    public final static String FIFTHCOLUMN = "ONI";
    public final static String SIXTHCOLUMN = "ultimate";
    public final static String SEVENTHCOLUMN = "price";


    public static final String AUTHORITY = "com.mobile.provider";
    public static final Uri CONTENT_URI = Uri.parse(
            "content://" + AUTHORITY +"/" + TABLE_NAMESTABLE);

    public static UriMatcher sUriMatcher;

    public MainDatabaseHelper mOpenHelper;

    public static final String SQL_CREATE_MAIN = "CREATE TABLE " +
            TABLE_NAMESTABLE +
            "(" +
            " _ID INTEGER PRIMARY KEY, " +
            FIRSTCOLUMN + " TEXT," +
            SECONDCOLUMN + " TEXT," +
            THIRDCOLUMN + " TEXT," +
            FOURTHCOLUMN + " TEXT," +
            FIFTHCOLUMN + " TEXT," +
            SIXTHCOLUMN + " TEXT," +
            SEVENTHCOLUMN + " TEXT)";



    public PopDBProvider() {

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return mOpenHelper.getWritableDatabase().delete(TABLE_NAMESTABLE, selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String name = values.getAsString(FIRSTCOLUMN).trim();
        String number = values.getAsString(SECONDCOLUMN).trim();
        String type = values.getAsString(THIRDCOLUMN).trim();
        String fandom = values.getAsString(FOURTHCOLUMN).trim();
        String on = values.getAsString(FIFTHCOLUMN).trim();
        String ultimate = values.getAsString(SIXTHCOLUMN).trim();
        String price = values.getAsString(SEVENTHCOLUMN).trim();


        if (name.equals(""))
            return null;
        if (number.equals(""))
            return null;
        if (type.equals(""))
            return null;
        if (fandom.equals(""))
            return null;
        if (on.equals(""))
            return null;
        if (ultimate.equals(""))
            return null;
        if (price.equals(""))
            return null;

        long id = mOpenHelper.getWritableDatabase().insert(TABLE_NAMESTABLE, null, values);

        return Uri.withAppendedPath(CONTENT_URI, "" + id);
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new MainDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return mOpenHelper.getReadableDatabase().query(TABLE_NAMESTABLE, projection, selection, selectionArgs,
                null, null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return mOpenHelper.getWritableDatabase().update(TABLE_NAMESTABLE, values, selection, selectionArgs);
    }
}
