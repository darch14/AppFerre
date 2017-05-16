package com.example.david.namantert;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by david on 15/05/2017.
 */

public class DatosEmpleados {
    public static ArrayList<Empleado> traerEmpleados(Context contexto){
        ArrayList<Empleado> empleados=new ArrayList<>();

        //Declarar variables
        SQLiteDatabase db;
        String sql,foto,cedula,nombre,apellido,edad,puesto,sexo;
        Empleado e;

        //Abrir conexion de lectura
        EmpleadosSQLiteOpenHelper aux=new EmpleadosSQLiteOpenHelper(contexto,"DBEmpleados",null,1);
        db=aux.getReadableDatabase();

        //Cursor
        sql="select foto, cedula, nombre, apellido, edad, puesto, sexo from Empleados";
        Cursor c=db.rawQuery(sql,null);

        //Recorrido del cursor
        if (c.moveToFirst()){
            do {
                foto=c.getString(0);
                cedula=c.getString(1);
                nombre=c.getString(2);
                apellido=c.getString(3);
                edad=c.getString(4);
                puesto=c.getString(5);
                sexo=c.getString(6);
                e=new Empleado(foto,cedula,nombre,apellido,edad,puesto,sexo);
                empleados.add(e);
            }while (c.moveToNext());
        }
        db.close();
        return empleados;
    }

    public static Empleado buscarEmpleados(Context contexto,String ced){

        //Declarar variables
        SQLiteDatabase db;
        String sql,foto,cedula,nombre,apellido,edad,puesto,sexo;
        Empleado e=null;

        //Abrir conexion de lectura
        EmpleadosSQLiteOpenHelper aux=new EmpleadosSQLiteOpenHelper(contexto,"DBEmpleados",null,1);
        db=aux.getReadableDatabase();

        //Cursor
        sql="select * from Empleados where cedula ='"+ced+"'";
        Cursor c=db.rawQuery(sql,null);

        //Recorrido del cursor
        if (c.moveToFirst()){
            foto=c.getString(0);
            cedula=c.getString(1);
            nombre=c.getString(2);
            apellido=c.getString(3);
            edad=c.getString(4);
            puesto=c.getString(5);
            sexo=c.getString(6);
            e=new Empleado(foto,cedula,nombre,apellido,edad,puesto,sexo);

        }
        db.close();
        return e;
    }
}
