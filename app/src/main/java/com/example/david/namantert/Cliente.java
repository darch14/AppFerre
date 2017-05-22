package com.example.david.namantert;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by david on 21/05/2017.
 */

public class Cliente {
    private String cedula,nombre,apellido,telefono,ncompras;

    public Cliente( String cedula, String nombre, String apellido, String telefono, String ncompras) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.ncompras = ncompras;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNcompras() {
        return ncompras;
    }

    public void setNcompras(String ncompras) {
        this.ncompras = ncompras;
    }

    public void guardar(Context contexto){
        SQLiteDatabase db;
        String sql;

        ClientesSQLiteOpenHelper aux=new ClientesSQLiteOpenHelper(contexto,"DBClientes",null,1);
        db=aux.getWritableDatabase();

        sql="INSERT INTO Clientes values('"
                +this.getCedula()+"','"
                +this.getNombre()+"','"
                +this.getApellido()+"','"
                +this.getTelefono()+"','"
                +this.getNcompras()+"')";
        db.execSQL(sql);

        db.close();
    }

    public void eliminar(Context contexto){
        SQLiteDatabase db;
        String sql;

        ClientesSQLiteOpenHelper aux=new ClientesSQLiteOpenHelper(contexto,"DBClientes",null,1);
        db=aux.getWritableDatabase();

        sql="DELETE FROM Clientes where cedula ='"+this.getCedula()+"'";
        db.execSQL(sql);

        db.close();
    }

    public void modificar(Context contexto){
        SQLiteDatabase db;
        String sql;

        ClientesSQLiteOpenHelper aux=new ClientesSQLiteOpenHelper(contexto,"DBClientes",null,1);
        db=aux.getWritableDatabase();

        sql="UPDATE Clientes SET nombre='"+this.getNombre()
                +"',apellido='"+this.getApellido()
                +"',telefono='"+this.getTelefono()
                +"',ncompras='"+this.getNcompras()
                +"'where cedula='"+this.getCedula()+"'";
        db.execSQL(sql);

        db.close();
    }
}
