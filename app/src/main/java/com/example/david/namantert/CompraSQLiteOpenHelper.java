package com.example.david.namantert;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by david on 22/05/2017.
 */

public class CompraSQLiteOpenHelper extends SQLiteOpenHelper{
    private String sql="CREATE TABLE Compras(cliente text,material text,precio text)";

    public CompraSQLiteOpenHelper(Context contexto, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE EXISTS Compras");
        db.execSQL(sql);
    }
}
