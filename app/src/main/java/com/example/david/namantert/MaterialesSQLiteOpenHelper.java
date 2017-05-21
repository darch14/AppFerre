package com.example.david.namantert;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by david on 19/05/2017.
 */

public class MaterialesSQLiteOpenHelper extends SQLiteOpenHelper{
    String sql="CREATE TABLE Materiales(foto text,codigo text,tipo text, nombre text,precio text,cantidad text)";

    public MaterialesSQLiteOpenHelper(Context contexto, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE EXISTS Materiales");
        db.execSQL(sql);
    }
}
