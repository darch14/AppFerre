package com.example.david.namantert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Lista_Registros extends AppCompatActivity {
    private ListView ls;
    private String[] opc;
    private ArrayAdapter adapter;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__registros);

        ls=(ListView)findViewById(R.id.lsRegistros);
        opc=getResources().getStringArray(R.array.opciones_registros);
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,opc);
        ls.setAdapter(adapter);

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        i=new Intent(Lista_Registros.this,Registrar_Empleado.class);
                        startActivity(i);
                        break;
                    case 1:
                        i=new Intent(Lista_Registros.this,Registrar_Material.class);
                        startActivity(i);
                        break;
                }
            }
        });
    }
}
