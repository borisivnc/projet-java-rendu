package grades;

import grades.ihm.BarChartGraph;
import grades.promotion.Promotion;
import grades.promotion.Student;
import grades.promotion.Teacher;
import grades.ihm.graph;
import org.jfree.ui.RefineryUtilities;

import java.io.*;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {


        Promotion p = new Promotion();
        // On prend les étudiants de la liste csv
        try {

            p.loadFromCsv("StudentList.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // On ajoute des professeurs
        Teacher a = new Teacher("Pascal","Marivaux");
        Teacher b = new Teacher("Helene","Zaric");
        Teacher c = new Teacher("Fabien","Silva");
        Teacher d = new Teacher("Walter","White");

        // Les professeurs donnent des notes
        a.addGrade("Math",17 , p.getStudent(0) );
        a.addGrade("Math",10 ,p.getStudent(1));
        a.addGrade("Math",9 ,p.getStudent(2));
        a.addGrade("Math",8 ,p.getStudent(3));
        a.addGrade("Math",15 ,p.getStudent(4));
        a.addGrade("Math",11 ,p.getStudent(5));

        a.addGrade("Math",10 , p.getStudent(0) );
        a.addGrade("Math",15 ,p.getStudent(1));
        a.addGrade("Math",13 ,p.getStudent(2));
        a.addGrade("Math",14 ,p.getStudent(3));
        a.addGrade("Math",10 ,p.getStudent(4));
        a.addGrade("Math",16 ,p.getStudent(5));

        b.addGrade("English",13 ,p.getStudent(0));
        b.addGrade("English",15 ,p.getStudent(1));
        b.addGrade("English",13 ,p.getStudent(2));
        b.addGrade("English",10 ,p.getStudent(3));
        b.addGrade("English",15 ,p.getStudent(4));
        b.addGrade("English",12 ,p.getStudent(5));

        b.addGrade("English",17 ,p.getStudent(0));
        b.addGrade("English",10 ,p.getStudent(1));
        b.addGrade("English",20 ,p.getStudent(2));
        b.addGrade("English",12 ,p.getStudent(3));
        b.addGrade("English",10 ,p.getStudent(4));
        b.addGrade("English",10 ,p.getStudent(5));


        c.addGrade("IT", 12,p.getStudent(0));
        c.addGrade("IT",19 ,p.getStudent(1));
        c.addGrade("IT",15 ,p.getStudent(2));
        c.addGrade("IT",12 ,p.getStudent(3));
        c.addGrade("IT",15 ,p.getStudent(4));
        c.addGrade("IT",14 ,p.getStudent(5));

        c.addGrade("IT", 9,p.getStudent(0));
        c.addGrade("IT",14 ,p.getStudent(1));
        c.addGrade("IT",16 ,p.getStudent(2));
        c.addGrade("IT",10 ,p.getStudent(3));
        c.addGrade("IT",18 ,p.getStudent(4));
        c.addGrade("IT",10 ,p.getStudent(5));

        d.addGrade("Chemistry",17 ,p.getStudent(0));
        d.addGrade("Chemistry",15 ,p.getStudent(1));
        d.addGrade("Chemistry",15 ,p.getStudent(2));
        d.addGrade("Chemistry",10 ,p.getStudent(3));
        d.addGrade("Chemistry",15 ,p.getStudent(4));
        d.addGrade("Chemistry",9 ,p.getStudent(5));

        d.addGrade("Chemistry",18 ,p.getStudent(0));
        d.addGrade("Chemistry",13 ,p.getStudent(1));
        d.addGrade("Chemistry",15 ,p.getStudent(2));
        d.addGrade("Chemistry",17 ,p.getStudent(3));
        d.addGrade("Chemistry",12 ,p.getStudent(4));
        d.addGrade("Chemistry",14 ,p.getStudent(5));

        // Pour chaque étudiants on affiche leur bulletin

        for(Student s : p){

            System.out.println("\n+---------------------------+");
            System.out.println( "|       "+ s.getName() +" "+ s.getSurname());
            System.out.println("+----------------------------------------------------------------------------------------------------------------+");
            System.out.println("+Nom de la matiere | Moyenne Eleve | Mediane Eleve | Moyenne Promotion | Mediane Promotion | Note Min | Note Max |");
            System.out.println("+----------------------------------------------------------------------------------------------------------------+");
            System.out.println("+  Mathematiques   |    " + s.getAverageGrade( "Math" )+ "       |    " + s.getMedianGrade("Math") + "       | " + p.getPromotionAverageGrade("Math") +"|       " + p.getPromotionMedianGrade("Math") + "        |  " + p.getMinValue("Math")+"     |  "+ p.getMaxValue("Math") +"    |");
            System.out.println("+----------------------------------------------------------------------------------------------------------------+");
            System.out.println("+     Anglais      |    " + s.getAverageGrade( "English" )+ "       |    " + s.getMedianGrade("English") + "       | " + p.getPromotionAverageGrade("English") +"|       " + p.getPromotionMedianGrade("English") + "        |  " + p.getMinValue("English")+"    |  "+ p.getMaxValue("English") +"    |");
            System.out.println("+----------------------------------------------------------------------------------------------------------------+");
            System.out.println("+   Informatique   |    " + s.getAverageGrade( "IT" )+ "       |    " + s.getMedianGrade("IT") + "       | " + p.getPromotionAverageGrade("IT") +"|       " + p.getPromotionMedianGrade("IT") + "        |  " + p.getMinValue("IT")+"     |  "+ p.getMaxValue("IT") +"    |");
            System.out.println("+----------------------------------------------------------------------------------------------------------------+");
            System.out.println("+      Chimie      |    " + s.getAverageGrade( "Chemistry" )+ "       |    " + s.getMedianGrade("Chemistry") + "       | " + p.getPromotionAverageGrade("Chemistry") +"|       " + p.getPromotionMedianGrade("Chemistry") + "        |  " + p.getMinValue("Chemistry")+"     |  "+ p.getMaxValue("Chemistry") +"    |");
            System.out.println("+----------------------------------------------------------------------------------------------------------------+");
            System.out.println("+ Moyenne Generale : " + s.getAverageGrade() + "        |     Mediane Generale : "+ s.getMedianGrade() + "    |");
            System.out.println("+------------------------------------------------------------------+ \n");


        }

        // On crée les deux graphiques qui affiche la moyenne generale de chaque éléves et la mediane également
        
        BarChartGraph chart = new BarChartGraph("Graphique Moyenne Générale","Moyennes des élèves dans une promotion",p);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);

        graph chartMedian = new graph("Graphique Mediane Générale","Mediane des élèves dans une promotion",p);
        chartMedian.pack();
        RefineryUtilities.centerFrameOnScreen(chartMedian);
        chartMedian.setVisible(true);


    }

    }

