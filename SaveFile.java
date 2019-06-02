	//C:\Users\steve\Desktop\Months

	import java.io.BufferedWriter;
	import java.io.File;
	import java.io.FileWriter;
	import java.io.IOException;

	public class SaveFile {

		final String path = "C:\\Users\\steve\\Desktop\\Months"; //once we have this in a application folder that will be destination. insert your desired path here
																// for testing
		BufferedWriter saver = null;
		
		public void saveAFile(MonthClass targetMonth) {
		
			try {
			
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

