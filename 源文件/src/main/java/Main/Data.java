package Main;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;

public class Data {
    public static LinkedList<String> Lines = new LinkedList<>();
    public static LinkedList<File> storage = new LinkedList<>();
    public static LinkedList<String> fileRead = new LinkedList<>();
    public static AnalyzeFile check = new AnalyzeFile();
    public static String dir = new String();  //分析目录
    public static int javaFileNumber = 0; //Java 源程序文件个数
    public static long characterNumber = 0;  //源程序中字符总个数
    public static long commentNumber = 0;   //注释总个数
    public static long commentCharacterNumber = 0;   //注释总的字符数
    public static LinkedList<keyWord> wordLinkedList = new LinkedList<>();   //关键字
    public static final String[] KEYWORDS = {"abstract", "assert", "boolean", "break", "byte", "case",
            "catch", "char", "class", "const", "continue", "default", "do", "double", "else", "enum",
            "extends", "final", "finally", "float", "for", "goto", "if", "implements", "import", "instanceof",
            "int", "interface", "long", "native", "new", "package", "private", "protected", "public",
            "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw",
            "throws", "transient", "try", "void", "volatile", "while"};

    public static HashMap<String, Integer> wordHashMap = new HashMap<>();

    public static void setWordLinkedListAndHashMap() {
        for (String name : KEYWORDS
        ) {
            wordHashMap.put(name, 0);
            wordLinkedList.addLast(new keyWord(name));
        }
    }

    public static void setWords() {
        int len = wordLinkedList.size();
        for (int i = 0; i < len; i++) {
            wordLinkedList.get(i).number = wordHashMap.get(wordLinkedList.get(i).name);
        }
    }

    public static void reset() {
        Lines.clear();
        dir = "";
        javaFileNumber = 0;
        characterNumber = 0;
        commentNumber = 0;
        commentCharacterNumber = 0;
        wordLinkedList.clear();
        setWordLinkedListAndHashMap();
        fileRead.clear();
    }
}
