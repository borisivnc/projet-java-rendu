package grades.promotion;

import java.util.ArrayList;

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



}