import java.text.DecimalFormat;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.labels.*;
import org.jfree.chart.plot.*;
import org.jfree.data.general.DefaultPieDataset;

public class CategoryPieChart extends JFrame
{
	DefaultPieDataset dataset;
	JFrame pieChartFrame;
	MonthList monthList;
	MonthList selectedMonths;
	
	//initialize the pie chart
	public void initializePieChart(MonthList months)
	{
		//initialize the JFrame and the dataset
		monthList = months;
		pieChartFrame = new JFrame("Pie Chart");
		dataset = new DefaultPieDataset();
		selectedMonths = new MonthList();
	}
	
	public void initializePieChart()
	{
		pieChartFrame = new JFrame("Pie Chart");
		dataset = new DefaultPieDataset();
		selectedMonths = new MonthList();
	}
	
	//show the actual pie chart
	public void drawPieChart()
	{		
		//create the Pie chart
		JFreeChart chart = ChartFactory.createPieChart("Percent Spent in Each Category", dataset, true, true, false);
		PiePlot piePlot = (PiePlot) chart.getPlot();
		
		//Set the label properties
		piePlot.setSimpleLabels(true);
        PieSectionLabelGenerator gen = new
        		 StandardPieSectionLabelGenerator("{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
            piePlot.setLabelGenerator(gen);
		
        //add the chart to the panel and frame
		ChartPanel pieChartPanel = new ChartPanel(chart);
		pieChartFrame.add(pieChartPanel);			
		
		JPanel verticalLayout = new JPanel();
		verticalLayout.setLayout(new BoxLayout(verticalLayout,BoxLayout.Y_AXIS));
		verticalLayout.add(pieChartPanel);

		pieChartFrame.add(verticalLayout);
		
		pieChartFrame.pack();
		pieChartFrame.setTitle("Category Percents");
		pieChartFrame.setSize(750, 550);
		pieChartFrame.setVisible(true);
	}
	
	//function to set the value for a specific key

	public void addChangeDataValue(Comparable key, double value)
	{
		dataset.setValue(key, value);
	}
	
	//remove a key from the dataset
	
	public void removeDataValue(Comparable key)
	{
		dataset.remove(key);
	}

	//add all of the transactions from a specific month
	public void addMonth(MonthClass month)
	{
		TransactionList transactionList = month.monthTransactions;
		int numTransactions = transactionList.getLength();
		int currentTransaction = 0;
		while(numTransactions>currentTransaction)
		{
			TransactionObject transaction = transactionList.getTarget(currentTransaction);
			if(transaction.getExpense())
			{
				String description = transaction.getTranType();
				double tranValue = transaction.getDollarValue();
				double oldValue;
				try
				{
					oldValue = (double)dataset.getValue(description);
				}
				catch(Exception e)
				{
					oldValue = 0.0;
				}
				double newValue = oldValue+tranValue;
				dataset.setValue(description, newValue);
			}
			currentTransaction++;
		}
		selectedMonths.addMonthNode(month);
	}
	
	//remove all of the transactions from a specific month
	public void removeMonth(MonthClass month)
	{
		TransactionList transactionList = month.monthTransactions;
		int numTransactions = transactionList.getLength();
		int currentTransaction = 0;
		while(numTransactions>currentTransaction)
		{
			TransactionObject transaction = transactionList.getTarget(currentTransaction);
			String description = transaction.getTranType();
			float tranValue = transaction.getDollarValue();
			float oldValue = (float) dataset.getValue(description);
			float newValue = oldValue-tranValue;
			dataset.setValue(description, newValue);
			currentTransaction++;
		}
		//remove month from selectedMonths
	}
}
