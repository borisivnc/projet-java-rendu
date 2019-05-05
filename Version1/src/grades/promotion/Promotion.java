package grades.promotion;

import grades.util.Date;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Promotion implements Iterable<Student>, Collection<Student>, StudentComparator {

    private ArrayList<Student> students;
    private String name;

    public Promotion(String name) {

        students = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student getStudent(int i){
        return students.get(i);
    }

    public Student searchStudentWithID(int ID) {

        // On parcourt les étudiants 
        for(Student s : this) {

            if(s.getID() == ID) // si l'ID de l'étudiant correspond à l'ID recherché
                return s;    // On retourne cet étudiant
        }

        return null;  // Sinon on retourne null
    }

    public void sortByAverageGrade(boolean ascendingOrder) {
        if(ascendingOrder) students.sort(StudentComparator.AVERAGE_GRADE_ASCENDING_ORDER); // On tri en ordre croissant les étudiants en fonction de leur moyenne
        else students.sort(StudentComparator.AVERAGE_GRADE_DESCENDING_ORDER); // sinon on tri en ordre décroissant les étudiants 
    }

    public void sortByMedianGrade(boolean ascendingOrder) { // Ici on tri les étudiant on fontion de leur médiane
        if(ascendingOrder) students.sort(StudentComparator.MEDIAN_GRADE_ASCENDING_ORDER);
        else students.sort(StudentComparator.MEDIAN_GRADE_DESCENDING_ORDER);
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
