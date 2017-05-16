package com.example.david.namantert;

import android.content.res.Resources;
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
        }
    }

    public int fotoAleatoria(){
        int Fotos[]={R.drawable.empleado1,R.drawable.empleado2,R.drawable.empleado3};
        int numero = (int)(Math.random() * 3);
        return Fotos[numero];
    }
}
