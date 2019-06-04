import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

	public class SaveFile {
		BufferedWriter saver = null;
		
		public void saveAFile(MonthClass targetMonth) {
		
			try {
				
				JFileChooser fileSelector = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				fileSelector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileSelector.setDialogTitle("Select Directory to Save File");
				fileSelector.setApproveButtonText("Save");
				
				int selectionValue = fileSelector.showOpenDialog(null);
				
				if (selectionValue == JFileChooser.APPROVE_OPTION)
				{
					File fileSelection = fileSelector.getSelectedFile();
					String path = fileSelector.getSelectedFile().getAbsolutePath();
					
					File file = new File(path + "\\" + targetMonth.getMonth() + "-" + targetMonth.getYear() + ".txt");
					
					if(!file.exists()) {
					
						file.createNewFile();
					}	
				
					FileWriter writer = new FileWriter(file);
					saver = new BufferedWriter(writer);
					
					int i = 0;
					
					while(i < targetMonth.monthTransactions.getLength()) {
						
						saver.write(targetMonth.monthTransactions.getTarget(i).getDollarValue() + "/" + targetMonth.monthTransactions.getTarget(i).getInType() + "/" +
								targetMonth.monthTransactions.getTarget(i).getTranType() + "/" + targetMonth.monthTransactions.getTarget(i).getDay() + "/" +
								targetMonth.monthTransactions.getTarget(i).getExpense() + "/");
						
						i++;
					}
				}
			
			} catch (IOException ie) {
				ie.printStackTrace();
			}
			
			finally {
				
				try {
					
					if(saver != null) {
						
						saver.close();
					}
					
				} catch(Exception exc) {
					
					System.out.println("Could not close BufferedWriter");
				}
			}
		}
	}

