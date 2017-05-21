package com.example.david.namantert;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by david on 19/05/2017.
 */

public class Material {
    private String foto,codigo,tipo,nombre,precio,cantidad;

    public Material(String foto, String codigo, String tipo, String nombre, String precio, String cantidad) {
        this.foto = foto;
        this.codigo = codigo;
        this.tipo = tipo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public void guardar(Context contexto){
        SQLiteDatabase db;
        String sql;

        MaterialesSQLiteOpenHelper aux=new MaterialesSQLiteOpenHelper(contexto,"DBMateriales",null,1);
        db=aux.getWritableDatabase();

        sql="INSERT INTO Materiales values('"
                +this.getFoto()+"','"
                +this.getCodigo()+"','"
                +this.getTipo()+"','"
                +this.getNombre()+"','"
                +this.getPrecio()+"','"
                +this.getCantidad()+"')";
        db.execSQL(sql);

        db.close();
    }

    public void eliminar(Context contexto){
        SQLiteDatabase db;
        String sql;

        MaterialesSQLiteOpenHelper aux=new MaterialesSQLiteOpenHelper(contexto,"DBMateriales",null,1);
        db=aux.getWritableDatabase();

        sql="DELETE FROM Materiales where codigo ='"+this.getCodigo()+"'";
        db.execSQL(sql);

        db.close();
    }

    public void modificar(Context contexto){
        SQLiteDatabase db;
        String sql;

        MaterialesSQLiteOpenHelper aux=new MaterialesSQLiteOpenHelper(contexto,"DBMateriales",null,1);
        db=aux.getWritableDatabase();

        sql="UPDATE Materiales SET tipo='"+this.getTipo()
                +"',nombre='"+this.getNombre()
                +"',precio='"+this.getPrecio()
                +"',cantidad='"+this.getCantidad()
                +"'where codigo='"+this.getCodigo()+"'";
        db.execSQL(sql);

        db.close();
    }
}
