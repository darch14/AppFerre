package com.example.david.namantert;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by david on 22/05/2017.
 */

public class CarroCompra {
    private String ident,nomaterial,cantidad,precio;

    public CarroCompra(String ident,String nomaterial, String cantidad, String precio) {
        this.ident=ident;
        this.nomaterial = nomaterial;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getNomaterial() {
        return nomaterial;
    }

    public void setNomaterial(String nomaterial) {
        this.nomaterial = nomaterial;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void guardar(Context contexto){
        SQLiteDatabase db;
        String sql;

        CarroCompraSQLiteOpenHelper aux=new CarroCompraSQLiteOpenHelper(contexto,"DBCarroscompras",null,1);
        db=aux.getWritableDatabase();

        sql="INSERT INTO Carroscompras values('"
                +this.getIdent()+"','"
                +this.getNomaterial()+"','"
                +this.getCantidad()+"','"
                +this.getPrecio()+"')";
        db.execSQL(sql);

        db.close();
    }

    public void eliminar(Context contexto){
        SQLiteDatabase db;
        String sql;

        CarroCompraSQLiteOpenHelper aux=new CarroCompraSQLiteOpenHelper(contexto,"DBCarroscompras",null,1);
        db=aux.getWritableDatabase();

        sql="DELETE FROM Carroscompras where ident='"+this.getIdent()+"'";
        db.execSQL(sql);

        db.close();
    }
}
