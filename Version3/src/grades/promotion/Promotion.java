package grades.promotion;

import grades.util.Date;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;

public class Promotion implements Iterable<Student>, Collection<Student>, StudentComparator {

    private ArrayList<Student> students;
    private String name;

    public Promotion(String name) {

        students = new ArrayList<>();
        this.name = name;
    }

    public Promotion() {

        students = new ArrayList<>();
    }

     public void loadFromCsv(String filename) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(filename));
        Scanner dataScanner = null;

        int index = 0;
        int i = 0;

        int id;
        String name = "";
        String surname = "";
        String promo = "";

        boolean check;

        while (scanner.hasNextLine()) { 

            dataScanner = new Scanner(scanner.nextLine());
            dataScanner.useDelimiter(",");
            Date date = new Date();

            check = true;
           // On parcourt toutes les données d'une ligne
            while (dataScanner.hasNext()) {
                String data = dataScanner.next();

                // On récupère l'id, le prénom, le nom, la date dans l'ordre respectif
                if (index == 0) {

                    id = Integer.parseInt(data); 

                } else if (index == 1) {
                    name = data;

                } else if (index == 2) {
                    surname = data;

                } else if (index == 3) {
                    date.setDate(data);

                } else if (index == 4) {

                    if(i == 0) {
                        this.name = data;

                    }

                    else if(!data.equals(this.name)) {
                        System.out.println("Error : Wrong promotion in file at line " + (i + 1));
                        check = false;
                    }


                } else
                    System.out.println("invalid data::" + data);

                index++;

            }

            if(check) {
                // on ajoute cet étudiant avec le data correspondant 
                Student s = new Student(surname, name, date, this.name);
                students.add(s);
            }

            index = 0;
            i++;
        }

        scanner.close();
    }

    public void saveAsCsv(String fileName) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        for(Student s : this) {

            writer.write(s.getID() + "," +  s.getName() + "," + s.getSurname() + "," + s.getBirthDate() + "," + name + "\n");
        }

        writer.close();
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student getStudent(int i){
        return students.get(i);
    }

    public String getName() {
        return name;
    }

    public Student searchStudentWithID(int ID) {

        for(Student s : this) {

            if(s.getID() == ID)
                return s;
        }

        return null;
    }

    public void sortByAverageGrade(boolean ascendingOrder) {
        if(ascendingOrder) students.sort(StudentComparator.AVERAGE_GRADE_ASCENDING_ORDER);
        else students.sort(StudentComparator.AVERAGE_GRADE_DESCENDING_ORDER);
    }

    public void sortByMedianGrade(boolean ascendingOrder) {
        if(ascendingOrder) students.sort(StudentComparator.MEDIAN_GRADE_ASCENDING_ORDER);
        else students.sort(StudentComparator.MEDIAN_GRADE_DESCENDING_ORDER);
    }


    public double getPromotionAverageGrade (){

        double sum = 0;

        for (Student s : this){

            sum = sum + s.getAverageGrade();

        }

        if (this.size() > 0)
            return (sum / this.size());

        return 0;
    }

    public double getPromotionAverageGrade (String subject){

        double sum = 0;

        for (Student s : this){

            sum = sum + s.getAverageGrade(subject);

        }

        if (this.size() > 0)
            return (sum / this.size());

        return 0;
    }

    public float getPromotionMedianGrade(){

        ArrayList<Exam> examList = new ArrayList<>();


        for (Student s : this){

            examList.addAll(s.getExams());

        }

        if(examList.isEmpty())
            return 0;

        Collections.sort(examList);

        if(examList.size() > 1)
        {
            float medianIndex = Math.round((float)examList.size() / 2) - 1;

            return ( (examList.size() % 2 == 0)
                    ? (examList.get((int)medianIndex).getGrade() + examList.get((int)medianIndex + 1).getGrade()) / 2
                    : examList.get(examList.size() / 2).getGrade());
        }

        return examList.get(0).getGrade();

    }

    public float getPromotionMedianGrade( String subject){

        ArrayList<Float> gradeList = new ArrayList<>();


        for (Student s : this){

            gradeList.addAll(s.getGrades(subject));

        }

        if(gradeList.isEmpty())
            return 0;

        Collections.sort(gradeList);

        if(gradeList.size() > 1)
        {
            float medianIndex = Math.round((float)gradeList.size() / 2) - 1;

            return ( (gradeList.size() % 2 == 0) // Si pair
                    ? (gradeList.get((int)medianIndex) + gradeList.get((int)medianIndex + 1)) / 2 // alors on fait la moyenne des milieux
                    : gradeList.get(gradeList.size() / 2)); // Sinon on retourne le milieu
        }

        return gradeList.get(0);

    }


    public float getMinValue(String subject){

        ArrayList<Float> gradeList = new ArrayList<>();


        for (Student s : this){

            gradeList.addAll(s.getGrades(subject));

        }

        if(gradeList.isEmpty())
            return 0;

        Collections.sort(gradeList); // on tri par ordre croissant la liste de note 

        return gradeList.get(0); // On retourne donc la premiere valeur

    }


    public float getMaxValue(String subject){

        ArrayList<Float> gradeList = new ArrayList<>();


        for (Student s : this){

            gradeList.addAll(s.getGrades(subject));

        }

        if(gradeList.isEmpty())
            return 0;

        Collections.sort(gradeList,Collections.reverseOrder()); // On tri dans l'ordre décroissant

        return gradeList.get(0); // On retourne la premiere valeur

    }



    @Override
    public int size() {
        return students.size();
    }

    @Override
    public boolean isEmpty() {
        return students.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return students.contains(o);
    }

    @Override
    public Iterator<Student> iterator() {
        return students.iterator();
    }

    @Override
    public Object[] toArray() {
        return students.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return students.toArray(a);
    }

    public boolean add(String name, String surname, Date birthDate) {
        return this.add(new Student(name, surname, birthDate,this.name));
    }

    @Override
    public boolean add(Student student) {
        return students.add(student);
    }

    @Override
    public boolean remove(Object o) {
        return students.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return students.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Student> c) {
        return students.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return students.retainAll(c);
    }

    @Override
    public void clear() {
        students.clear();
    }

    @Override
    public boolean equals(Object o) {
        return students.equals(o);
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }

    @Override
    public void forEach(Consumer<? super Student> action) {
        students.forEach(action);
    }

    @Override
    public Spliterator<Student> spliterator() {
        return students.spliterator();
    }
}
