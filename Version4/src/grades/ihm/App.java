package grades.ihm;

import grades.promotion.Exam;
import grades.promotion.Promotion;
import grades.promotion.Student;
import grades.promotion.Teacher;
import grades.util.Date;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class App extends JFrame implements ActionListener, WindowListener {

    private JPanel mainPanel;

    private JPanel students_card;
    private JPanel teachers_card;
    private JPanel grades_card;
    private JPanel graph_card;

    private JPanel cards;

    private JTabbedPane tabbedPane;
    
    // On crée 4 panel
    private final static String STUDENTS_PANEL  = "Gestion des élèves";
    private final static String TEACHERS_PANEL  = "Gestion des professeurs";
    private final static String GRADES_PANEL    = "Gestion des notes";
    private final static String GRAPH_PANEL    = "Graphique sur moyenne";


    private Promotion promotion;

    private StudentsModel studentsModel;
    private JTable studentsTable;

    private TeachersModel teachersModel;
    private JTable teachersTable;

    private ExamsModel examsModel;
    private JTable examsTable;

    private JTable graphTable;

    private JTextField studentNameTextField;
    private JTextField studentSurnameTextField;
    private JTextField dateTextField;

    JButton addStudentButton;
    JButton studentDeleteRowButton;


    private JTextField teacherNameTextField;
    private JTextField teacherSurnameTextField;

    JButton addTeacherButton;
    JButton teacherDeleteRowButton;

    private JTextField examSubjectTextField;
    private JTextField examTeacherNameTextField;
    private JTextField examTeacherSurnameTextField;
    private JTextField examStudentNameTextField;
    private JTextField examStudentSurnameTextField;
    private JTextField gradeTextField;

    JButton addNotesButton;
    JButton notesDeleteRowButton;
    JButton chartButton;
    JButton chartButton2;

    private App(){

        super();

        promotion   = new Promotion();


        try {
            promotion.loadFromCsv("StudentList.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // On crée des professeurs qui ensuite ajoute des notes aux élèves de la liste
        Teacher a = new Teacher("Pascal","Marivaux");
        Teacher b = new Teacher("Helene","Zaric");
        Teacher c = new Teacher("Fabien","Silva");
        Teacher d = new Teacher("Walter","White");

        a.addGrade("Math",17 , promotion.getStudent(0) );
        a.addGrade("Math",10 ,promotion.getStudent(1));
        a.addGrade("Math",9 ,promotion.getStudent(2));
        a.addGrade("Math",8 ,promotion.getStudent(3));
        a.addGrade("Math",15 ,promotion.getStudent(4));
        a.addGrade("Math",11 ,promotion.getStudent(5));

        a.addGrade("Math",10 , promotion.getStudent(0) );
        a.addGrade("Math",15 ,promotion.getStudent(1));
        a.addGrade("Math",13 ,promotion.getStudent(2));
        a.addGrade("Math",14 ,promotion.getStudent(3));
        a.addGrade("Math",10 ,promotion.getStudent(4));
        a.addGrade("Math",16 ,promotion.getStudent(5));

        b.addGrade("English",13 ,promotion.getStudent(0));
        b.addGrade("English",15 ,promotion.getStudent(1));
        b.addGrade("English",13 ,promotion.getStudent(2));
        b.addGrade("English",10 ,promotion.getStudent(3));
        b.addGrade("English",15 ,promotion.getStudent(4));
        b.addGrade("English",12 ,promotion.getStudent(5));

        b.addGrade("English",17 ,promotion.getStudent(0));
        b.addGrade("English",10 ,promotion.getStudent(1));
        b.addGrade("English",20 ,promotion.getStudent(2));
        b.addGrade("English",12 ,promotion.getStudent(3));
        b.addGrade("English",10 ,promotion.getStudent(4));
        b.addGrade("English",10 ,promotion.getStudent(5));


        c.addGrade("IT", 12,promotion.getStudent(0));
        c.addGrade("IT",19 ,promotion.getStudent(1));
        c.addGrade("IT",15 ,promotion.getStudent(2));
        c.addGrade("IT",12 ,promotion.getStudent(3));
        c.addGrade("IT",15 ,promotion.getStudent(4));
        c.addGrade("IT",14 ,promotion.getStudent(5));


        d.addGrade("Chemistry",18 ,promotion.getStudent(0));
        d.addGrade("Chemistry",13 ,promotion.getStudent(1));
        d.addGrade("Chemistry",15 ,promotion.getStudent(2));
        d.addGrade("Chemistry",17 ,promotion.getStudent(3));
        d.addGrade("Chemistry",12 ,promotion.getStudent(4));
        d.addGrade("Chemistry",14 ,promotion.getStudent(5));

        studentsModel = new StudentsModel();

        studentsModel.setStudents(promotion.getStudents());

        studentsTable = new JTable(studentsModel);

        teachersModel = new TeachersModel();

        teachersTable = new JTable(teachersModel);


        try {
            teachersModel.setTeachers( Teacher.loadFromCsv("TeacherList.csv") );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        examsModel = new ExamsModel();

        examsTable = new JTable(examsModel);

        try {
            examsModel.setExams(Exam.loadFromCsv("ExamList.csv", studentsModel.getStudents(), teachersModel.getTeachers() ) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void build() {

        setTitle("App");
        setSize(800,600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout());

        addWindowListener(this);
    }

    private void addCompononents() throws FileNotFoundException {

        mainPanel = (JPanel) this.getContentPane();

        students_card = new JPanel();
        teachers_card = new JPanel();
        grades_card = new JPanel();
        graph_card = new JPanel();


        // Students management card

        JScrollPane studentsTablePane = new JScrollPane(studentsTable);

        studentsTablePane.createVerticalScrollBar();
        studentsTablePane.setPreferredSize(new Dimension(750, 400));

        JPanel form = new JPanel(new BorderLayout());

        // On ajoute des champs de texte pour le prenom, nom et la date
        studentNameTextField = new JTextField(20);
        studentSurnameTextField = new JTextField(20);
        dateTextField = new JTextField(20);

        // On crée le bouton Ajouter et Supprimer la ligne
        addStudentButton = new JButton("Ajouter");
        addStudentButton.addActionListener(this);

        studentDeleteRowButton = new JButton("Supprimer la ligne sélectionnée");
        studentDeleteRowButton.addActionListener(this);

        JPanel textFieldsPanel = new JPanel(new FlowLayout());

        textFieldsPanel.add(studentSurnameTextField);
        textFieldsPanel.add(studentNameTextField);
        textFieldsPanel.add(dateTextField);
        
        // On ajoute ce qu'on a crée dans le panel, on fait de même pour les professeurs, les notes et le graphes
        form.add(textFieldsPanel, BorderLayout.CENTER);
        form.add(addStudentButton, BorderLayout.PAGE_END);

        students_card.add(studentsTablePane, BorderLayout.NORTH);
        students_card.add(form, BorderLayout.CENTER);
        students_card.add(studentDeleteRowButton, BorderLayout.SOUTH);
        
        // Teachers management card

        JScrollPane teachersTablePane = new JScrollPane(teachersTable);

        teachersTablePane.createVerticalScrollBar();
        teachersTablePane.setPreferredSize(new Dimension(750, 400));

        JPanel teachersForm = new JPanel(new BorderLayout());

        teacherNameTextField = new JTextField(20);
        teacherSurnameTextField = new JTextField(20);

        addTeacherButton = new JButton("Ajouter");
        addTeacherButton.addActionListener(this);

        teacherDeleteRowButton = new JButton("Supprimer la ligne sélectionnée");
        teacherDeleteRowButton.addActionListener(this);

        JPanel teachersTextFieldsPanel = new JPanel(new FlowLayout());

        teachersTextFieldsPanel.add(teacherSurnameTextField);
        teachersTextFieldsPanel.add(teacherNameTextField);

        teachersForm.add(teachersTextFieldsPanel, BorderLayout.CENTER);
        teachersForm.add(addTeacherButton, BorderLayout.PAGE_END);

        teachers_card.add(teachersTablePane, BorderLayout.NORTH);
        teachers_card.add(teachersForm, BorderLayout.CENTER);
        teachers_card.add(teacherDeleteRowButton, BorderLayout.SOUTH);


        // Grades management card



        JScrollPane examsTablePane = new JScrollPane(examsTable);

        examsTablePane.createVerticalScrollBar();
        examsTablePane.setPreferredSize(new Dimension(750, 400));


        JPanel Examform = new JPanel(new BorderLayout());

        examSubjectTextField = new JTextField(10);
        examTeacherNameTextField = new JTextField(10);
        examTeacherSurnameTextField = new JTextField(10);
        examStudentNameTextField = new JTextField(10);
        examStudentSurnameTextField = new JTextField(10);
        gradeTextField = new JTextField(5);

        addNotesButton = new JButton("Ajouter");
        addNotesButton.addActionListener(this);

        notesDeleteRowButton = new JButton("Supprimer la ligne sélectionnée");
        notesDeleteRowButton.addActionListener(this);

        JPanel NotestextFieldsPanel = new JPanel(new FlowLayout());

        NotestextFieldsPanel.add(examSubjectTextField);
        NotestextFieldsPanel.add(examTeacherNameTextField);
        NotestextFieldsPanel.add(examTeacherSurnameTextField);
        NotestextFieldsPanel.add(examStudentNameTextField);
        NotestextFieldsPanel.add(examStudentSurnameTextField);
        NotestextFieldsPanel.add(gradeTextField);

        Examform.add(NotestextFieldsPanel, BorderLayout.CENTER);
        Examform.add(addNotesButton, BorderLayout.PAGE_END);

        grades_card.add(examsTablePane, BorderLayout.NORTH);
        grades_card.add(Examform, BorderLayout.CENTER);
        grades_card.add(notesDeleteRowButton, BorderLayout.SOUTH);

        // Graphique Moyenne et Mediane card

        JScrollPane graphTablePane = new JScrollPane(graphTable);

        graphTablePane.createVerticalScrollBar();
        graphTablePane.setPreferredSize(new Dimension(750, 400));

        JPanel graphform = new JPanel(new BorderLayout());
        chartButton = new JButton("Voir Graphique des moyennes");
        chartButton.addActionListener(this);

        graphform.add(chartButton, BorderLayout.CENTER);

        graph_card.add(graphform, BorderLayout.CENTER);

        chartButton2 = new JButton("Voir Graphique des medianes");
        chartButton2.addActionListener(this);

        graphform.add(chartButton2, BorderLayout.NORTH);

        graph_card.add(graphform, BorderLayout.NORTH);

        cards = new JPanel(new CardLayout());

        cards.add(students_card, STUDENTS_PANEL);
        cards.add(grades_card, GRADES_PANEL);



        tabbedPane = new JTabbedPane();

        tabbedPane.add(STUDENTS_PANEL, students_card);
        tabbedPane.add(TEACHERS_PANEL, teachers_card);
        tabbedPane.add(GRADES_PANEL, grades_card);
        tabbedPane.add(GRAPH_PANEL, graph_card);

        mainPanel.add(tabbedPane, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    
        // On voit ici les actions émise lors du clique sur les boutons
        Object source = e.getSource();

        if(source == addStudentButton) {

            String name = studentNameTextField.getText();
            String surname = studentSurnameTextField.getText();
            String birthDate = dateTextField.getText();

            Date d = new Date();

            ArrayList<Student> students = studentsModel.getStudents();

            if(!(name.isEmpty() || surname.isEmpty() || birthDate.isEmpty())) {

                d.setDate(birthDate);

                students.add(new Student(name, surname, d, promotion.getName()));
                studentsModel.fireTableDataChanged();
            }
        }

        else if(source == studentDeleteRowButton) {

            studentsModel.removeRow( studentsTable.getSelectedRow() );
        }
        
        else if(source == addTeacherButton) {

            String name = teacherNameTextField.getText();
            String surname = teacherSurnameTextField.getText();

            ArrayList<Teacher> teachers = teachersModel.getTeachers();

            if(!(name.isEmpty() || surname.isEmpty())) {

                teachers.add(new Teacher(name, surname));
                teachersModel.fireTableDataChanged();
            }
        }

        else if(source == teacherDeleteRowButton) {

            teachersModel.removeRow( teachersTable.getSelectedRow() );
        }

        else if(source == addNotesButton) {

            String subject = examSubjectTextField.getText();
            String teacherName = examTeacherNameTextField.getText();
            String teacherSurname = examTeacherSurnameTextField.getText();
            String studentName = examStudentNameTextField.getText();
            String studentSurname = examStudentSurnameTextField.getText();
            String grade = gradeTextField.getText();


            ArrayList<Exam> exams = examsModel.getExams();

            if(!(subject.isEmpty() || teacherName.isEmpty() || teacherSurname.isEmpty() || studentName.isEmpty() || studentSurname.isEmpty()|| grade.isEmpty())) {

                Student s = null;
                Teacher t = null;

                for(Student st : studentsModel.getStudents()) {

                    if(st.getName().equals(studentName) && st.getSurname().equals(studentSurname)) {
                        s = st;

                    }
                }

                for(Teacher teacher : teachersModel.getTeachers()) {

                    if(teacher.getName().equals(teacherName) && teacher.getSurname().equals(teacherSurname)) {
                        t = teacher;
                    }
                }

                float gradeFloat = Float.parseFloat(grade);
                exams.add(new Exam(subject,s, t ,gradeFloat ));
                examsModel.fireTableDataChanged();
            }
        }

        else if(source == notesDeleteRowButton) {

            examsModel.removeRow( examsTable.getSelectedRow() );
        }

        else if(source == chartButton) {

            BarChartGraph chart = null;
            try {
                chart = new BarChartGraph("Graphique Moyenne Générale","Moyennes des élèves dans une promotion",studentsModel.getStudents());
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            chart.pack();
            RefineryUtilities.centerFrameOnScreen(chart);
            chart.setVisible(true);
        }

        else if(source == chartButton2) {

            BarChartGraphMed chart = null;
            try {
                chart = new BarChartGraphMed("Graphique Mediane Générale","Mediane des élèves dans une promotion",studentsModel.getStudents());
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            chart.pack();
            RefineryUtilities.centerFrameOnScreen(chart);
            chart.setVisible(true);
        }
    }


    private static void createAndShowGUI() throws FileNotFoundException {

        App frame = new App();

        frame.build();
        frame.addCompononents();
        frame.setVisible(true);
    }

    public static void main(String[] args){

        SwingUtilities.invokeLater(() -> {
            try {
                createAndShowGUI();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

    }


    // Sauvegarde a la fermeture du programme

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Closing...");
        try {
            promotion.saveAsCsv("StudentList.csv");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
