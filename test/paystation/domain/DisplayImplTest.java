package paystation.domain;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
//import paystation.domain.IllegalCoinException;
//import static paystation.domain.Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andrewditty
 */
public class DisplayImplTest {
     /**
     * Entering 5 cents should make the display report 2 minutes parking time.
     * @throws paystation.domain.IllegalCoinException
     */
    
    //PayStation ps;
    Display d;
    
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;


    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }


    @Before
    public void setup() {
        //ps = new PayStationImpl();
        d = new DisplayImpl();
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @After
    public void teardown() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }
    
    
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
     * 
     * @throws paystation.domain.IllegalCoinException
     */
    @Test
    public void shouldRejectInvalidTextInput()
            throws IllegalCoinException {
        int test = d.validateUserInput("text");
        assertEquals("Should display error", "Invalid selection. Integers only. Enter 1-5 only\n", getOutput());
        assertEquals("Should return -1", -1, test);
    }
    
    @Test 
    public void shouldRejectOutOfRangeInput()
            throws IllegalCoinException {
        //int test = d.validateUserInput("0");
        //assertEquals("Should display error", "Invalid selection. Enter 1-5 only\n", getOutput());
        
        int test = d.validateUserInput("7");
        assertEquals("Should display error", "Invalid selection. Enter 1-5 only\n", getOutput());
        
        assertEquals("Should return -1", -1, test);
    }
    
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
    }
    
    @Test 
    public void shouldPromptUser()
            throws IllegalCoinException {
        d.selectOption(0,true);
        //provideInput("1");
        assertEquals("Should prompt user", "Please select an option numerically (1-5)\n", 
                getOutput());
    }
    
    @Test 
    public void shouldLoop()
            throws IllegalCoinException {
        d.selectOption(2,true);
        //provideInput("1");
        assertEquals("Should loop", "Please select an option numerically (1-5)\n"+
                "Invalid input (0)\n"+"Invalid input (1)\n", getOutput());
    }
}
