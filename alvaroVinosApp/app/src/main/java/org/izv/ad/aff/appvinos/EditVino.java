package org.izv.ad.aff.appvinos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.izv.ad.aff.appvinos.helper.DeleteFile;
import org.izv.ad.aff.appvinos.helper.ReadFile;
import org.izv.ad.aff.appvinos.helper.WriteFile;
import java.util.List;

public class EditVino extends AppCompatActivity {

    private Button btEdit, btDelete;
    private TextView tvSeeId;
    private EditText etNameEdit, etCellarEdit, etColourEdit, etOriginEdit, etDegreesEdit, etDateEdit;
    private WriteFile writeFile;
    private ReadFile readFile;
    private DeleteFile deleteFile;
    private Bundle b;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vino);

        btEdit = findViewById(R.id.bt_edit3);
        btDelete = findViewById(R.id.bt_delete3);
        tvSeeId = findViewById(R.id.tvID3);
        etCellarEdit = findViewById(R.id.etBodega3);
        etNameEdit = findViewById(R.id.etNombre3);
        etCellarEdit = findViewById(R.id.etBodega3);
        etColourEdit = findViewById(R.id.etColor3);
        etOriginEdit = findViewById(R.id.etOrigen3);
        etDegreesEdit = findViewById(R.id.etGrado3);
        etDateEdit = findViewById(R.id.etFecha3);

        //Recibimos del bundle de la actividad principal
        b = getIntent().getExtras();

        //creamos un objeto de la clase ReadFile para trabajar
        readFile = new ReadFile();

        //Mediante el bundle hecho anteriormente recibimos el valor del id del vino que deseamos editar
        id = Integer.parseInt(b.getString("valor"));

        //vemos el Id del vino recibido en el TextView
        tvSeeId.setText(String.valueOf(id));

        //carga los datos del vino a editar
        recogeET();



        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardaVList(actualizaVino());
                Intent i = new Intent(EditVino.this, MainActivity.class);
                startActivity(i);
            }});

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardaVList(borraVList());
                Intent i = new Intent(EditVino.this, MainActivity.class);
                startActivity(i);
            }});

    }

    //Recogemos los datos y los ponemos en los editText del vino correspondiente
    public void recogeET(){
        //creamos una lista de vinos desde el archivo.csv
        List<Vino> v = readFile.readFile(getFilesDir());
        //si existe el vino carga en la posicion [i] de la lista de vinos los datos del vino
        if(buscaVino() != -1) {
            int i = buscaVino();
            etNameEdit.setText(v.get(i).getNombre());
            etCellarEdit.setText(v.get(i).getBodega());
            etColourEdit.setText(v.get(i).getColor());
            etOriginEdit.setText(v.get(i).getOrigen());
            etDegreesEdit.setText(String.valueOf(v.get(i).getGrados()));
            etDateEdit.setText(String.valueOf(v.get(i).getFecha()));
        }
    }

    //actualiza los datos del vino que se indique
    public List<Vino> actualizaVino(){
        //creamos una lista de vinos desde el archivo.csv
        List<Vino> v = readFile.readFile(getFilesDir());
        //en la lista de vinos indica la posicion del vino a actualizar
        if(buscaVino() != -1) {
            int i = buscaVino();
            v.get(i).setNombre(etNameEdit.getText().toString());
            v.get(i).setBodega(etCellarEdit.getText().toString());
            v.get(i).setColor(etColourEdit.getText().toString());
            v.get(i).setOrigen(etOriginEdit.getText().toString());
            try {
                v.get(i).setGrados(Double.parseDouble(etDegreesEdit.getText().toString()));
                v.get(i).setFecha(Integer.parseInt(etDateEdit.getText().toString()));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return v;
    }

    //para guardar la lista de vinos borramos el existente y añadimos la lista de vinos a un nuevo fichero
    public void guardaVList(List<Vino> v){
        //elimina el archivo.csv
        deleteFile = new DeleteFile();
        deleteFile.deleteFile(getFilesDir());
        writeFile = new WriteFile();
        //escribe la lista de vinos en un nuevo csv
        for(int i= 0;i < v.size();i++){
            writeFile.writeFile(getFilesDir(), Vino.escribeVino(v.get(i)));
        }
    }

    //elimina el vino indicado de la lista de vinos
    public List<Vino> borraVList(){
        //cargamos la lista de vinos desde el archivo.csv
        List<Vino> v =  readFile.readFile(getFilesDir());
        //si existe el vino buscado
        if(buscaVino() != -1) {
            int i = buscaVino();
            v.remove(i);
        }
        return v;
    }

    //Método donde comprobamos si existe el vino en la lista
    public int buscaVino(){
        //carga la lista de vinos desde el archivo
        List<Vino> v = readFile.readFile(getFilesDir());

        for(int i=0;i< v.size();i++){
            if(id == v.get(i).getId()){
                return i;
            }
        }
        //cuando no existe el vino
        return -1;
    }

}