package grades.promotion;

import grades.util.Date;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.round;

public class Student extends Person {

    private int                 ID;
    private Date                birthDate;

    private ArrayList<Exam>     exams;
    private float               averageGrade;

    private float               medianGrade;

    private String              promotion;

    private final int           NB_EXAMS = 10;


    public Student(String surname, String name, Date birthDate, String promotion) {
        super(name, surname);
        this.ID = _ID++;
        this.birthDate = birthDate;
        this.promotion = promotion;
        this.exams = new ArrayList<>();
    }

    public void setID(int id) {
        ID = id;
    }

    public void setPromotion(String promo) {
        promotion = promo;
    }


    public void addGrade(String subject, float grade, Teacher teacherGrading) {

        exams.add(new Exam(subject, this, teacherGrading, grade));
        exams.sort((o1, o2) -> Float.compare(o1.getGrade(), o2.getGrade()));

        float gradeSum = 0;

        if(exams.size() > 1)
        {
            float medianIndex = Math.round((float)exams.size() / 2) - 1;

            medianGrade = ( (exams.size() % 2 == 0)
                    ? (exams.get((int)medianIndex).getGrade() + exams.get((int)medianIndex + 1).getGrade()) / 2
                    : exams.get(exams.size() / 2).getGrade());
        }

        else
        {
            medianGrade = exams.get(0).getGrade();
        }


        for(Exam e : exams) {

            gradeSum += e.getGrade();
        }

        averageGrade = (float)gradeSum / exams.size();
    }

    public int getID() {
        return ID;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public float getAverageGrade() throws IllegalStateException {

        if(exams.isEmpty())
            throw new IllegalStateException("The student has no grades");

        return averageGrade;
    }

    public float getMedianGrade() throws IllegalStateException {

        if(exams.isEmpty())
            throw new IllegalStateException("The student has no grades");

        return medianGrade;
    }

    public Set<Teacher> getGraders() {

        Set<Teacher> teacherSet = new HashSet<>();

        for(Exam e : exams)
            teacherSet.add(e.getTeacherGrading());

        return teacherSet;
    }

    @Override
    public String toString() {

        String result;
        result = super.toString() + " id: " + ID;

        result += "\nGrades : ";

        for(Exam e : exams) {

            result += (e.getSubject() + " " + e.getGrade());
        }

        try {

            result += "\nAverage grade : " + getAverageGrade();
            result += "\nMedian grade : " + getMedianGrade();
        }

        catch (IllegalStateException e) {
            result += e.getMessage();
        }

        result += "\nGraders : ";
        result += getGraders();

        result += "\nPromotion: ";
        result += promotion;

        return result;

    }



    private static int _ID;

    static {
        _ID = 0;
    }
}
