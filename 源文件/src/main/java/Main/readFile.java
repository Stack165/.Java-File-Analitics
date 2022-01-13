package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class readFile {
    public static void read(File f){
        String str;
        Data.fileRead.clear();
        try(
                BufferedReader bfr=new BufferedReader(new FileReader(f.getPath()))
        ) {
            while ((str=bfr.readLine())!=null){
                Data.fileRead.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
