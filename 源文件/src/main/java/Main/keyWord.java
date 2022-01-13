package Main;

import java.util.Comparator;
//关键字
//实现字典排序和值排序
public class keyWord implements Comparable <keyWord>{
    public String name = new String();
    public int number = 0;

    keyWord(String name) {
        this.name = name;
        number=0;
    }


    @Override
    public int compareTo( keyWord e) {
        if(this.number<e.number){
            return 1;
        }else if(this.number>e.number){
            return -1;
        }else{
            if(this.name.compareTo(e.name)<0){
                return -1;
            }else if(this.name.compareTo(e.name)>0){
                return 1;
            }else {
                return 0;
            }
        }
    }
}
