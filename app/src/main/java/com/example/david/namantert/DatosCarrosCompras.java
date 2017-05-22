package com.example.david.namantert;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by david on 22/05/2017.
 */

public class DatosCarrosCompras {
    public static ArrayList<CarroCompra> traerCarrosCompras(Context contexto){
        ArrayList<CarroCompra> carroCompras=new ArrayList<>();

        String sql,nomaterial,cantidad,precio,ident;
        SQLiteDatabase db;
        CarroCompra ca;

        CarroCompraSQLiteOpenHelper aux=new CarroCompraSQLiteOpenHelper(contexto,"DBCarroscompras",null,1);
        db=aux.getReadableDatabase();

        sql="select * from Carroscompras";
        Cursor c=db.rawQuery(sql,null);

        if (c.moveToFirst()){
            do {
                ident=c.getString(0);
                nomaterial=c.getString(1);
                cantidad=c.getString(2);
                precio=c.getString(3);
                ca=new CarroCompra(ident,nomaterial,cantidad,precio);
                carroCompras.add(ca);

            }while (c.moveToNext());
        }
        db.close();
        return carroCompras;
    }

    public static CarroCompra buscarCarrosCompras(Context contexto,String id){

        String sql,nomaterial,cantidad,precio,ident;
        SQLiteDatabase db;
        CarroCompra ca=null;

        CarroCompraSQLiteOpenHelper aux=new CarroCompraSQLiteOpenHelper(contexto,"DBCarroscompras",null,1);
        db=aux.getReadableDatabase();

        sql="select * from Carroscompras where ident='"+id+"'";
        Cursor c=db.rawQuery(sql,null);

        if (c.moveToFirst()){
                ident=c.getString(0);
                nomaterial=c.getString(1);
                cantidad=c.getString(2);
                precio=c.getString(3);
                ca=new CarroCompra(ident,nomaterial,cantidad,precio);
        }
        db.close();
        return ca;
    }
}
