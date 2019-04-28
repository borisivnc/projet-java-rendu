package grades.promotion;


public class Exam implements Comparable<Exam>{

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

    public String toString() {

        return "((" + studentGraded.getSurname() + ", " + studentGraded.getName() + ") (" + studentGraded.getSurname() + ", " + studentGraded.getName() + ") " + subject + " " + grade + ")";
    }


    @Override
    public int compareTo(Exam o) {
        return Float.compare(getGrade(), o.getGrade());
    }
}
