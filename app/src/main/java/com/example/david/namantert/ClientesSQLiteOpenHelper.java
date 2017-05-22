package com.example.david.namantert;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by david on 21/05/2017.
 */

public class ClientesSQLiteOpenHelper extends SQLiteOpenHelper{
    private String sql="CREATE TABLE Clientes(cedula text,nombre text,apellido text,telefono text,ncompras text)";

    public ClientesSQLiteOpenHelper(Context contexto, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE EXISTS Clientes");
        db.execSQL(sql);
    }
}
