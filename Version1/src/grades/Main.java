package grades;

import grades.promotion.Promotion;
import grades.promotion.Student;
import grades.promotion.Teacher;
import grades.util.Date;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Promotion p = new Promotion("");

        Student maria = new Student("Maria","Graus Rafael", new Date(28,7,1998),"L3 2021");
        Student boris = new Student("Boris","Ivanovic",new Date(20,1,1998),"L3 2021");
        Student jb = new Student("Jean-Baptiste","Granet",new Date(27,3,1998),"L3 2021");
        Student laura = new Student("Laura","Trus",new Date(2,4,1998),"L3 2021");
        Student tristan = new Student("Tristan","Sar",new Date(11,11,1998),"L3 2021");
        Student mamadou = new Student("Mamadou","Dupont",new Date(25,9,1998),"L3 2021");


        Teacher a = new Teacher("Pascal","Marivaux");
        Teacher b = new Teacher("Helene","Zaric");
        Teacher c = new Teacher("Fabien","Silva");
        Teacher d = new Teacher("Walter","White");


        p.add(maria);
        p.add(boris);
        p.add(jb);
        p.add(laura);
        p.add(tristan);
        p.add(mamadou);

        a.addGrade("Math",17 ,maria);
        a.addGrade("Math",10 ,boris);
        a.addGrade("Math",9 ,jb);
        a.addGrade("Math",2 ,laura);
        a.addGrade("Math",15 ,tristan);
        a.addGrade("Math",11 ,mamadou);

        b.addGrade("English",13 ,maria);
        b.addGrade("English",15 ,boris);
        b.addGrade("English",13 ,jb);
        b.addGrade("English",10 ,laura);
        b.addGrade("English",15 ,tristan);
        b.addGrade("English",07 ,mamadou);

        c.addGrade("IT", 13.5f,maria);
        c.addGrade("IT",19 ,boris);
        c.addGrade("IT",15 ,jb);
        c.addGrade("IT",12 ,laura);
        c.addGrade("IT",15 ,tristan);
        c.addGrade("IT",14 ,mamadou);

        d.addGrade("Chemistry",17 ,maria);
        d.addGrade("Chemistry",15.5f ,boris);
        d.addGrade("Chemistry",15 ,jb);
        d.addGrade("Chemistry",10 ,laura);
        d.addGrade("Chemistry",15 ,tristan);
        d.addGrade("Chemistry",07 ,mamadou);

        System.out.println("-------------- Affichage d'un eleve --------------\n");

        System.out.println(jb.toString());

        System.out.println("\n--------------- Affichage des eleve d'une promotion --------------\n");

        for ( Student s : p ) {

            System.out.println(s);
            System.out.println("\n");
        }

        System.out.println("-------------- Recherche d'un etudiant avec id = 4 ---------------");

        System.out.println( p.searchStudentWithID(4));



        System.out.println("\n--------- Sort by Average in Ascending Order ----------------- \n");

        p.sortByAverageGrade(true);
        for ( Student s : p ) {

            System.out.println(s);
            System.out.println("\n");
        }

        System.out.println("\n----------Sort by Average in Descending Order ----------------- \n");

        p.sortByAverageGrade(false);
        for ( Student s : p ) {

            System.out.println(s);
            System.out.println("\n");
        }

        System.out.println("\n---------Sort by Median in Ascending Order----------------- \n");

        p.sortByMedianGrade(true);
        for ( Student s : p ) {

            System.out.println(s);
            System.out.println("\n");
        }

        System.out.println("\n---------Sort by Median in Descending Order ----------------- \n");

        p.sortByMedianGrade(false);
        for ( Student s : p ) {

            System.out.println(s);
            System.out.println("\n");
        }





    }
}
