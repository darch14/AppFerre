package com.example.david.namantert;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by david on 15/05/2017.
 */

public class Empleado {
    private String foto;
    private String cedula;
    private String nombre;
    private String apellido;
    private String edad;
    private String puesto;
    private String sexo;

    public Empleado(String foto, String cedula, String nombre, String apellido, String edad, String puesto, String sexo) {
        this.foto = foto;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.puesto = puesto;
        this.sexo = sexo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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

    public String getedad() {
        return edad;
    }

    public void setedad(String edad) {
        this.edad = edad;
    }

    public String getpuesto() {
        return puesto;
    }

    public void setpuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getsexo() {
        return sexo;
    }

    public void setsexo(String sexo) {
        this.sexo = sexo;
    }

    public void guardar(Context contexto){
        //declarar las variables
        SQLiteDatabase db;
        String sql;

        //Abrir la conexion de base de datos en modo escritura
        EmpleadosSQLiteOpenHelper aux=new EmpleadosSQLiteOpenHelper(contexto,"DBEmpleados",null,1);
        db=aux.getWritableDatabase();

        //insertar
        sql="INSERT INTO Empleados values('"
                +this.getFoto()+"','"
                +this.getCedula()+"','"
                +this.getNombre()+"','"
                +this.getApellido()+"','"
                +this.getedad()+"','"
                +this.getpuesto()+"','"
                +this.getsexo()+"')";
        db.execSQL(sql);

        db.close();
    }

    public void eliminar(Context contexto){
        //declarar las variables
        SQLiteDatabase db;
        String sql;

        //Abrir la conexion de base de datos en modo escritura
        EmpleadosSQLiteOpenHelper aux=new EmpleadosSQLiteOpenHelper(contexto,"DBEmpleados",null,1);
        db=aux.getWritableDatabase();

        //insertar
        sql="DELETE FROM Empleados where cedula='"+this.getCedula()+"'";
        db.execSQL(sql);

        db.close();
    }

    public void modificar(Context contexto){
        //declarar las variables
        SQLiteDatabase db;
        String sql;

        //Abrir la conexion de base datos en modo escritura
        EmpleadosSQLiteOpenHelper  aux = new EmpleadosSQLiteOpenHelper(contexto,"DBEmpleados",null,1);
        db = aux.getWritableDatabase();

        //insertar forma 1
        sql = "UPDATE Empleados SET nombre='"+this.getNombre()
                +"', apellido='"+this.getApellido()
                +"', edad='"+this.getedad()
                +"', puesto='"+this.getpuesto()
                +"', sexo='" +this.getsexo()+"' "
                + "where cedula ='"+this.getCedula()+"'";

        db.execSQL(sql);

        //cerrar conexion
        db.close();

    }

}
