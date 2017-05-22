package com.example.david.namantert;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by david on 19/05/2017.
 */

public class DatosMateriales {
    public static ArrayList<Material> traerMateriales(Context contexto){
        ArrayList<Material> materials=new ArrayList<>();

        //Declarar variables
        SQLiteDatabase db;
        String sql,foto,codigo,tipo,nombre,precio,cantidad;
        Material m;

        //Abrir conexion de lectura
        MaterialesSQLiteOpenHelper aux=new MaterialesSQLiteOpenHelper(contexto,"DBMateriales",null,1);
        db=aux.getReadableDatabase();

        //Cursor
        sql="select foto, codigo, tipo, nombre, precio, cantidad from Materiales";
        Cursor c=db.rawQuery(sql,null);

        //Recorrido del cursor
        if (c.moveToFirst()){
            do {
                foto=c.getString(0);
                codigo=c.getString(1);
                tipo=c.getString(2);
                nombre=c.getString(3);
                precio=c.getString(4);
                cantidad=c.getString(5);
                m=new Material(foto,codigo,tipo,nombre,precio,cantidad);
                materials.add(m);
            }while (c.moveToNext());
        }
        db.close();
        return materials;
    }

    public static Material buscarMateriales(Context contexto,String cod){

        //Declarar variables
        SQLiteDatabase db;
        String sql,foto,codigo,tipo,nombre,precio,cantidad;
        Material m=null;

        //Abrir conexion de lectura
        MaterialesSQLiteOpenHelper aux=new MaterialesSQLiteOpenHelper(contexto,"DBMateriales",null,1);
        db=aux.getReadableDatabase();

        //Cursor
        sql="select * from Materiales where codigo ='"+cod+"'";
        Cursor c=db.rawQuery(sql,null);

        //Recorrido del cursor
        if (c.moveToFirst()){
            foto=c.getString(0);
            codigo=c.getString(1);
            tipo=c.getString(2);
            nombre=c.getString(3);
            precio=c.getString(4);
            cantidad=c.getString(5);
            m=new Material(foto,codigo,tipo,nombre,precio,cantidad);
        }
        db.close();
        return m;
    }

    public static Material buscarPorNombreMateriales(Context contexto,String nom){

        //Declarar variables
        SQLiteDatabase db;
        String sql,foto,codigo,tipo,nombre,precio,cantidad;
        Material m=null;

        //Abrir conexion de lectura
        MaterialesSQLiteOpenHelper aux=new MaterialesSQLiteOpenHelper(contexto,"DBMateriales",null,1);
        db=aux.getReadableDatabase();

        //Cursor
        sql="select * from Materiales where nombre ='"+nom+"'";
        Cursor c=db.rawQuery(sql,null);

        //Recorrido del cursor
        if (c.moveToFirst()){
            foto=c.getString(0);
            codigo=c.getString(1);
            tipo=c.getString(2);
            nombre=c.getString(3);
            precio=c.getString(4);
            cantidad=c.getString(5);
            m=new Material(foto,codigo,tipo,nombre,precio,cantidad);
        }
        db.close();
        return m;
    }

}
