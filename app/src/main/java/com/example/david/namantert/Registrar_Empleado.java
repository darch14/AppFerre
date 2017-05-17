package com.example.david.namantert;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Registrar_Empleado extends AppCompatActivity {
    private EditText cajaCedula,cajaNombre,cajaApellido,cajaEdad;
    private Spinner comboPuesto;
    private RadioButton rMasculino,rFemanino;
    private Resources res;
    private String[] opc;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar__empleado);

        res=this.getResources();
        cajaCedula=(EditText)findViewById(R.id.txtCedulaEmpleado);
        cajaNombre=(EditText)findViewById(R.id.txtNombreEmpleado);
        cajaApellido=(EditText)findViewById(R.id.txtApellidoEmpleado);
        cajaEdad=(EditText)findViewById(R.id.txtEdadEmpleado);
        rMasculino=(RadioButton)findViewById(R.id.rbMasculino);
        rFemanino=(RadioButton)findViewById(R.id.rbFemenino);
        comboPuesto=(Spinner)findViewById(R.id.cmdPuestoEmpleado);
        opc=res.getStringArray(R.array.opciones_puestos);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,opc);
        comboPuesto.setAdapter(adapter);
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
        if (cajaEdad.getText().toString().isEmpty()){
            cajaEdad.setError(res.getString(R.string.error_cajaedad));
            cajaEdad.requestFocus();
            return false;
        }
        return true;
    }

    public boolean validarCedula(){
        if (cajaCedula.getText().toString().isEmpty()){
            cajaCedula.setError(res.getString(R.string.error_cajacedula));
            cajaCedula.requestFocus();
            return false;
        }
        return true;
    }

    public void guardar(View v){
        String foto,cedula,nombre,apellido,edad,puesto,sexo;
        Empleado e;
        if (validar()){
            foto=String.valueOf(fotoAleatoria());
            cedula=cajaCedula.getText().toString();
            nombre=cajaNombre.getText().toString();
            apellido=cajaApellido.getText().toString();
            edad=cajaEdad.getText().toString();
            puesto=comboPuesto.getSelectedItem().toString();
            if (rMasculino.isChecked())sexo=res.getString(R.string.masculino);
            else sexo=res.getString(R.string.femenino);

            e=new Empleado(foto,cedula,nombre,apellido,edad,puesto,sexo);
            e.guardar(getApplicationContext());

            Toast.makeText(getApplicationContext(), res.getString(R.string.empleado_guardado),
                    Toast.LENGTH_SHORT).show();

            limpiar();
        }
    }

    public int fotoAleatoria(){
        int Fotos[]={R.drawable.empleado1,R.drawable.empleado2,R.drawable.empleado3};
        int numero = (int)(Math.random() * 3);
        return Fotos[numero];
    }

    public void limpiar(){
        cajaCedula.setText("");
        cajaNombre.setText("");
        cajaApellido.setText("");
        cajaEdad.setText("");
        comboPuesto.setSelection(0);
        rMasculino.setChecked(true);
        cajaCedula.requestFocus();
    }
    public void borrar(View v){
        limpiar();
    }

    public void buscar(View v){
        Empleado e;
        if (validarCedula()){
            e=DatosEmpleados.buscarEmpleados(getApplicationContext(),cajaCedula.getText().toString());
            if (e!=null){
                cajaNombre.setText(e.getNombre());
                cajaApellido.setText(e.getApellido());
                cajaEdad.setText(e.getedad());

                if (e.getpuesto().equalsIgnoreCase(res.getString(R.string.gerente)))comboPuesto.setSelection(0);
                if (e.getpuesto().equalsIgnoreCase(res.getString(R.string.vendedor)))comboPuesto.setSelection(1);
                if (e.getpuesto().equalsIgnoreCase(res.getString(R.string.contador)))comboPuesto.setSelection(2);
                if (e.getpuesto().equalsIgnoreCase(res.getString(R.string.empleado_mostrador)))comboPuesto.setSelection(3);
                if (e.getpuesto().equalsIgnoreCase(res.getString(R.string.cajero)))comboPuesto.setSelection(4);
                if (e.getpuesto().equalsIgnoreCase(res.getString(R.string.bodeguero)))comboPuesto.setSelection(5);
                if (e.getpuesto().equalsIgnoreCase(res.getString(R.string.repartidor)))comboPuesto.setSelection(6);

                if (e.getsexo().equalsIgnoreCase(res.getString(R.string.masculino)))rMasculino.setChecked(true);
                else rFemanino.setChecked(true);
            }
        }
    }

    public void eliminar(View v){
        Empleado e;
        if (validarCedula()){
            e=DatosEmpleados.buscarEmpleados(getApplicationContext(),cajaCedula.getText().toString());
            if (e!=null){
                AlertDialog.Builder ventana = new AlertDialog.Builder(this);
                ventana.setTitle(res.getString(R.string.confirmacion));
                ventana.setMessage(res.getString(R.string.mensaje_confirmacion));

                ventana.setPositiveButton(res.getString(R.string.confirmar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Empleado e;
                        e = DatosEmpleados.buscarEmpleados(getApplicationContext(), cajaCedula.getText().toString());

                        e.eliminar(getApplicationContext());
                        limpiar();
                        Toast.makeText(getApplicationContext(), res.getString(R.string.empleado_eliminado),
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
            }
        }
    }

    public void modificar(View v){
        Empleado e,e1;
        String nombre,apellido,edad,puesto,sexo;
        if (validarCedula()){
            e=DatosEmpleados.buscarEmpleados(getApplicationContext(),cajaCedula.getText().toString());
            if (e!=null){
                nombre=cajaNombre.getText().toString();
                apellido=cajaApellido.getText().toString();
                edad=cajaEdad.getText().toString();
                puesto=comboPuesto.getSelectedItem().toString();
                if (rMasculino.isChecked())sexo=res.getString(R.string.masculino);
                else sexo=res.getString(R.string.femenino);

                e1=new Empleado(e.getFoto(),e.getCedula(),nombre,apellido,edad,puesto,sexo);
                e1.modificar(getApplicationContext());
                Toast.makeText(getApplicationContext(), res.getString(R.string.empleado_modificado),
                        Toast.LENGTH_SHORT).show();


                limpiar();
            }
        }
    }

}
