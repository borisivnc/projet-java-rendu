package grades.ihm;

import grades.promotion.Exam;
import grades.promotion.Promotion;
import grades.promotion.Student;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class BarChartGraph extends ApplicationFrame {



    public BarChartGraph(String applicationTitle , String chartTitle, ArrayList<Student> students) throws FileNotFoundException {
        super( applicationTitle );
        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "Eleve",
                "Moyenne Generale",
                createDataset(students),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel( barChart );
        chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }


    private CategoryDataset createDataset(ArrayList<Student> students ) throws FileNotFoundException {


        final DefaultCategoryDataset dataset =
                new DefaultCategoryDataset( );

        // On prend comme valeur pour le graphique la moyenne génerale de chaque élève et leur prénom en abscisses
        dataset.setValue(students.get(0).getAverageGrade(),"Moyenne Generale", students.get(0).getName());
        dataset.setValue(students.get(1).getAverageGrade(),"Moyenne Generale",students.get(1).getName());
        dataset.setValue(students.get(2).getAverageGrade(),"Moyenne Generale",students.get(2).getName());
        dataset.setValue(students.get(3).getAverageGrade(),"Moyenne Generale",students.get(3).getName());
        dataset.setValue(students.get(4).getAverageGrade(),"Moyenne Generale",students.get(4).getName());
        dataset.setValue(students.get(5).getAverageGrade(),"Moyenne Generale",students.get(5).getName());


        return dataset;
    }




}
