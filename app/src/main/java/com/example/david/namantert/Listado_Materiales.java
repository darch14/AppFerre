package com.example.david.namantert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Listado_Materiales extends AppCompatActivity {
    private ListView ls;
    private ArrayList<Material> materials;
    private AdaptadorMaterial adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado__materiales);

        ls=(ListView)findViewById(R.id.lsMateriales);
        materials=DatosMateriales.traerMateriales(getApplicationContext());
        adapter=new AdaptadorMaterial(getApplicationContext(),materials);
        ls.setAdapter(adapter);
    }
}
