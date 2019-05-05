package grades.promotion;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Teacher extends Person  {

    ArrayList<Student> students;
    private ArrayList<Exam>  exams;

    public Teacher() {
        super();
    }

    public Teacher(String name, String surname) {
        super(name, surname);
    }

    public void addGrade(String subject, float grade, Student studentGraded) {
        studentGraded.addGrade(subject, grade, this);
    }

    public Student searchStudentWithID(Promotion promo, int ID){
        return promo.searchStudentWithID(ID);
    }

    public void setNote(Promotion promotion, int ID, float newGrade ,int i) throws IllegalStateException {

        Student s = searchStudentWithID(promotion, ID);

        if(s == null )
            throw new IllegalStateException("The student does not exist");

        else if(exams.size() > i )
            exams.get(i).setGrade(newGrade);

        else
            exams.add(new Exam("", s, this, newGrade));

    }

    public static ArrayList<Teacher> loadFromCsv(String filename) throws FileNotFoundException { // La même fonction qu'on utilise pour prendre les données d'un fichier des étudiants, on le fait ici pour prendre les prenom et nom des professeurs 

        ArrayList<Teacher> teacherList = new ArrayList<>();

        Scanner scanner = new Scanner(new File(filename));
        Scanner dataScanner = null;

        int index = 0;
        int i = 0;

        String name = "";
        String surname = "";

        boolean check;

        while (scanner.hasNextLine()) {

            dataScanner = new Scanner(scanner.nextLine());
            dataScanner.useDelimiter(",");

            while (dataScanner.hasNext()) {
                String data = dataScanner.next();

                if (index == 0) {
                    name = data;

                } else if (index == 1) {
                    surname = data;

                } else
                    System.out.println("invalid data::" + data);

                index++;

            }

            Teacher t = new Teacher(name, surname);
            teacherList.add(t);

            index = 0;
        }

        scanner.close();

        return teacherList;
    }



}
