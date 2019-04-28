package grades;

import grades.promotion.Promotion;
import grades.promotion.Student;
import grades.promotion.Teacher;
import grades.util.Date;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Promotion p = new Promotion();

        try {
            p.loadFromCsv("StudentList.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            p.saveAsCsv("StudentList_output.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("\n-----------------------------------\n");

        for(Student s : p)
            System.out.println(s + "\n");



    }
}
