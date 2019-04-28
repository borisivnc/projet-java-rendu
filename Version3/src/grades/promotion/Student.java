package grades.promotion;

import grades.util.Date;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;



public class Student extends Person {

    private int                 ID;
    private Date                birthDate;

    private ArrayList<Exam>     exams;
    private float               averageGrade;

    private float               medianGrade;

    private String              promotion;

    private final int           NB_EXAMS = 10;


    public Student(String name, String surname, Date birthDate, String promotion) {
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
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public float getAverageGrade() throws IllegalStateException {

        if(exams.isEmpty())
            throw new IllegalStateException("The student has no grades");

        float sum =  0.f;
        int n = 0;

        for(Exam e : exams) {

            sum += e.getGrade();
            n++;
        }

       return (float)sum / n;
    }


    public float getAverageGrade(String subject) throws IllegalStateException {

        if(exams.isEmpty())
            throw new IllegalStateException("The student has no grades");

        float sum =  0.f;
        int n = 0;

        for(Exam e : exams) {

            if(e.getSubject().equals(subject)) {

                sum += e.getGrade();
                n++;
            }
        }

        return (n == 0) ? 0 : (sum / n );
    }

    public float getMedianGrade() throws IllegalStateException {

        if(exams.isEmpty())
            throw new IllegalStateException("The student has no grades");

        if(exams.size() > 1)
        {
            float medianIndex = Math.round((float)exams.size() / 2) - 1;

            return ( (exams.size() % 2 == 0)
                    ? (exams.get((int)medianIndex).getGrade() + exams.get((int)medianIndex + 1).getGrade()) / 2
                    : exams.get(exams.size() / 2).getGrade());
        }

        return exams.get(0).getGrade();
    }

    public float getMedianGrade(String subject) throws IllegalStateException {

        if(exams.isEmpty())
            throw new IllegalStateException("The student has no grades");

            if(exams.size() > 1)
            {
                for(Exam e : exams) {

                        if(e.getSubject().equals(subject)) {

                        float medianIndex = Math.round((float)exams.size() / 2) - 1;

                        return ( (exams.size() % 2 == 0)
                            ? (exams.get((int)medianIndex).getGrade() + exams.get((int)medianIndex + 1).getGrade()) / 2
                            : exams.get(exams.size() / 2).getGrade());

                        }
                }
            }

        return exams.get(0).getGrade();
    }

    public ArrayList<Float> getGrades(String subject) {

        ArrayList<Float> examList = new ArrayList<>();

        for(Exam e : exams) {
            if(subject.equals(e.getSubject())) {

                examList.add(e.getGrade());

            }
        }

        return examList;
    }

    public Set<Teacher> getGraders() {

        Set<Teacher> teacherSet = new HashSet<>();

        for(Exam e : exams)
            teacherSet.add(e.getTeacherGrading());

        return teacherSet;
    }

    public ArrayList<Exam> getExams() {
        return exams;
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
