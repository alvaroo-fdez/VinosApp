package org.izv.ad.aff.appvinos.helper;

import org.izv.ad.aff.appvinos.Vino;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class ReadFile{

    //lista de vinos desde el csv
    public List<Vino> readFile(File file){
        String linea = "";
        List<Vino> winelist = new ArrayList<>();
        File f = new File(file, WriteFile.fileName);
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            while ((linea = br.readLine()) != null) {
                winelist.add(Vino.leeVino(linea));
            }
            br.close();
        } catch(IOException e) {
        }
        return winelist;
    }
}





