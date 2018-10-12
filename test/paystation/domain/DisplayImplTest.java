package paystation.domain;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Before;
import org.junit.Test;

/*
 * Test Cases for Display
 */

public class DisplayImplTest {
    
    Display d;
    
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    //helper class
    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
    
    //helper class
    private String getOutput() {
        return testOut.toString();
    }


    @Before
    public void setup() {
        d = new DisplayImpl();
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @After
    public void teardown() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }
    
     /**
     * The Display should print out the menu, when drawMenu() is called
     */
    @Test
    public void shouldDrawMenu()
            throws IllegalCoinException {
        
        String menuMessage = "    1. Deposit Coins\n" +
            "    2. Display\n" +
            "    3. Buy Ticket\n" +
            "    4. Cancel\n" +
            "    5. Change Rate Strategy\n";
        
        d.drawMenu();
        assertEquals("Should display the menu string",menuMessage, getOutput());
    }
    
     /**
     * Verify that non integer input is rejected
     */
    @Test
    public void shouldRejectInvalidTextInput()
            throws IllegalCoinException {
        int test = d.validateUserInput("text");
        assertEquals("Should display error", "Invalid selection. Integers only. Enter 1-5 only\n", getOutput());
        assertEquals("Should return -1", -1, test);
    }
    
    /**
     * Verify that out of range input is rejected
     */
    @Test 
    public void shouldRejectOutOfRangeInput()
            throws IllegalCoinException {
        //int test = d.validateUserInput("0");
        //assertEquals("Should display error", "Invalid selection. Enter 1-5 only\n", getOutput());
        
        int test = d.validateUserInput("7");
        assertEquals("Should display error", "Invalid selection. Enter 1-5 only\n", getOutput());
        
        assertEquals("Should return -1", -1, test);
    }
    
    /**
     * Verify that valid input is accepted
     */
    @Test 
    public void shouldAcceptValidInput()
            throws IllegalCoinException {
        String in = null;
        int res;
        
        for(int i = 1; i<=5; i++){
            in = Integer.toString(i);
            res = d.validateUserInput(in);
            
            assertEquals("Should return " + in, i, res);
        }
        
        res = d.validateUserInput("   3  ");
        assertEquals("Should return 3", 3, res);
    }
    
    /**
     * Verify that user is prompted
     */
    @Test 
    public void shouldPromptUser()
            throws IllegalCoinException {
        d.selectOption(0,true);
        //provideInput("1");
        assertEquals("Should prompt user", "Please select an option numerically (1-5)\n", 
                getOutput());
    }
    
    /**
     * Verify that the program will loop with invalid inputs and return 4 if 
     * max loops are exceeded
     */
    @Test 
    public void shouldLoop()
            throws IllegalCoinException {
        int res = d.selectOption(3,true);
        //provideInput("1");
        assertEquals("Should loop", "Please select an option numerically (1-5)\n"+
                "Invalid input (0)\n"+"Invalid input (1)\n"+"Invalid input (2)\n", 
                getOutput());
        assertEquals("Should return 4", 4, res);
    }
    
    
    /**
     * If a receipt with 30 minutes is entered then the amount of minutes should
     * be printed as well as the year
     */
    @Test
    public void shouldReturnTimes()
        throws IllegalCoinException{
        
        int value = 30;
        Receipt test = new ReceiptImpl(value);
        String[] res = d.calculateTimes(test);
        
        String mins = res[0];
        String now = res[1];
        String exp = res[2];
        
        Calendar cal = Calendar.getInstance();
        int today = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        
        assertEquals("Should return correct minutes ", mins, Integer.toString(value));
        assertThat("Now should contain ", now, containsString(Integer.toString(year)));
        assertThat("exp should contain ", exp, containsString(Integer.toString(year)));
    }
    
    /**
     * If a receipt is printed it should contain certain Strings
     */
    @Test
    public void shouldPrintReceipt()
            throws IllegalCoinException {
        int value = 30;
        Receipt test = new ReceiptImpl(value);
        d.printReceipt(test);
        //assertEquals("Should print the recipt", value+" minutes", getOutput());
        assertThat("Should return string", getOutput(), containsString("Ticket purchased on "));
    }
}
