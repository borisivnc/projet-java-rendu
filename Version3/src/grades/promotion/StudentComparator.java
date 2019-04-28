package grades.promotion;

import java.util.Comparator;

public interface StudentComparator {

    public static Comparator<Student> AVERAGE_GRADE_ASCENDING_ORDER = new Comparator<Student>() {

        @Override
        public int compare(Student s1, Student s2) {
            return Float.compare(s1.getAverageGrade(), s2.getAverageGrade());
        }

    };

    public static Comparator<Student> AVERAGE_GRADE_DESCENDING_ORDER = new Comparator<Student>() {

        @Override
        public int compare(Student s1, Student s2) {

            return (s1.getAverageGrade() == s2.getAverageGrade()) ? 0 :
                    (s2.getAverageGrade() < s2.getAverageGrade()) ? 1 : -1;
        }

    };

    public static Comparator<Student> MEDIAN_GRADE_ASCENDING_ORDER = new Comparator<Student>() {

        @Override
        public int compare(Student s1, Student s2) {
            return Float.compare(s1.getMedianGrade(), s2.getMedianGrade());
        }

    };

    public static Comparator<Student> MEDIAN_GRADE_DESCENDING_ORDER = new Comparator<Student>() {

        @Override
        public int compare(Student s1, Student s2) {

            return (s1.getMedianGrade() == s2.getMedianGrade()) ? 0 :
                    (s2.getMedianGrade() < s2.getMedianGrade()) ? 1 : -1;
        }

    };


}
