package com.example.david.namantert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by david on 15/05/2017.
 */

public class AdaptadorEmpleado extends BaseAdapter {
    private Context contexto;
    private ArrayList<Empleado> empleados;

    public AdaptadorEmpleado(Context contexto, ArrayList<Empleado> empleados) {
        this.contexto = contexto;
        this.empleados = empleados;
    }

    @Override
    public int getCount() {
        return empleados.size();
    }

    @Override
    public Object getItem(int position) {
        return empleados.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(empleados.get(position).getCedula());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Declarar variable
        TextView cajaCedula,cajaNombre,cajapuesto;
        ImageView foto;
        LayoutInflater inflater;
        View itemView;

        //Uso del inflater
        inflater=(LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        itemView=inflater.inflate(R.layout.item_personalizado_empleado,null);

        //Capturar los opjetos
        cajaCedula=(TextView)itemView.findViewById(R.id.txtCedulaEmpleadoP);
        cajaNombre=(TextView)itemView.findViewById(R.id.txtNombreEmpleadoP);
        cajapuesto=(TextView)itemView.findViewById(R.id.txtPuestoEmpleadoP);
        foto=(ImageView)itemView.findViewById(R.id.imgFotoEmpleado);

        //Pasar la informacion
        foto.setImageResource(Integer.parseInt(empleados.get(position).getFoto()));
        cajaCedula.setText(empleados.get(position).getCedula());
        cajaNombre.setText(empleados.get(position).getNombre()+" "
                +empleados.get(position).getApellido());
        cajapuesto.setText(empleados.get(position).getpuesto());

        return itemView;
    }
}
