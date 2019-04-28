package grades.ihm;

import grades.promotion.Student;
import grades.util.Date;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class StudentsModel extends AbstractTableModel {

    private final String[] headers = { "Nom", "Prénom", "Date de naissance", "Promotion" };

    private ArrayList<Student> students;

    public StudentsModel() {
        students = new ArrayList<>();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {

            case 0:
                // Nom
                return students.get(rowIndex).getSurname();

            case 1:
                // Prénom
                return students.get(rowIndex).getName();

            case 2:
                // Date de naissance
                return students.get(rowIndex).getBirthDate();

            case 3:
                // Promotion
                return students.get(rowIndex).getPromotion();

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
            case 3:
                return String.class;

            case 2:
                return Date.class;

            default:
                return Object.class;
        }
    }

    public boolean isCellEditable(int row, int col) {
        return true;
    }

    public void removeRow(int row) {
        if(students.size() > row) {
            students.remove(row);
            fireTableRowsDeleted(row,row);
        } else
            System.out.println("Row > size");
    }


}
