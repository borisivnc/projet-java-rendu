package grades.ihm;

import grades.promotion.Teacher;
import grades.util.Date;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TeachersModel extends AbstractTableModel {

    private final String[] headers = { "Nom", "Prénom" };

    private ArrayList<Teacher> teachers;

    public TeachersModel() {
        teachers = new ArrayList<>();
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public int getRowCount() {
        return teachers.size();
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
                return teachers.get(rowIndex).getSurname();

            case 1:
                // Prénom
                return teachers.get(rowIndex).getName();

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
                return String.class;

            default:
                return Object.class;
        }
    }

    public boolean isCellEditable(int row, int col) {
        return true;
    }

    public void removeRow(int row) {
        if(teachers.size() > row) {
            teachers.remove(row);
            fireTableRowsDeleted(row,row);
        } else
            System.out.println("Row > size");
    }


}
