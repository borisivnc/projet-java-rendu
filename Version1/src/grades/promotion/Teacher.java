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

        Student s = searchStudentWithID(promotion, ID); // on cherche en premier si l'étudiant existe

        if(s == null ) // Si on trouve pas l'étudiant
            throw new IllegalStateException("The student does not exist"); // On previens l'utilisateur 

        else if(exams.size() > i ) // si l'examen est déjà présent dans la liste 
            exams.get(i).setGrade(newGrade); // On ajoute juste la nouvelle note newGrade

        else
            exams.add(new Exam("", s, this, newGrade)); // sinon on crée l'examen en y mettant la note egalement

    }



}
