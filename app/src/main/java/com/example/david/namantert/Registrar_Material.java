package com.example.david.namantert;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Registrar_Material extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private EditText cajaCodigo,cajaPrecio,cajaCantidad;
    private Spinner comboTipo,comboNombre;
    private Resources res;
    private String[] opc,opc2;
    private ArrayAdapter<CharSequence> adapter,adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar__material);

        res=this.getResources();
        cajaCodigo=(EditText)findViewById(R.id.txtCodigoMaterial);
        cajaPrecio=(EditText)findViewById(R.id.txtPrecioMaterial);
        cajaCantidad=(EditText)findViewById(R.id.txtCantidadMaterial);
        comboTipo=(Spinner)findViewById(R.id.cmdTipoMaterial);
        comboNombre=(Spinner)findViewById(R.id.cmdNombreMaterial);
        opc=res.getStringArray(R.array.opciones_tipo);
        adapter=ArrayAdapter.createFromResource(this,R.array.opciones_tipo,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comboTipo.setAdapter(adapter);
        comboTipo.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        int[] localidades={R.array.opciones_cerradura,R.array.opciones_electricidad,R.array.opciones_tornillos};
        adapter2 = ArrayAdapter.createFromResource(
                this,
                localidades[position],//En función de la provincia, se carga el array que corresponda del XML
                android.R.layout.simple_spinner_item);

        //Se carga el tipo de vista para el adaptador
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Se aplica el adaptador al Spinner de localidades
        comboNombre.setAdapter(adapter2);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public boolean validar(){
        if (cajaCodigo.getText().toString().isEmpty()){
            cajaCodigo.setError(res.getString(R.string.error_codigo));
            cajaCodigo.requestFocus();
            return false;
        }
        if (cajaPrecio.getText().toString().isEmpty()){
            cajaPrecio.setError(res.getString(R.string.error_precio));
            cajaPrecio.requestFocus();
            return false;
        }
        if (cajaCantidad.getText().toString().isEmpty()){
            cajaCantidad.setError(res.getString(R.string.error_cantidad));
            cajaCantidad.requestFocus();
            return false;
        }
        return true;
    }

    public boolean validarCodigo(){
        if (cajaCodigo.getText().toString().isEmpty()){
            cajaCodigo.setError(res.getString(R.string.error_codigo));
            cajaCodigo.requestFocus();
            return false;
        }
        return true;
    }

    public boolean validarRepetido(){
        Material m=DatosMateriales.buscarMateriales(getApplicationContext(),cajaCodigo.getText().toString());
        if (m!=null){
            cajaCodigo.setError(res.getString(R.string.error_codigo_existente));
            cajaCodigo.requestFocus();
            return false;
        }else {
            Material m2=DatosMateriales.buscarPorNombreMateriales(getApplicationContext(),comboNombre.getSelectedItem().toString());
            if (m2!=null){
                new AlertDialog.Builder(this).setMessage(getResources().getString(R.string.mensaje_nombre_existente)).setCancelable(true).show();
                return false;
            }
        }
        return true;
    }

    public void guardar(View v){
        String foto,codigo,precio,cantidad,tipo,nombre;
        Material m;
        if (validar()){
            if (validarRepetido()){
                foto=String.valueOf(fotoMaterial());
                codigo=cajaCodigo.getText().toString();
                precio=cajaPrecio.getText().toString();
                cantidad=cajaCantidad.getText().toString();
                tipo=comboTipo.getSelectedItem().toString();
                nombre=comboNombre.getSelectedItem().toString();
                m=new Material(foto,codigo,tipo,nombre,precio,cantidad);
                m.guardar(getApplicationContext());

                Toast.makeText(getApplicationContext(), res.getString(R.string.material_guardado),
                        Toast.LENGTH_SHORT).show();
                limpiar();
            }
        }
    }

    public void limpiar(){
        cajaCodigo.setText("");
        cajaPrecio.setText("");
        cajaCantidad.setText("");
        comboTipo.setSelection(0);
        comboNombre.setSelection(0);
        cajaCodigo.requestFocus();
    }
    public void borrar(View v){
        limpiar();
    }

    public int fotoMaterial(){
        int foto[]={R.drawable.imgcerradura,R.drawable.imdelectricidad,R.drawable.imgtornillos};
        int numero=0;
        if (comboTipo.getSelectedItemPosition()==0){
            numero=0;
        }
        if (comboTipo.getSelectedItemPosition()==1){
            numero=1;
        }
        if (comboTipo.getSelectedItemPosition()==2){
            numero=2;
        }
        return foto[numero];
    }

    public void buscar(View v){
        Material m;
        if (validarCodigo()){
            m=DatosMateriales.buscarMateriales(getApplicationContext(),cajaCodigo.getText().toString());
            if (m!=null){
                cajaPrecio.setText(m.getPrecio());
                cajaCantidad.setText(m.getCantidad());

                if (m.getTipo().equalsIgnoreCase(res.getString(R.string.cerradura)))comboTipo.setSelection(0);
                if (m.getTipo().equalsIgnoreCase(res.getString(R.string.electricidad)))comboTipo.setSelection(1);
                if (m.getTipo().equalsIgnoreCase(res.getString(R.string.tornilleria)))comboTipo.setSelection(2);

                if (m.getNombre().equalsIgnoreCase(res.getString(R.string.cerradura_alta_ceguridad)))comboNombre.setSelection(0);
                if (m.getNombre().equalsIgnoreCase(res.getString(R.string.cerradura_digital)))comboNombre.setSelection(1);
                if (m.getNombre().equalsIgnoreCase(res.getString(R.string.cerradura_manija)))comboNombre.setSelection(2);
                if (m.getNombre().equalsIgnoreCase(res.getString(R.string.cerradura_pomo)))comboNombre.setSelection(3);

                if (m.getNombre().equalsIgnoreCase(res.getString(R.string.canalizacion)))comboNombre.setSelection(0);
                if (m.getNombre().equalsIgnoreCase(res.getString(R.string.extenciones)))comboNombre.setSelection(1);
                if (m.getNombre().equalsIgnoreCase(res.getString(R.string.tacos)))comboNombre.setSelection(2);

                if (m.getNombre().equalsIgnoreCase(res.getString(R.string.chazos_anclajes)))comboNombre.setSelection(0);
                if (m.getNombre().equalsIgnoreCase(res.getString(R.string.clavos)))comboNombre.setSelection(1);
                if (m.getNombre().equalsIgnoreCase(res.getString(R.string.remaches)))comboNombre.setSelection(2);
                if (m.getNombre().equalsIgnoreCase(res.getString(R.string.tornillos)))comboNombre.setSelection(3);
            }else {
                Toast.makeText(getApplicationContext(), res.getString(R.string.error_codigo_no_existente),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void eliminar(View v){
        Material m;
        if (validarCodigo()){
            m=DatosMateriales.buscarMateriales(getApplicationContext(),cajaCodigo.getText().toString());
            if (m!=null){
                AlertDialog.Builder ventana = new AlertDialog.Builder(this);
                ventana.setTitle(res.getString(R.string.confirmacion));
                ventana.setMessage(res.getString(R.string.mensaje_confirmacion_material));

                ventana.setPositiveButton(res.getString(R.string.confirmar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Material m;
                        m = DatosMateriales.buscarMateriales(getApplicationContext(),cajaCodigo.getText().toString());

                        m.eliminar(getApplicationContext());
                        limpiar();
                        Toast.makeText(getApplicationContext(), res.getString(R.string.material_eliminado),
                                Toast.LENGTH_SHORT).show();
                    }
                });

                ventana.setNegativeButton(res.getString(R.string.cancelar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cajaCodigo.requestFocus();
                    }
                });

                ventana.show();
            }else {
                Toast.makeText(getApplicationContext(), res.getString(R.string.error_codigo_no_existente),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void modificar(View v){//solo se modificara el precio y la cantidad de los materiales
        Material m,m2;
        String precio,cantidad;
        if (validarCodigo()){
            m=DatosMateriales.buscarMateriales(getApplicationContext(),cajaCodigo.getText().toString());
            if (m!=null){

                precio=cajaPrecio.getText().toString();
                cantidad=cajaCantidad.getText().toString();

                m2=new Material(m.getFoto(),m.getCodigo(),m.getTipo(),m.getNombre(),precio,cantidad);
                m2.modificar(getApplicationContext());

                Toast.makeText(getApplicationContext(), res.getString(R.string.material_modificado),
                        Toast.LENGTH_SHORT).show();
                limpiar();
            }else {
                Toast.makeText(getApplicationContext(), res.getString(R.string.error_codigo_no_existente),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
