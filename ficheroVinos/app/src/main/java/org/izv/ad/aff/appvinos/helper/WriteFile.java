package org.izv.ad.aff.appvinos.helper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile{
    //nombre del archivo csv
    public static final String fileName = "archivoAlvaro.csv";

    public void writeFile(File file, String wine) {

        //escribimos en el archivo csv una linea que contiene los datos del vino
        File f = new File(file, fileName);
        FileWriter fw = null;

        try {
            fw = new FileWriter(f, true);
            fw.write(wine);
            fw.write("\n");
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}




