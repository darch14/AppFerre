package com.example.david.namantert;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

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
}
