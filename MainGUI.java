import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ListIterator;

public class MainGUI extends JPanel{
		
	public MainGUI() {
		
		//LINKED LIST
		MonthList monthsInList = new MonthList();
		
		//MAIN MENU COMPONENTS (these are available upon initialization)
		//------------------------------------------------------------------------------
		//adds the buttons on the GUI
		JButton addMonthButton = new JButton("Add Month");
		addMonthButton.setBounds(10,10,130,40);
		
		JButton saveMonthsButton = new JButton("Save Data");
		saveMonthsButton.setBounds(10,60,130,40);
		
		
		//ADD MONTH COMPONENTS (these are only visible once the add button is pressed)
		//==================================================================================
		//label for enter year
		JLabel enterYear = new JLabel();
		enterYear.setBounds(10,10,150,40);
		enterYear.setText("Enter the year: ");
		
		//label for enter month
		JLabel enterMonth = new JLabel();
		enterMonth.setBounds(10,70,150,40);
		enterMonth.setText("Enter the month: ");
		
		//text field for user year input
		JTextField yearText = new JTextField();
		yearText.setBounds(10,50,150,20);
		
		//text field for user month input
		JTextField monthText = new JTextField();
		monthText.setBounds(10,110,150,20);
		
		//button for creating a new month and adding to lists
		JButton createMonth = new JButton("Create");
		createMonth.setBounds(10,170,130,40);
		
		//button for loading a month from a file
		JButton loadMonth = new JButton("Load");
		loadMonth.setBounds(10,220,130,40);
		//===================================================================================
		
		//NEED BUTTONS FOR YEARLY ANALYSIS HERE-----------------------------------------------
		
		//JList implementation for listing month objects
		DefaultListModel monthListModel = new DefaultListModel();
		JList monthGUIList = new JList(monthListModel);
		
		JScrollPane monthPane = new JScrollPane(monthGUIList);
		monthPane.setBounds(200,10,250,500);
			
			//double click action block on a list item
			monthGUIList.addMouseListener(new MouseAdapter() {
				public void clicked(MouseEvent m) {
					JList monthGUIList = (JList)m.getSource();
					
					if(m.getClickCount() == 2) {
						
						int i = monthGUIList.locationToIndex(m.getPoint());
						//HERE IS WHERE THE SUB-OPS TO MODIFY, DEL, OR OPEN A MONTH SHOULD GO
					}
				}
			});
		
			//action block for the add month button
			addMonthButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent a) {
						
					//hides non-relevant options for this task
					saveMonthsButton.setVisible(false);
					addMonthButton.setVisible(false);
					
					//sets relevant components to be visible
					enterYear.setVisible(true);
					enterMonth.setVisible(true);
					monthText.setVisible(true);
					yearText.setVisible(true);
					createMonth.setVisible(true);
					loadMonth.setVisible(true);
					
					yearText.setText("");
					monthText.setText("");
									
				}	
			});
					
					//creates a new month based on user input
					createMonth.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent b) {
								
								String yT = yearText.getText();
								String mT = monthText.getText();
								
								//verify input integrity (WORTH IMPROVING FOR BETTER ERROR HANDLING?)
								if(yT.length() == 4 & mT.length() > 1) {
									
									//creates the new month class with input at parameters
									//and adds that to the linked list
									MonthClass newMonth = new MonthClass(yT, mT);
									monthsInList.addMonthNode(newMonth);
									
									yearText.setText("");
									monthText.setText("");
									
									//hides non-relevant options for this task
									saveMonthsButton.setVisible(true);
									addMonthButton.setVisible(true);
									
									//sets relevant components to be visible
									enterYear.setVisible(false);
									enterMonth.setVisible(false);
									monthText.setVisible(false);
									yearText.setVisible(false);
									createMonth.setVisible(false);
									loadMonth.setVisible(false);
									
									populateList(monthListModel, monthsInList);
								}
								
								else {
									
									//HAVE DIALOG BOX EXPLAINING WRONG INPUT
									yearText.setText("");
									monthText.setText("");
									
								}
							}
					});
					
					//NEED BUTTON EVENT FOR LOADING A MONTH FILE INTO LIST-------------------------------
			
			//save months button action block
			saveMonthsButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent a) {
					//SAVES ALL MONTH OBJECTS BY CALLING SAVE METHOD IN EACH MONTH CLASS
				}
			});
		
		//adds elements to the frame
		add(addMonthButton);
		add(saveMonthsButton);
		add(enterYear);
		add(enterMonth);
		add(monthText);
		add(yearText);
		add(createMonth);
		add(loadMonth);
		
		add(monthPane);	
		
		//sets stage for initial GUI options
		enterYear.setVisible(false);
		enterMonth.setVisible(false);
		monthText.setVisible(false);
		yearText.setVisible(false);
		createMonth.setVisible(false);
		loadMonth.setVisible(false);
	}
	
	//method for populating the pane with Month objects from the linked list
	public void populateList(DefaultListModel targetDLM, MonthList targetList) {
		
		targetDLM.removeAllElements();
		
		int index = 0;
		
		while(index < targetList.getLength()) {
			
			targetDLM.addElement(targetList.getTarget(index).getYear() + ", " + targetList.getTarget(index).getMonth());
			index++;
		}
	}
	
	//MAIN
	public static void main(String s[]) {
		
		//initializes and sizes the GUI frame
		JFrame mainFrame = new JFrame("Monease");
		
		//closing this GUI closes the program!
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainFrame.setContentPane(new MainGUI());
		
		mainFrame.setSize(500, 600);
		
		mainFrame.setLayout(null);
		mainFrame.setVisible(true);
	}
	
}
	