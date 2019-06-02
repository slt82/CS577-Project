import javax.swing.JDialog;
import javax.swing.JLabel;

public class ErrorDialog extends JDialog
{
	String errorMsg = "";
	
	public void setErrorMsg(String message)
	{
		errorMsg = message;
	}
	
	public void showDialog()
	{
		setTitle("Error");
		setModal(true);
		JLabel errorMessage = new JLabel(errorMsg);
		add(errorMessage);
		pack();
		setVisible(true);
	}
}