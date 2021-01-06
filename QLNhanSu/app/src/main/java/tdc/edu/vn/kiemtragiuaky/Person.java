package tdc.edu.vn.kiemtragiuaky;

import java.util.ArrayList;
import java.util.Arrays;

public class Person {
    private String name;
    private String degree;
    private ArrayList<String> hobbies;

    public Person(){}
    public Person(String name, String degree, ArrayList<String> hobbies) {
        this.name = name;
        this.degree = degree;
        this.hobbies = hobbies;
    }

    public String getHobbiesString(){
        String str;
        str=hobbies.toString().replaceAll(",",";");
        str= str.substring(1,str.length()-1);
        return str;
    }

    public ArrayList<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(ArrayList<String> hobbies) {
        this.hobbies = hobbies;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String str ="";
        str=hobbies.toString().replaceAll(",",";");
        str= str.substring(1,str.length()-1);
        return String.format("%s-%s-", getName(), getDegree())+str;
    }
}
