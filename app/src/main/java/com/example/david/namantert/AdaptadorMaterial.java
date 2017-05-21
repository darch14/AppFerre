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
 * Created by david on 19/05/2017.
 */

public class AdaptadorMaterial  extends BaseAdapter{
    private Context contexto;
    private ArrayList<Material> materials;

    public AdaptadorMaterial(Context contexto, ArrayList<Material> materials) {
        this.contexto = contexto;
        this.materials = materials;
    }

    @Override
    public int getCount() {
        return materials.size();
    }

    @Override
    public Object getItem(int position) {
        return materials.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        //Declarar variable
        TextView cajaCodigo,cajaNombre,cajaPrecio,cajaCantidad;
        ImageView foto;
        LayoutInflater inflater;
        View itemView;

        //Uso del inflater
        inflater=(LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        itemView=inflater.inflate(R.layout.item_personalizado_material,null);

        //Capturar los opjetos
        cajaCodigo=(TextView)itemView.findViewById(R.id.txtCodigoMaterialP);
        cajaNombre=(TextView)itemView.findViewById(R.id.txtNombreMaterialP);
        cajaPrecio=(TextView)itemView.findViewById(R.id.txtPrecioMaterialP);
        cajaCantidad=(TextView)itemView.findViewById(R.id.txtCantidadMaterialP);
        foto=(ImageView)itemView.findViewById(R.id.imgFotoMaterial);

        //Pasar la informacion
        foto.setImageResource(Integer.parseInt(materials.get(position).getFoto()));
        cajaCodigo.setText(materials.get(position).getCodigo());
        cajaNombre.setText(materials.get(position).getNombre());
        cajaPrecio.setText(materials.get(position).getPrecio());
        cajaCantidad.setText(materials.get(position).getCantidad());

        return itemView;
    }
}
