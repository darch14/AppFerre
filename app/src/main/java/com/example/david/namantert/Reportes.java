package com.example.david.namantert;

import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Reportes extends AppCompatActivity {
    private ListView ls;
    private Resources res;
    private String[] op;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);

        ls=(ListView)findViewById(R.id.lsReportes);
        res=this.getResources();
        op=res.getStringArray(R.array.opciones_reporte);
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,op);
        ls.setAdapter(adapter);

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        mayorEdad();
                        break;
                    case 1:
                        materialCaro();
                        break;
                    case 2:
                        clienteComprador();
                        break;
                }
            }
        });
    }
    public void mayorEdad(){
        String mensaje;
        ArrayList<Empleado> e=DatosEmpleados.traerEmpleados(getApplicationContext());
        Empleado e2=e.get(0);
        for (int i=1;i<e.size();i++){
            if (Integer.parseInt(e2.getedad())<Integer.parseInt(e.get(i).getedad())){
                e2=e.get(i);
            }
        }
        mensaje=res.getString(R.string.mensaje_reporte1)+": "+e2.getNombre()+" "+e2.getApellido();
        new AlertDialog.Builder(this).setMessage(mensaje).setCancelable(true).show();
    }

    public void materialCaro(){
        String mensaje;
        ArrayList<Material> m=DatosMateriales.traerMateriales(getApplicationContext());
        Material m2=m.get(0);
        for (int i=1;i<m.size();i++){
            if (Integer.parseInt(m2.getPrecio())<Integer.parseInt(m.get(i).getPrecio())){
                m2=m.get(i);
            }
        }
        mensaje=res.getString(R.string.mensaje_reporte2)+": "+m2.getNombre();
        new AlertDialog.Builder(this).setMessage(mensaje).setCancelable(true).show();
    }

    public void clienteComprador(){
        String mensaje;
        ArrayList<Cliente> c=DatosClientes.traerClientes(getApplicationContext());
        Cliente c2=c.get(0);
        for (int i=0;i<c.size();i++){
            if (Integer.parseInt(c2.getNcompras())<Integer.parseInt(c.get(i).getNcompras())){
                c2=c.get(i);
            }
        }
        mensaje=res.getString(R.string.mensaje_reporte3)+": "+c2.getNombre()+" "+c2.getApellido();
        new AlertDialog.Builder(this).setMessage(mensaje).setCancelable(true).show();
    }
}
