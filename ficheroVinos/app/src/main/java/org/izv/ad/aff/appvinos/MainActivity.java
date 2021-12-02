package org.izv.ad.aff.appvinos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.izv.ad.aff.appvinos.helper.ReadFile;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btEdit, btAdd;
    private EditText etId;
    private TextView tvText;
    private ReadFile readFile;
    private List<Vino> vinoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvText = findViewById(R.id.tv_1);
        btEdit = findViewById(R.id.bt_edit1);
        btAdd = findViewById(R.id.bt_add1);
        etId = findViewById(R.id.et_id1);

        //creamos objeto de la clase ReadFile para trabajar con el
        readFile = new ReadFile();

        //cargamos la lista de vinos desde el fichero
        vinoList = readFile.readFile(getFilesDir());

        //si existe información visualizala en el TextView
        if(vinoList.size() > 0) {
            tvText.setText(visuaLista(vinoList));
        }
        else {
            //no hay vinos
            tvText.setText("");
        }

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Si hemos completado el editText (no esta vacío)
                if(etId.length() > 0) {
                    //si el vino que pedimos editar existe usamos el pasarActivity para ir al activity edit
                    if(vinoExist(vinoList)) {
                        //abrimos actividad de editar vinos
                        pasarActivity();
                    }
                    else{
                        //no existe el vino que queremos editar
                        Toast.makeText(getApplicationContext(), "No existe ningún vino con esa ID", Toast.LENGTH_LONG).show();
                    }
                }
            }});

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //al pulsar el boton de añadir abrimos la actividad de add
                Intent i = new Intent(MainActivity.this, AddVino.class);
                startActivity(i);
            }});

    }

    //el pasaractivity lo que hace es almacenar la id del vino que pide editar y se la pasa al edit vino activity
    public void pasarActivity(){
        Intent i = new Intent(this, EditVino.class);
        Bundle b = new Bundle();
        b.putString("valor", etId.getText().toString());
        i.putExtras(b);
        startActivity(i);
    }

    //Para visualizar la lista de vinos en el main activity
    public String visuaLista(List<Vino> w) {
        String text = "";
        for(int i = 0;i < w.size();i++) {
            text += w.get(i).getId() + "; " + w.get(i).getNombre() + "; " + w.get(i).getBodega() + "; " + w.get(i).getOrigen() + "; " + w.get(i).getColor() + "; " + w.get(i).getGrados() + "; " + w.get(i).getFecha() + '\n'+'\n';
        }
        return text;
    }

    //Comprobamos si existe un vino
    public  boolean vinoExist(List<Vino> w){
        for(int i=0;i <w.size();i++){
            if(w.get(i).getId() == Long.parseLong(etId.getText().toString())){
                return true;
            }
        }
        return false;
    }

}