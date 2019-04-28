package grades.ihm;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.io.FileNotFoundException;
import grades.promotion.Promotion;

public class graph extends JFrame {


    public graph( String applicationTitle , String chartTitle, Promotion p ) throws FileNotFoundException {
        super( applicationTitle );
        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "Eleve",
                "Mediane Generale",
                createDataset(p),
                PlotOrientation.VERTICAL,
                true, true, false);


        ChartPanel chartPanel = new ChartPanel( barChart );
        chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }


    private CategoryDataset createDataset(Promotion p ) throws FileNotFoundException {


        final DefaultCategoryDataset dataset =
                new DefaultCategoryDataset( );

        dataset.setValue(p.getStudent(0).getMedianGrade(),"Mediane Generale", p.getStudent(0).getName());
        dataset.setValue(p.getStudent(1).getMedianGrade(),"Mediane Generale",p.getStudent(1).getName());
        dataset.setValue(p.getStudent(2).getMedianGrade(),"Mediane Generale",p.getStudent(2).getName());
        dataset.setValue(p.getStudent(3).getMedianGrade(),"Mediane Generale",p.getStudent(3).getName());
        dataset.setValue(p.getStudent(4).getMedianGrade(),"Mediane Generale",p.getStudent(4).getName());
        dataset.setValue(p.getStudent(5).getMedianGrade(),"Mediane Generale",p.getStudent(5).getName());


        return dataset;
    }





}
