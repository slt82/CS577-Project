import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.xy.*;

public class MonthBalanceLineGraph extends JFrame
{
	
	XYSeriesCollection dataset;
	JFrame lineGraphFrame;
	MonthList monthList;
	MonthList selectedMonths;
	
	String[] longMonths = {"01", "03", "05", "07", "08", "10", "12"};
	String[] mediumMonths = {"04", "06", "09", "11"};
	String[] shortMonths = {"02"};

	//initialize the pie chart
	public void initializeLineGraph(MonthList allMonths)
	{
		//initialize the JFrame and the dataset
		lineGraphFrame = new JFrame("Line Graph");
		dataset = new XYSeriesCollection();
		monthList = allMonths;
		selectedMonths = new MonthList();
	}
	public void initializeLineGraph()
	{
		//initialize the JFrame and the dataset
		lineGraphFrame = new JFrame("Line Graph");
		dataset = new XYSeriesCollection();
		selectedMonths = new MonthList();
	}
	
	//show the actual line graph	
	public void drawLineGraph()
	{
		//initializes and sizes the GUI frame
		lineGraphFrame = new JFrame("Line Graph");
		JFreeChart chart = ChartFactory.createXYLineChart("Month Balance", "Day", "Balance", dataset, PlotOrientation.VERTICAL, true, true, false);
		ChartPanel chartPanel = new ChartPanel(chart);
		
		JPanel verticalLayout = new JPanel();
		verticalLayout.setLayout(new BoxLayout(verticalLayout,BoxLayout.Y_AXIS));
		verticalLayout.add(chartPanel);

		lineGraphFrame.add(verticalLayout);

		lineGraphFrame.pack();
		lineGraphFrame.setTitle("Line Graph");
		
		//lineGraphFrame.setLayout(null);
		
		lineGraphFrame.setSize(750, 550);
		lineGraphFrame.setVisible(true);
	}
	
	//add all of the transactions from a specific month
	public void addMonth(MonthClass month)
	{
		TransactionList transactionList = month.monthTransactions;
		int numTransactions = transactionList.getLength();
		int currentTransaction = 0;
		XYSeries newMonth = new XYSeries(month.getMonth()+"/"+month.getYear());
		if(Arrays.asList(longMonths).contains(month.getMonth()))
		{
			int day = 1;
			while(day<=31)
			{
				newMonth.add(day, 0);
				day++;
			}
		}
		else if(Arrays.asList(mediumMonths).contains(month.getMonth()))
		{
			int day = 1;
			while(day<=30)
			{
				newMonth.add(day, 0);
				day++;
			}
		}
		else if(Arrays.asList(shortMonths).contains(month.getMonth()))
		{
			int day = 1;
			while(day<=28)
			{
				newMonth.add(day, 0);
				day++;
			}
		}
		while(numTransactions>currentTransaction)
		{
			TransactionObject transaction = transactionList.getTarget(currentTransaction);
			int transactionDate = transaction.getDay();
			float transactionValue = transaction.getDollarValue();
			int index = newMonth.indexOf(transactionDate);
			double previousValue = (double) newMonth.getY(index);
			newMonth.update((Number)transactionDate, previousValue+transactionValue);
			updateLaterDays(newMonth, transactionDate, transactionValue);
			currentTransaction++;
		}
		dataset.addSeries(newMonth);
		selectedMonths.addMonthNode(month);
	}
	
	//update the future days so that we can keep an accurate record of how much was earned or spent in the month
	private void updateLaterDays(XYSeries series, int transactionDate, float newValue) 
	{
		if(transactionDate<series.getItemCount())
		{
			double dayValue = (double) series.getY(series.indexOf(transactionDate+1));
			series.update((Number)(transactionDate+1), newValue+dayValue);
			updateLaterDays(series, transactionDate+1, newValue);
		}
	}

	//remove all of the transactions from a specific month
	public void removeMonth(MonthClass month)
	{
		XYSeries oldMonthSeries = dataset.getSeries(month.getMonth()+month.getYear());
		dataset.removeSeries(oldMonthSeries);
		//selectedMonths. want to be able to remove the selected month from the MonthList
	}
}