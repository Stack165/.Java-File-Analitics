package Main;

import java.io.*;

public class AnalyzeFile {
    private String name;
    private boolean exist;
    private boolean isFile;
    private boolean isDirectory;
    private File theFile;
    private boolean isJava = false;

    public AnalyzeFile() {
    }

    public AnalyzeFile(String name) {
        this.name = name;
        this.theFile = new File(this.name);
        this.exist = this.theFile.exists();
        this.isFile = this.theFile.isFile();
        this.isDirectory = this.theFile.isDirectory();
        if (isFile) {
            String end = getFileExtension(theFile);
            if (end.equals("java")) {
                isJava = true;
            }
        }
    }

    public boolean isJava() {
        return isJava;
    }

    public boolean isFile() {
        return isFile;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public boolean isExist() {
        return exist;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public File getTheFile() {
        return theFile;
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    private static String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    private void getLines(String path) {//从文件中读取字符串
        String thisLine = new String();
        try (
                FileReader fr = new FileReader(path);
                BufferedReader bfr = new BufferedReader(fr)
        ) {
            while ((thisLine = bfr.readLine()) != null) {
                Data.Lines.addLast(thisLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getCharacterNumber() {//获取文件字符个数
        for (int i = 0; i < Data.Lines.size(); i++) {
            String line = Data.Lines.get(i);
            Data.characterNumber += line.length();
        }
    }

    private void checkComment() {//分析Java 源程序文件中的注释的个数
        int flag = -1;
        for (int i = 0; i < Data.Lines.size(); i++) {
            String line = Data.Lines.get(i);
            if ((flag = line.indexOf("/**")) != -1 && !line.contains("/**/")) {
                Data.commentNumber++;
                Data.commentCharacterNumber += line.length() - flag - 3;
                if (line.contains("*/")) {
                    Data.commentCharacterNumber -= 2;
                    continue;
                }
                i++;
                for (; !((line = Data.Lines.get(i)).contains("*/")); i++) {
                    Data.commentCharacterNumber += line.length();
                }
                Data.commentCharacterNumber += line.indexOf("*/");
                i--;
            } else if ((flag = line.indexOf("/*")) != -1) {
                Data.commentNumber++;
                Data.commentCharacterNumber += line.length() - flag - 2;
                if (line.contains("*/")) {
                    Data.commentCharacterNumber -= 2;
                    continue;
                }
                i++;
                for (; !((line = Data.Lines.get(i)).contains("*/")); i++) {
                    Data.commentCharacterNumber += line.length();
                }
                Data.commentCharacterNumber += line.indexOf("*/");
                i--;
            } else if ((flag = line.indexOf("//")) != -1) {
                Data.commentNumber++;
                Data.commentCharacterNumber += line.length() - flag - 2;
            }
        }
    }


    private void checkWord() {
        int size = Data.Lines.size();
        String read = "";
        char a, b, c;
        int flag1 = 0, flag2 = 0;
        for (int i = 0; i < size; i++) {
            flag2 = 0;
            String line = Data.Lines.get(i);
            int length = line.length();
            for (int j = 0; j < length; j++) {
                a = line.charAt(j);
                if (j + 1 < length) {
                    b = line.charAt(j + 1);
                    if (a == '/' && b == '/') {
                        break;
                    } else if (a == '/' && b == '*') {
                        flag1 = 1;
                    }
                    if (a == '*' && b == '/' && flag1 == 1) {
                        flag1 = 0;
                    }
                }
                if (flag1 == 0 && flag2 == 0 && a == '"') {
                    flag2 = 1;
                } else if (flag1 == 0 && flag2 == 1 && a == '"') {
                    flag2 = 0;
                }
                if (flag1 == 0 && flag2 == 0) {
                    if (j == 0 && a != ' ' && a != '{' && a != '}' && a != '.' && a != '(' && a != ')') {
                        read = read + a;
                    } else if (j == 0) {
                        continue;
                    }
                    boolean isWord = (a >= 'a' && a <= 'z') || (a >= 'A' && a <= 'Z') || a == '$' || a == '_';
                    if (isWord) {
                        read += a;
                    }
                    if (read != "" && (a == ' ' || j == length - 1 || a == '.' || !isWord)) {
                        if (Data.wordHashMap.containsKey(read)) {
                            Data.wordHashMap.put(read, Data.wordHashMap.get(read) + 1);
                        }
                        read = "";
                    }
                }

            }
        }
    }

    //检测单个文件
    public void checkFile(String path) {
        getLines(path);
        getCharacterNumber(); //源程序中字符总个数
        checkComment();
        checkWord();
    }

    //检测文件夹内所有文件
    public void checkALlFile() {
        String[] list = theFile.list();
        for (int i = 0; i < list.length; i++) {
            String name = list[i];
            String end = getFileExtension(name);
            if (end.equals("java")) {
                Data.javaFileNumber++;
                String path = theFile.getPath() + "\\" + name;
                getLines(path);
            }
            if (theFile.listFiles()[i].isDirectory()){
                checkALlFile(theFile.listFiles()[i]);
            }
        }
        getCharacterNumber(); //源程序中字符总个数
        checkComment();
        checkWord();
    }
    public void checkALlFile(File t) {
        String[] list = t.list();
        for (int i = 0; i < list.length; i++) {
            String name = list[i];
            String end = getFileExtension(name);
            if (end.equals("java")) {
                Data.javaFileNumber++;
                String path = t.getPath() + "\\" + name;
                getLines(path);
            }
            if (t.listFiles()[i].isDirectory()){
                checkALlFile(t.listFiles()[i]);
            }
        }
    }

}
