package com.example.david.namantert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Listas extends AppCompatActivity {
    private ListView ls;
    private String[] opc;
    private ArrayAdapter adapter;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listas);

        ls=(ListView)findViewById(R.id.lsListado);
        opc=getResources().getStringArray(R.array.opciones_listas);
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,opc);
        ls.setAdapter(adapter);

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        i=new Intent(Listas.this, Lista_Empleados.class);
                        startActivity(i);
                        break;
                    case 1:
                        i=new Intent(Listas.this,Listado_Materiales.class);
                        startActivity(i);
                        break;
                    case 2:
                        i=new Intent(Listas.this,Listado_Registro_Compras.class);
                        startActivity(i);
                        break;
                }
            }
        });
    }
}
