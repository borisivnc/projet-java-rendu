package grades.ihm;

import grades.promotion.Promotion;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import java.io.FileNotFoundException;


public class BarChartGraph extends ApplicationFrame {



    public BarChartGraph( String applicationTitle , String chartTitle, Promotion p ) throws FileNotFoundException {
        super( applicationTitle );
        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "Eleve",
                "Moyenne Generale",
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

        // On prend comme valeur pour le graphe la moyenne de chaque élève et le prenom de celui-ci
        dataset.setValue(p.getStudent(0).getAverageGrade(),"Moyenne Generale", p.getStudent(0).getName());
        dataset.setValue(p.getStudent(1).getAverageGrade(),"Moyenne Generale",p.getStudent(1).getName());
        dataset.setValue(p.getStudent(2).getAverageGrade(),"Moyenne Generale",p.getStudent(2).getName());
        dataset.setValue(p.getStudent(3).getAverageGrade(),"Moyenne Generale",p.getStudent(3).getName());
        dataset.setValue(p.getStudent(4).getAverageGrade(),"Moyenne Generale",p.getStudent(4).getName());
        dataset.setValue(p.getStudent(5).getAverageGrade(),"Moyenne Generale",p.getStudent(5).getName());


        return dataset;
    }




}
