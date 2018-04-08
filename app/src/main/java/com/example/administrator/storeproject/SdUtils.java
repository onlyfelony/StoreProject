package com.example.administrator.storeproject;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.security.auth.login.LoginException;

public class SdUtils {

    public static Boolean IsExist() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
    public static Boolean InputStreamSaveSD(InputStream in, String fileName,
                                            String rootdir) {
        Boolean bool = false;
        if (IsExist()) {
            File dir = new File(Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + File.separator + rootdir);
            if (!dir.exists()){
                dir.mkdir();

            }
            FileOutputStream fos;
            try {
                fos = new FileOutputStream(new File(dir, fileName));
                byte[] buffer = new byte[1024];
                int length;

                try {
                    while ((length = in.read(buffer)) > 0) {
                        fos.write(buffer, 0, length);
                    }
                    fos.close();
                    in.close();
                    bool = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return bool;
    }
    public static Boolean pathSaveSD(String sourcePath, String fileName,
                                     String rootdir) {
        Boolean bool = false;
        FileInputStream fis;
        try {

            fis = new FileInputStream(sourcePath);

            if (InputStreamSaveSD(fis, fileName, rootdir)) {
                bool = true;
            } else {
                bool = false;
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool;
    }




}
