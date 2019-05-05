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
        // on sauvegarde chaque ligne dans un nouveau fichier csv
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
