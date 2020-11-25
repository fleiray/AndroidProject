package com.example.androidassignments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class ChatDatabaseHelper extends SQLiteOpenHelper {
    public static SQLiteDatabase database;
    public static final String DATABASE_NAME = "Messages.db";
    private static final int VERSION_NUM = 3;
    public static final String TableName = "ChatTable";
    public static final String KEY_ID = "key_id";
    public static final String KEY_MESSAGE = "key_message";
    public ChatDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUM);
    }
    private static final String TABLE_CREATE_SQL = "create table "
            + TableName + "(" + KEY_ID
            + " integer primary key autoincrement, " + KEY_MESSAGE
            + " text not null);";
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("ChatDatabaseHelper", "Calling onCreate");
        db.execSQL(TABLE_CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVersion=" + oldVersion + "newVersion=" + newVersion);
        Log.w(ChatDatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }
//    public void open() throws SQLException{
//        database = this.getWritableDatabase();
//    }
//
//    public boolean addMessage(String message){
//        database = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(KEY_MESSAGE,message);
//        return database.insert(TableName, null, contentValues) != -1;
//    }
//
//    public Cursor getMessages(){
//        database = this.getReadableDatabase();
//        String sql = "SELECT * FROM "+ TableName+";";
//        return database.rawQuery(sql,null);
//    }
//
//    public void deleteMessage(int id){
//        database = this.getWritableDatabase();
//        String sql = "DELETE FROM "+TableName+" WHERE key_id="+id+";";
//        database.execSQL(sql);
//    }
    public void onDowngrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
        Log.i("ChatDatabaseHelper", "Why you Downgrade?, oldVersion=" + i + " newVersion=" + i1);
    }
}
