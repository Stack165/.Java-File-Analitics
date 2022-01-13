package Main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class outputFile {
    public static void output() {
        String type;
        File dir;
        /*if(Data.check.isDirectory()){
            type="D";
            dir = new File(Data.check.getTheFile().getPath()+"/data");
        }else {
            dir= new File(Data.check.getTheFile().getParent()+"/data");
            type="F";
        }*/
        if (Data.check.isDirectory()) {
            type = "D";
            dir = new File("data");
        } else {
            dir = new File("data");
            type = "F";
        }
        dir.mkdir();
        File storage = new File(dir.getPath() + "/" + type + "_" + Data.check.getTheFile().getName() + "_Result.txt");
        try {
            storage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = new String();
        try (
                BufferedWriter out = new BufferedWriter(new FileWriter(storage.getPath()))
        ) {
            str = "分析文件 :" + Data.check.getTheFile().getPath();
            out.write(str);
            out.newLine();
            out.newLine();
            if (type.equals("D")) {
                out.write("Java 源程序文件个数:" + Data.javaFileNumber);
                out.newLine();
            }
            out.write("源程序中字符总个数 :" + Data.characterNumber);
            out.newLine();
            out.write("注释总个数 :" + Data.commentNumber);
            out.newLine();
            out.write("注释总的字符数 :" + Data.commentCharacterNumber);
            out.newLine();
            out.newLine();
            out.write("关键字使用情况如下：\n");
            for (keyWord k : Data.wordLinkedList
            ) {
                out.write("[" + k.name + "\t=     " + k.number + "]");
                out.newLine();
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Data.storage.add(storage);
    }
}
