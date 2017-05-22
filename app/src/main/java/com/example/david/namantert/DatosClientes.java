package com.example.david.namantert;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by david on 21/05/2017.
 */

public class DatosClientes {
    public static ArrayList<Cliente> traerClientes(Context contexto){
        ArrayList<Cliente> clientes=new ArrayList<>();

        String sql,cedula,nombre,apellido,telefono,ncompras;
        SQLiteDatabase db;
        Cliente cl;

        ClientesSQLiteOpenHelper aux=new ClientesSQLiteOpenHelper(contexto,"DBClientes",null,1);
        db=aux.getReadableDatabase();

        sql="select cedula,nombre,apellido,telefono,ncompras from Clientes";
        Cursor c=db.rawQuery(sql,null);

        if (c.moveToFirst()){
            do {
                cedula=c.getString(0);
                nombre=c.getString(1);
                apellido=c.getString(2);
                telefono=c.getString(3);
                ncompras=c.getString(4);
                cl =new Cliente(cedula,nombre,apellido,telefono,ncompras);
                clientes.add(cl);

            }while (c.moveToNext());
        }
        db.close();
        return clientes;
    }

    public static Cliente buscarClientes(Context contexto,String ced){

        String sql,cedula,nombre,apellido,telefono,ncompras;
        SQLiteDatabase db;
        Cliente cliente=null;

        ClientesSQLiteOpenHelper aux=new ClientesSQLiteOpenHelper(contexto,"DBClientes",null,1);
        db=aux.getReadableDatabase();

        sql="select * from Clientes where cedula='"+ced+"'";
        Cursor c=db.rawQuery(sql,null);

        if (c.moveToFirst()){
                cedula=c.getString(0);
                nombre=c.getString(1);
                apellido=c.getString(2);
                telefono=c.getString(3);
                ncompras=c.getString(4);
                cliente =new Cliente(cedula,nombre,apellido,telefono,ncompras);
        }
        db.close();
        return cliente;
    }
}
