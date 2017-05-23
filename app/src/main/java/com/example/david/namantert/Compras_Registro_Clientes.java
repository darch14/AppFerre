package com.example.david.namantert;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Compras_Registro_Clientes extends AppCompatActivity {
    private EditText cajaCedula,cajaNombre,cajaApellido,cajaTelefono;
    private Resources res;
    private TableLayout tabla;
    private ArrayList<Cliente> clien;
    private Intent i;
    private Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras__registro__clientes);

        res=this.getResources();
        cajaCedula=(EditText)findViewById(R.id.txtCedulaCliente);
        cajaNombre=(EditText)findViewById(R.id.txtNombreCliente);
        cajaApellido=(EditText)findViewById(R.id.txtApellidoCliente);
        cajaTelefono=(EditText)findViewById(R.id.txtTelefonoCliente);
        i = new Intent(this,Compra.class);
        b = new Bundle();

        tabla();
    }

    public void tabla(){
        tabla = (TableLayout) findViewById(R.id.tblCliente);
        clien = DatosClientes.traerClientes(getApplicationContext());

        tabla.removeAllViewsInLayout();
        for (int i = 0; i < clien.size()+1; i++) {
            if (i==0){
                TableRow fila = new TableRow(this);
                TextView c1 = new TextView(this);
                TextView c2 = new TextView(this);
                TextView c3 = new TextView(this);
                TextView c4 = new TextView(this);
                TextView c5 = new TextView(this);

                c1.setText(res.getString(R.string.numero));
                c2.setText(res.getString(R.string.cedula));
                c3.setText(res.getString(R.string.nombre));
                c4.setText(res.getString(R.string.telefono));
                c5.setText(res.getString(R.string.numero_compras));

                fila.addView(c1);
                fila.addView(c2);
                fila.addView(c3);
                fila.addView(c4);
                fila.addView(c5);

                tabla.addView(fila);
            }else {
                TableRow fila = new TableRow(this);
                TextView c1 = new TextView(this);
                TextView c2 = new TextView(this);
                TextView c3 = new TextView(this);
                TextView c4 = new TextView(this);
                TextView c5 = new TextView(this);

                c1.setText(""+i);
                c2.setText(clien.get(i-1).getCedula());
                c3.setText(clien.get(i-1).getNombre()+" "+clien.get(i-1).getApellido());
                c4.setText(clien.get(i-1).getTelefono());
                c5.setText(clien.get(i-1).getNcompras());

                fila.addView(c1);
                fila.addView(c2);
                fila.addView(c3);
                fila.addView(c4);
                fila.addView(c5);

                tabla.addView(fila);
            }
        }
    }

    public void borrar(View v){
        limpiar();
    }

    public boolean validar(){
        if (cajaCedula.getText().toString().isEmpty()){
            cajaCedula.setError(res.getString(R.string.error_cajacedula));
            cajaCedula.requestFocus();
            return false;
        }
        if (cajaNombre.getText().toString().isEmpty()){
            cajaNombre.setError(res.getString(R.string.error_cajanombre));
            cajaNombre.requestFocus();
            return false;
        }
        if (cajaApellido.getText().toString().isEmpty()){
            cajaApellido.setError(res.getString(R.string.error_cajaapellido));
            cajaApellido.requestFocus();
            return false;
        }
        if (cajaTelefono.getText().toString().isEmpty()){
            cajaTelefono.setError(res.getString(R.string.error_cajatelefono));
            cajaTelefono.requestFocus();
            return false;
        }
        return true;
    }

    public boolean validarCedulaRepetido(){
        ArrayList<Cliente> c=DatosClientes.traerClientes(getApplicationContext());
        for (int i=0;i<c.size();i++){
            if (c.get(i).getCedula().equalsIgnoreCase(cajaCedula.getText().toString())){
                cajaCedula.setError(res.getString(R.string.error_cedula_existente));
                cajaCedula.requestFocus();
                return false;
            }
        }
        return true;
    }

    public void guardar(View v){
        String cedula,nombre,apellido,telefono,ncompras;
        Cliente cliente;
        if (validar()){
            if (validarCedulaRepetido()){
                cedula=cajaCedula.getText().toString();
                nombre=cajaNombre.getText().toString();
                apellido=cajaApellido.getText().toString();
                telefono=cajaTelefono.getText().toString();
                ncompras=String.valueOf(0);
                cliente=new Cliente(cedula,nombre,apellido,telefono,ncompras);
                cliente.guardar(getApplicationContext());

                Toast.makeText(getApplicationContext(), res.getString(R.string.cliente_guardado),
                        Toast.LENGTH_SHORT).show();
                limpiar();
                tabla();
            }
        }
    }

    public void limpiar(){
        cajaCedula.setText("");
        cajaNombre.setText("");
        cajaApellido.setText("");
        cajaTelefono.setText("");
        cajaCedula.requestFocus();
    }



    public boolean validarCedula(){
        if (cajaCedula.getText().toString().isEmpty()){
            cajaCedula.setError(res.getString(R.string.error_cajacedula));
            cajaCedula.requestFocus();
            return false;
        }
        return true;
    }

    public void buscar(View v){
        Cliente c;
        if (validarCedula()){
            c=DatosClientes.buscarClientes(getApplicationContext(),cajaCedula.getText().toString());
            if (c!=null){
                cajaNombre.setText(c.getNombre());
                cajaApellido.setText(c.getApellido());
                cajaTelefono.setText(c.getTelefono());
            }else {
                Toast.makeText(getApplicationContext(), res.getString(R.string.cliente_existe),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void eliminar(View v){
        Cliente c;
        if (validarCedula()){
            c=DatosClientes.buscarClientes(getApplicationContext(),cajaCedula.getText().toString());
            if (c!=null){
                AlertDialog.Builder ventana = new AlertDialog.Builder(this);
                ventana.setTitle(res.getString(R.string.confirmacion));
                ventana.setMessage(res.getString(R.string.mensaje_confirmacion_cliente));

                ventana.setPositiveButton(res.getString(R.string.confirmar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Cliente c;
                        c=DatosClientes.buscarClientes(getApplicationContext(),cajaCedula.getText().toString());

                        c.eliminar(getApplicationContext());
                        limpiar();
                        tabla();
                        Toast.makeText(getApplicationContext(), res.getString(R.string.cliente_eliminado),
                                Toast.LENGTH_SHORT).show();
                    }
                });

                ventana.setNegativeButton(res.getString(R.string.cancelar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cajaCedula.requestFocus();
                    }
                });

                ventana.show();
            }else {
                Toast.makeText(getApplicationContext(), res.getString(R.string.cliente_existe),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void modificar(View v){
        Cliente p,p2;
        String nombre,apellido,telefono;
        if(validarCedula()) {
            p = DatosClientes.buscarClientes(getApplicationContext(), cajaCedula.getText().toString());
            if(p!=null){

                nombre = cajaNombre.getText().toString();
                apellido=cajaApellido.getText().toString();
                telefono=cajaTelefono.getText().toString();
                p2 = new Cliente(p.getCedula(),nombre,apellido,telefono,p.getNcompras());
                p2.modificar(getApplicationContext());

                Toast.makeText(getApplicationContext(), res.getString(R.string.cliente_modificado),
                        Toast.LENGTH_SHORT).show();
                limpiar();
                tabla();
            }else {
                Toast.makeText(getApplicationContext(), res.getString(R.string.cliente_existe),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void compra(View v){
        Cliente c;
        if (validarCedula()){
            c=DatosClientes.buscarClientes(getApplicationContext(),cajaCedula.getText().toString());
            if (c!=null){
                b.putString("cedula",c.getCedula());
                i.putExtras(b);
                startActivity(i);
            }else {
                Toast.makeText(getApplicationContext(), res.getString(R.string.cliente_existe),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

}
