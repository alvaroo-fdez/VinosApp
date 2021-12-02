package org.izv.ad.aff.appvinos.helper;

import java.io.File;

public class DeleteFile{
    public void deleteFile(File file) {
        File f = new File(file, WriteFile.fileName);
        f.delete();
    }
}
