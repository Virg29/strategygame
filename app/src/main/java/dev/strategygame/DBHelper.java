package dev.strategygame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by danila on 26.03.17.
 */

class DBHelper extends SQLiteOpenHelper {
    private SQLiteDatabase gameReadableDb = getReadableDatabase();

    private SQLiteDatabase game = getWritableDatabase();

    private int longOfAnswerMassive = 128;


    public DBHelper(Context context){
        super(context, "database", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL("create table levels ("
                + "id integer primary key autoincrement,"
                + "path text,"
                + "stage text,"
                + "state double,"
                + "globalStage" + ");");

        game = db;

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int v1, int v2){
        db.execSQL("DROP TABLE IF EXISTS + TABLE_NAME");
        onCreate(db);
    }

    String[] getAllParams(String id){       //тут отдаются все 5 параметров уровня
        String[] out = new String[5];
        Cursor cursor = gameReadableDb.rawQuery("SELECT id,path,stage,state,globalStage FROM `levels` WHERE id="+id,null);
        out[0]= cursor.getString(cursor.getColumnIndex("id"));
        out[1]= cursor.getString(cursor.getColumnIndex("path"));
        out[2]= cursor.getString(cursor.getColumnIndex("stage"));
        out[3]= cursor.getString(cursor.getColumnIndex("state"));
        out[4]= cursor.getString(cursor.getColumnIndex("globalStage"));
        return out;
    }





}
