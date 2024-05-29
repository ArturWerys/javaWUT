package naukaDoKolosa1;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;

public class Wykres {

	static JFreeChart chart;
	public static DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	public Wykres() {

		// Tworzenie wykresu słupkowego kategorycznego
		chart = ChartFactory.createBarChart("Ilość wylosować poszczególnych kolorów", "Kolor", // Opis osi X
				"ilość", // Opis osi Y
				dataset, // Dane
				PlotOrientation.VERTICAL, // Orientacja wykresu
				true, // Legenda
				true, // Tooltips
				false // URL
		);

		// ZMIANA WYGLĄDU
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.white);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		plot.setOutlineVisible(false);

		// Ustawienie osi X
		CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
		xAxis.setCategoryMargin(0.1); // Margines między słupkami

		// ZMIANA WYGLĄDU LEGENDY
		LegendTitle legend = chart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setPadding(20.0, 20.0, 0.0, 0.0);

		// Dodanie odpowiednich kolorów dla słupków
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, Color.GREEN); // Zielony

	}

	public void updateChart(int greenC, int redC, int blueC, int blackC) {
		dataset.setValue(greenC, "Kolory", "Zielony");
		dataset.setValue(redC, "Kolory", "Czerwony");
		dataset.setValue(blueC, "Kolory", "Niebieski");
		dataset.setValue(blackC, "Kolory", "Czarny");

	}

	public void saveChartToFile(JFrame frame, JFreeChart chart, int width, int height) {
		JFileChooser fileChooser = new JFileChooser();
		int returnValue = fileChooser.showSaveDialog(frame);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			try {
				ChartUtilities.saveChartAsPNG(file, chart, width, height);
				System.out.println("Wykres został zapisany do pliku: " + file.getAbsolutePath());
			} catch (IOException e) {
				System.err.println("Wystąpił błąd podczas zapisywania wykresu: " + e.getMessage());
			}
		}
	}

	public JFreeChart getChart() {
		return chart;
	}

	public ChartPanel getChartPanel() {
		return new ChartPanel(chart);
	}

}
