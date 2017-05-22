package com.example.david.namantert;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by david on 22/05/2017.
 */

public class CarroCompraSQLiteOpenHelper  extends SQLiteOpenHelper{
    private String sql="CREATE TABLE Carroscompras(ident text,nomaretial text,cantidad text,precio text)";

    public CarroCompraSQLiteOpenHelper(Context contexto, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE EXISTS Carroscompras");
        db.execSQL(sql);
    }
}
