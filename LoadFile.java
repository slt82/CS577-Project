import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.util.Scanner;

public class LoadFile {

	public void loadAFile(MonthList targetList) {
		
		JFileChooser fileSelector = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		
		int selectionValue = fileSelector.showOpenDialog(null);
		
		if (selectionValue == JFileChooser.APPROVE_OPTION) {
			
			File fileSelection = fileSelector.getSelectedFile();
			
			String monthData = fileSelection.getName();
			
			String cutData = monthData.substring(0, monthData.length() - 4);
			
			String[] splitArr = cutData.split("-");
			
			MonthClass newMonth = new MonthClass(splitArr[1], splitArr[0]);
			
			targetList.addMonthNode(newMonth);
			
			try {
				
				Scanner load = new Scanner(fileSelection).useDelimiter("/");
				
					while (load.hasNext()) {
						
						TransactionObject newTransaction = new TransactionObject(load.nextFloat(), load.nextInt(), load.next(), load.nextInt(), load.nextBoolean());
					
						newMonth.monthTransactions.addTranNode(newTransaction);
					}
				
				load.close();
				
			} catch(Exception exc) {
				
				exc.printStackTrace();
			}
			
		}
	}
}
