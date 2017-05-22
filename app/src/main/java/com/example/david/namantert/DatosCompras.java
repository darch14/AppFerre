package com.example.david.namantert;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by david on 22/05/2017.
 */

public class DatosCompras {
    public static ArrayList<Compras> traerCompras(Context contexto){
        ArrayList<Compras> compras=new ArrayList<>();

        String sql,cliente,material,precio;
        SQLiteDatabase db;
        Compras co;

        CompraSQLiteOpenHelper aux=new CompraSQLiteOpenHelper(contexto,"DBCompras",null,1);
        db=aux.getReadableDatabase();

        sql="select cliente,material,precio from Compras";
        Cursor c=db.rawQuery(sql,null);

        if (c.moveToFirst()){
            do {
                cliente=c.getString(0);
                material=c.getString(1);
                precio=c.getString(2);
                co=new Compras(cliente,material,precio);
                compras.add(co);

            }while (c.moveToNext());
        }
        db.close();
        return compras;
    }
}
