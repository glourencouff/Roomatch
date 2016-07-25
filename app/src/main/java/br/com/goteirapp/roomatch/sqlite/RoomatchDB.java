package br.com.goteirapp.roomatch.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Gabriel on 21/07/2016.
 */
public class RoomatchDB {

    private final RoomatchDBHelper roomatchDBHelper;


    public RoomatchDB(Context context, SQLiteDatabase db) {
        roomatchDBHelper = new RoomatchDBHelper(context);
        conectar(db);
    }

    private void conectar(SQLiteDatabase db){
        if (db == null){
            db = roomatchDBHelper.getWritableDatabase();
        }
    }

    public void fechar(SQLiteDatabase db){
        if (db != null){
            db.close();
            db = null;
        }
    }
}
