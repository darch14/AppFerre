package com.example.david.namantert;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by david on 15/05/2017.
 */

public class EmpleadosSQLiteOpenHelper extends SQLiteOpenHelper {
    private String sql="CREATE TABLE Empleados(foto text,cedula text,nombre text,apellido text,edad text,puesto text,sexo text)";

    public EmpleadosSQLiteOpenHelper(Context contexto, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE EXISTS Empleados");
        db.execSQL(sql);
    }
}
