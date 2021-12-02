package org.izv.ad.aff.appvinos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.izv.ad.aff.appvinos.helper.ReadFile;
import org.izv.ad.aff.appvinos.helper.WriteFile;
import java.util.List;

public class AddVino extends AppCompatActivity {

    private Button btAdd;
    private Intent i;
    private WriteFile writeFile;
    private ReadFile readFile;
    private EditText etId, etnombre, etbodega, etcolor, etorigen, etgrados, etfecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vino);

        btAdd = findViewById(R.id.bt_add2);
        etId = findViewById(R.id.et_id2);
        etId.setText("");
        etnombre = findViewById(R.id.et_nombre2);
        etnombre.setText("");
        etbodega = findViewById(R.id.et_bodega2);
        etbodega.setText("");
        etcolor = findViewById(R.id.et_color2);
        etcolor.setText("");
        etorigen = findViewById(R.id.et_origen2);
        etorigen.setText("");
        etgrados = findViewById(R.id.et_grado2);
        etgrados.setText("");
        etfecha = findViewById(R.id.et_fecha2);
        etfecha.setText("");

        //intent para ir a la main activity
        i = new Intent(AddVino.this, MainActivity.class);
        //creamos el objeto de la clase ReadFile;
        readFile = new ReadFile();

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //si existe el vino
               if(buscaVino()){
                   Toast.makeText(getApplicationContext(), "Ya existe un vino con tal ID, intente con otra", Toast.LENGTH_LONG).show();
               }
               //si hay algun campo vacio
               else if(emptyC()){
                   Toast.makeText(getApplicationContext(), "Complete todos los campos", Toast.LENGTH_LONG).show();
               }
               else {
                   //añadimos el vino al fichero y volvemos al main activity
                   writeFile = new WriteFile();
                   writeFile.writeFile(getFilesDir(), Vino.escribeVino(creaVino()));
                   startActivity(i);
               }

            }});

    }

    //comprobamos que no haya ningun campo vacío
    public boolean emptyC(){
        if(
                etId.getText().length() == 0 || etnombre.getText().length() == 0 ||
                etbodega.getText().length() == 0 ||  etcolor.getText().length() == 0 ||
                etorigen.getText().length() == 0 || etgrados.getText().length() == 0 ||
                etfecha.getText().length() == 0
        ){
           return true;
        }
        else{
            return false;
        }
    }

    //Creamos un vino con los datos que le pasamos por los editText
    public Vino creaVino(){

        Long id = null; Double grados = 0.0; int fecha = 0;
        try {
            id = Long.parseLong(etId.getText().toString());
            grados = Double.parseDouble(etgrados.getText().toString());
            fecha = Integer.parseInt(etfecha.getText().toString());
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        String nombre = etnombre.getText().toString();
        String bodega = etbodega.getText().toString();
        String color = etcolor.getText().toString();
        String origen = etorigen.getText().toString();

        //creamos el objeto vino con los datos de las variables
        Vino v = new Vino(id,nombre,bodega,color,origen,grados,fecha);

        return v;
    }

    //comprobamos si el id que queremos añadir ya existe (ya existiría el vino)
    public boolean buscaVino(){
        //cogemos la lista de vinos desde el archivo
        List<Vino> v = readFile.readFile(getFilesDir());

        for(int i= 0;i < v.size();i++){
            if(Long.parseLong(etId.getText().toString()) == v.get(i).getId()){
                return true;
            }
        }
    return false;
    }

}