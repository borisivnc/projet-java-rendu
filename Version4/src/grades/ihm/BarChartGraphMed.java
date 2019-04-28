package grades.ihm;

import grades.promotion.Promotion;
import grades.promotion.Student;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class BarChartGraphMed extends JFrame {


    public BarChartGraphMed( String applicationTitle , String chartTitle,  ArrayList<Student> students ) throws FileNotFoundException {
        super( applicationTitle );
        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "Eleve",
                "Mediane Generale",
                createDataset(students),
                PlotOrientation.VERTICAL,
                true, true, false);


        ChartPanel chartPanel = new ChartPanel( barChart );
        chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }


    private CategoryDataset createDataset( ArrayList<Student> students) throws FileNotFoundException {


        final DefaultCategoryDataset dataset =
                new DefaultCategoryDataset( );

        dataset.setValue(students.get(0).getMedianGrade(),"Mediane Generale", students.get(0).getName());
        dataset.setValue(students.get(1).getMedianGrade(),"Mediane Generale",students.get(1).getName());
        dataset.setValue(students.get(2).getMedianGrade(),"Mediane Generale",students.get(2).getName());
        dataset.setValue(students.get(3).getMedianGrade(),"Mediane Generale",students.get(3).getName());
        dataset.setValue(students.get(4).getMedianGrade(),"Mediane Generale",students.get(4).getName());
        dataset.setValue(students.get(5).getMedianGrade(),"Mediane Generale",students.get(5).getName());


        return dataset;
    }





}
