package com.example.david.namantert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class Listado_Registro_Compras extends AppCompatActivity {
    private TableLayout tabla;
    private ArrayList<Compras> compras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado__registro__compras);

        tabla=(TableLayout)findViewById(R.id.tblRegistroCompra);
        compras=DatosCompras.traerCompras(getApplicationContext());

        for (int i = 0; i < compras.size(); i++) {
            TableRow fila = new TableRow(this);
            TextView c1 = new TextView(this);
            TextView c2 = new TextView(this);
            TextView c3 = new TextView(this);
            TextView c4 = new TextView(this);

            c1.setText(String.valueOf(i+1));
            c2.setText(compras.get(i).getCliente());
            c3.setText(compras.get(i).getMaterial());
            c4.setText(compras.get(i).getPrecio());

            fila.addView(c1);
            fila.addView(c2);
            fila.addView(c3);
            fila.addView(c4);

            tabla.addView(fila);

        }
    }
}
