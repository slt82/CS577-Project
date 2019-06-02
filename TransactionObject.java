//SE577 Project: Monease
//Class for a particular transaction within a month

public class TransactionObject {
	
	private float dollarValue;
	private int inputType;
	private String transactionType;
	private int day;
	private boolean expense; //This equals true when it is an expense and it is false when it is an income transaction.
	
	//default null constructor
	public TransactionObject() {
		
		dollarValue = 0;
		inputType = 0;
		transactionType = null;
		day = 0;
		expense = false;
	}
	
	//alternate TransactionObject constructor for use with user input
	public TransactionObject(float newDolVal, int newInType, String newTranType, int newDay, boolean isExpense) {
		
		//mutators for initializing object with non null values
		dollarValue = newDolVal;
		inputType = newInType;
		transactionType = newTranType;
		day = newDay;
		expense = isExpense;
	}
	
	//mutator for modifying a transaction's dollar amount
	protected void modifyDolVal(float newVal) {
		
		dollarValue = newVal;
	}
	
	//mutator for modifying a transaction's input type (2 = from statement, 1 = manual entry)
	//this value should be checked to be either 1 or 2 in controller
	protected void modifyInType(int newInType) {
		
		inputType = newInType;
	}
	
	//mutator for modifying the type/descriptor of the transaction
	protected void modifyTranType(String newTranType) {
		
		transactionType = newTranType;
	}
	
	//mutator for modifying the day of the month of a transaction
	//controller should verify that this is not greater than 31 or less than 1
	protected void modifyDay(int newDay) {
		
		day = newDay;
	}
	
	protected void modifyIncomeOrExpense(boolean isExpense)
	{
		expense = isExpense;
	}
	
	//get method for a transaction's dollar value
	protected float getDollarValue() {
		
		return dollarValue;
	}
	
	//get method for a transaction's input type
	protected int getInType() {
		
		return inputType;
	}
	
	//get method for a transaction's type/descriptor
	protected String getTranType() {
		
		return transactionType;
	}
	
	//get method for a transaction's day of the month
	protected int getDay() {
		
		return day;
	}
	
	protected boolean getExpense()
	{
		return expense;
	}
}
