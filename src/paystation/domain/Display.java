/*
 * The Display of a Parking Pay Station.
 *
 * Responsibilities:
 *
 * 1) Print Menu
 * 2) Take user Input
 * 3) Validate user unput
 * 4) Format Receipts for visibility
 * 5) Print Receipts
 *
 */
package paystation.domain;

/**
 *
 * @author andrewditty
 */
public interface Display {
    
    /**
     * Prints private String MENU
     */
    public void drawMenu();
    
    /**
     * Confirms that User inputs is valid
     * @param input is a String of user input
     * 
     * @return either the valid selection or -1 to signify an invalid selection
     */
    public int validateUserInput(String input);
    
    
    /**
     * Gets menu selection from user. It forces the user to enter a valid 
     * selection (1-5) or accepts a specified amount of invalid inputs and
     * cancels the transaction
     * 
     * @param maxInvalidInputs is the maximum attempts a user has to make a valid
     * selection
     * @param isTest is a value that should be false unless testing
     * 
     * @return a valid selection, an integer 1-5
     */
    public int selectOption(int maxInvalidInputs, boolean isTest);
    
    /**
     * Prints a formatted receipt
     * 
     * @param r is a receipt object to print
     */
    public void printReceipt(Receipt r);
    
    /**
     * Formats the receipt r into useful strings
     * 
     * @param r is a receipt object to format
     * 
     * @return an array of strings, with three elements 
     * minutes bought e.i. "30" or "44"
     * the current time
     * the time the parking expires
     */
    public String[] calculateTimes(Receipt r);
}
