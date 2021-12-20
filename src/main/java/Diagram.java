import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.util.Map;


public class Diagram extends JFrame {
    public Diagram(Map<String, Double> transactions) {
        super("Переводы");

        JFreeChart pieChart = ChartFactory.createPieChart3D(
                "Сумма переводов по месяцам за 2020 год",
                getDataset(transactions),
                true,
                true,
                false);

        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setStartAngle(290);
        plot.setForegroundAlpha(0.8f);

        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 540));
        setContentPane(chartPanel);
    }

    private static DefaultPieDataset getDataset(Map<String, Double> transactions) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        transactions.forEach(dataset::setValue);
        return dataset;
    }
}
