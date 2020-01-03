package com.tutkal.yazilimsinama.Red.Adapter;

import java.util.Comparator;

public class RecyclerClass {


    private int ID;
    String value1,value2,value3,value4,value5;

    String as[];


    public RecyclerClass(int ID, String... args) {
        as=new String[args.length];
        this.ID=ID;
        for (int i = 0; i <args.length ; i++) {
            as[i]=args[i];
        }

    }
    public String[] getAs() {
        return as;
    }

    public RecyclerClass() {
    }

    public void setAs(String[] as) {
        this.as = as;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public static Comparator<RecyclerClass> StuNameComparator = new Comparator<RecyclerClass>() {

        public int compare(RecyclerClass s1, RecyclerClass s2) {
            String StudentName1 = s1.getAs()[0].toUpperCase();
            String StudentName2 = s2.getAs()[0].toUpperCase();

            //ascending order
            return StudentName1.compareTo(StudentName2);

            //descending order
            //return StudentName2.compareTo(StudentName1);
        }};

    public static Comparator<RecyclerClass> StuRollno = new Comparator<RecyclerClass>() {

        public int compare(RecyclerClass s1, RecyclerClass s2) {

            int rollno1 = Integer.parseInt(s1.getAs()[2]);
            int rollno2 = Integer.parseInt(s2.getAs()[2]);

            /*For ascending order*/
            return rollno1-rollno2;

            /*For descending order*/
            //rollno2-rollno1;
        }};

}