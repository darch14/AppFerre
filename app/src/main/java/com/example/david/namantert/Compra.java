package com.example.david.namantert;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Compra extends AppCompatActivity {
    private EditText cajaCodigo,cajaCantidad;
    private TextView cajaNombre,cajaPrecio;
    private Resources res;
    private TableLayout tabla;
    private ArrayList<CarroCompra> carr;
    private Bundle b;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        res=this.getResources();
        cajaCodigo=(EditText)findViewById(R.id.txtCodigoCompra);
        cajaCantidad=(EditText)findViewById(R.id.txtCantidadCompra);
        cajaNombre=(TextView)findViewById(R.id.txtNombreCompra);
        cajaPrecio=(TextView)findViewById(R.id.txtPrecioCompra);
        tabla=(TableLayout)findViewById(R.id.tblCarroCompra);
        b=getIntent().getExtras();
        i=new Intent(this,Compras_Registro_Clientes.class);
        tabla();
    }

    public void tabla(){
        tabla = (TableLayout) findViewById(R.id.tblCarroCompra);
        carr = DatosCarrosCompras.traerCarrosCompras(getApplicationContext());

        tabla.removeAllViewsInLayout();
        for (int i = 0; i < carr.size()+1; i++) {
            if (i==0){
                TableRow fila = new TableRow(this);
                TextView c1 = new TextView(this);
                TextView c2 = new TextView(this);
                TextView c3 = new TextView(this);
                TextView c4 = new TextView(this);
                TextView c5 = new TextView(this);

                c1.setText(res.getString(R.string.numero));
                c2.setText(res.getString(R.string.nombre_material));
                c3.setText(res.getString(R.string.cantidad));
                c4.setText(res.getString(R.string.precio));

                fila.addView(c1);
                fila.addView(c2);
                fila.addView(c3);
                fila.addView(c4);

                tabla.addView(fila);
            }else {
                TableRow fila = new TableRow(this);
                TextView c1 = new TextView(this);
                TextView c2 = new TextView(this);
                TextView c3 = new TextView(this);
                TextView c4 = new TextView(this);

                c1.setText(""+i);
                c2.setText(carr.get(i-1).getNomaterial());
                c3.setText(carr.get(i-1).getCantidad());
                c4.setText(carr.get(i-1).getPrecio());

                fila.addView(c1);
                fila.addView(c2);
                fila.addView(c3);
                fila.addView(c4);

                tabla.addView(fila);
            }
        }
    }

    public void buscar(View v){
        Material m ,m1;
        if (validarCodigo()){
            m=DatosMateriales.buscarMateriales(getApplicationContext(),cajaCodigo.getText().toString());
            m1=DatosMateriales.buscarMateriales(getApplicationContext(),cajaCodigo.getText().toString());
            if (m!=null || m1!=null){
                cajaNombre.setText(m.getNombre());
                cajaPrecio.setText(m.getPrecio());
            }else {
                Toast.makeText(getApplicationContext(), res.getString(R.string.error_codigo_no_existente),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean validarCodigo(){
        if (cajaCodigo.getText().toString().isEmpty()){
            cajaCodigo.setError(res.getString(R.string.error_codigo));
            cajaCodigo.requestFocus();
            return false;
        }
        return true;
    }

    public void agregar(View v){
        String material,cantidad,pre;
        int precio;
           if (validar()){
               CarroCompra c;
               material=cajaNombre.getText().toString();
               cantidad=cajaCantidad.getText().toString();
               pre=cajaPrecio.getText().toString();
               precio=Integer.parseInt(cantidad)*Integer.parseInt(pre);
               String cedulaC=b.getString("cedula");
               c=new CarroCompra(cedulaC,material,cantidad,String.valueOf(precio));
               c.guardar(getApplicationContext());
               tabla();
               limpiar();
           }
    }

    public void limpiar(){
        cajaCodigo.setText("");
        cajaCantidad.setText("");
        cajaNombre.setText("");
        cajaPrecio.setText("");
    }

    public boolean validar(){
        if (cajaCodigo.getText().toString().isEmpty()){
            cajaCodigo.setError(res.getString(R.string.error_codigo));
            cajaCodigo.requestFocus();
            return false;
        }
        if (cajaCantidad.getText().toString().isEmpty()){
            cajaCantidad.setError(res.getString(R.string.error_cantidad));
            cajaCantidad.requestFocus();
            return false;
        }
        if (cajaNombre.getText().toString().isEmpty()){
            cajaCodigo.setError(res.getString(R.string.haga_busqueda));
            cajaCodigo.requestFocus();
            return false;
        }
        if (cajaPrecio.getText().toString().isEmpty()){
            cajaCodigo.setError(res.getString(R.string.haga_busqueda));
            cajaCodigo.requestFocus();
            return false;
        }
        return true;
    }

    public void comprar(View v){
        ArrayList<CarroCompra> cc;
        Cliente cl,cl2;
        String cedulaC,n,material="";
        int n1,precio=0;
        cc=DatosCarrosCompras.traerCarrosCompras(getApplicationContext());
        if (cc.get(0)!=null){
            cedulaC=b.getString("cedula");
            cl=DatosClientes.buscarClientes(getApplicationContext(),cedulaC);
            if (cl!=null){
                n=cl.getNcompras();
                n1=Integer.parseInt(n)+1;
                cl2=new Cliente(cl.getCedula(),cl.getNombre(),cl.getApellido(),cl.getTelefono(),String.valueOf(n1));
                cl2.modificar(getApplicationContext());

                for (int i=0;i<cc.size();i++){
                    material=material+cc.get(i).getNomaterial()+", ";
                    precio=precio+Integer.parseInt(cc.get(i).getPrecio());
                }
                material=material.substring(0,material.length()-2);
                //guardar la compra
                Compras compras=new Compras(cl2.getNombre()+" "+cl2.getApellido(),material,String.valueOf(precio));
                compras.guardar(getApplicationContext());

                //Restar la cantidad a cada material comprado
                for (int i=0;i<cc.size();i++){
                    Material m=DatosMateriales.buscarPorNombreMateriales(getApplicationContext(),cc.get(i).getNomaterial());
                    if (m!=null){
                        Material m2;
                        int cantidad=Integer.parseInt(m.getCantidad())-Integer.parseInt(cc.get(i).getCantidad());
                        m2=new Material(m.getFoto(),m.getCodigo(),m.getTipo(),m.getNombre(),m.getPrecio(),String.valueOf(cantidad));
                        m2.modificar(getApplicationContext());
                    }
                }
                //limpia el Carrito de Compras
                limpiarCarroCompra();

                Toast.makeText(getApplicationContext(), res.getString(R.string.compra_exitosa),
                        Toast.LENGTH_SHORT).show();
                tabla();
                startActivity(i);
                finish();
            }
        }
    }

    public void limpiarCarroCompra(){
        String cedulaC=b.getString("cedula");
        CarroCompra cc=DatosCarrosCompras.buscarCarrosCompras(getApplicationContext(),cedulaC);
        cc.eliminar(getApplicationContext());
    }
}
