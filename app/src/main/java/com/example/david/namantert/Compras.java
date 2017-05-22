package com.example.david.namantert;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by david on 22/05/2017.
 */

public class Compras {
    private String cliente,material,precio;

    public Compras(String cliente, String material, String precio) {
        this.cliente = cliente;
        this.material = material;
        this.precio = precio;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
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

        CompraSQLiteOpenHelper aux=new CompraSQLiteOpenHelper(contexto,"DBCompras",null,1);
        db=aux.getWritableDatabase();

        sql="INSERT INTO Compras values('"
                +this.getCliente()+"','"
                +this.getMaterial()+"','"
                +this.getPrecio()+"')";
        db.execSQL(sql);

        db.close();
    }

}
