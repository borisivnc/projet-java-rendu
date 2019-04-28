package grades.promotion;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Exam {

    private String      subject;
    private Student     studentGraded;
    private Teacher     teacherGrading;
    private float       grade;


    public Exam(String subject, Student studentGraded, Teacher teacherGrading, float grade) {
        this.subject = subject;
        this.studentGraded = studentGraded;
        this.teacherGrading = teacherGrading;
        this.grade = grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public Student getStudentGraded() {
        return studentGraded;
    }

    public Teacher getTeacherGrading() {
        return teacherGrading;
    }

    public float getGrade() {
        return grade;
    }

    public static ArrayList<Exam> loadFromCsv(String filename, ArrayList<Student> students, ArrayList<Teacher> teachers) throws FileNotFoundException {

        ArrayList<Exam> examList = new ArrayList<>();

        Scanner scanner = new Scanner(new File(filename));
        Scanner dataScanner = null;

        int index = 0;
        int i = 0;

        String subject = "";
        String studentName = "";
        String studentSurname = "";
        String teacherName = "";
        String teacherSurname = "";
        float grade = 0;

        while (scanner.hasNextLine()) {

            dataScanner = new Scanner(scanner.nextLine());
            dataScanner.useDelimiter(",");

            while (dataScanner.hasNext()) {
                String data = dataScanner.next();

                if (index == 0) {
                    subject = data;

                }  else if (index == 1) {
                    teacherSurname = data;

                }  else if (index == 2) {
                    teacherName = data;

                }   else if (index == 3) {
                    studentSurname = data;

                }  else if (index == 4) {
                    studentName = data;

                }else if (index == 5) {
                    grade = Float.valueOf(data.trim());

                } else
                    System.out.println("invalid data::" + data);

                index++;

            }

            Student s = null;
            Teacher t = null;

            for(Student st : students) {

                if(st.getName().equals(studentName) && st.getSurname().equals(studentSurname)) {
                    s = st;

                }
            }

            for(Teacher teacher : teachers) {

                if(teacher.getName().equals(teacherName) && teacher.getSurname().equals(teacherSurname)) {
                    t = teacher;
                }
            }

            if(s != null && t != null) {

                Exam e = new Exam(subject, s, t, grade);
                examList.add(e);
            }

            index = 0;
        }

        scanner.close();

        return examList;
    }



    public String toString() {

        return "((" + studentGraded.getSurname() + ", " + studentGraded.getName() + ") (" + studentGraded.getSurname() + ", " + studentGraded.getName() + ") " + subject + " " + grade + ")";
    }


}
