package com.example.david.namantert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Lista_Empleados extends AppCompatActivity {
    private ListView ls;
    private ArrayList<Empleado> empleados;
    private AdaptadorEmpleado adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_empleados);

        ls=(ListView)findViewById(R.id.lsListaEmpleados);
        empleados=DatosEmpleados.traerEmpleados(getApplicationContext());
        adapter=new AdaptadorEmpleado(getApplicationContext(),empleados);
        ls.setAdapter(adapter);
    }
}
