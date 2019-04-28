package grades.ihm;

import grades.promotion.Exam;
import grades.util.Date;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ExamsModel extends AbstractTableModel {

    private final String[] headers = {  "Subject", "Teacher\'s name", "Teacher\'s surname",
                                        "Student\'s name", "Student\'s surname", "Grade" };

    private ArrayList<Exam> exams;

    public ExamsModel() {
        exams = new ArrayList<>();
    }

    public ArrayList<Exam> getExams() {
        return exams;
    }

    public void setExams(ArrayList<Exam> exams) {
        this.exams = exams;
    }

    @Override
    public int getRowCount() {
        return exams.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {

            case 0:

                return exams.get(rowIndex).getSubject();

            case 1:

                return exams.get(rowIndex).getTeacherGrading().getName();

            case 2:

                return exams.get(rowIndex).getTeacherGrading().getSurname();

            case 3:

                return exams.get(rowIndex).getStudentGraded().getName();

            case 4:

                return exams.get(rowIndex).getStudentGraded().getSurname();

            case 5:

                return exams.get(rowIndex).getGrade();

            default:
                throw new IllegalStateException();
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return headers[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                return String.class;

            case 5:
                return Float.class;

            default:
                return Object.class;
        }
    }

    public boolean isCellEditable(int row, int col) {
        return true;
    }

    public void removeRow(int row) {
        if(exams.size() > row) {
            exams.remove(row);
            fireTableRowsDeleted(row,row);
        } else
            System.out.println("Row > size");
    }


}
