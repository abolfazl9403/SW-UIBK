package org.example.services;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class GoldPlotService {
    public void plotGoldPrices(List<GoldPrice> goldPrices) throws IOException {
        if (goldPrices == null || goldPrices.isEmpty()) {
            System.out.println("No gold prices to plot.");
            return;
        }

        // Create a dataset for the chart
        XYSeries series = new XYSeries("Gold Prices");
        for (GoldPrice goldPrice : goldPrices) {
            LocalDateTime dateTime = goldPrice.getDate().atStartOfDay();
            Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
            series.add(date.getTime(), goldPrice.getPrice());
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);

        // Create the chart
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Gold Prices Over Time", // chart title
                "Date",                  // x-axis label
                "Gold Price (EUR)",      // y-axis label
                dataset,                 // data
                true,                    // include legend
                true,
                false
        );

        // Customize the chart appearance
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.setDomainAxis(new DateAxis("Date"));
        plot.setRangeAxis(new NumberAxis("Gold Price (EUR)"));

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
        renderer.setSeriesPaint(0, Color.BLUE);
        plot.setRenderer(renderer);

        // Save the chart as an image file
        File chartFile = new File("gold_prices_chart.jpg");
        ChartUtils.saveChartAsPNG(chartFile, chart, 800, 600);

        System.out.println("Gold prices chart saved to: " + chartFile.getAbsolutePath());
    }
}
