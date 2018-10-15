package paystation.domain;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Scanner;
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
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    public Display setupDisplayWithInput(String input) {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(input.trim().getBytes()));
        return setupDisplay();
    }

    public Display setupDisplay() {
        Scanner scanner = new Scanner(System.in);
        return new DisplayImpl(scanner);
    }

     /**
     * The Display should print out the menu, when drawMenu() is called
     */
    @Test
    public void shouldDrawMenu() throws IllegalCoinException {
        this.d = setupDisplay();
        
        String menuMessage =
            "Please select an option numerically (1-5)\n" +
            "1. Deposit Coins\n" +
            "2. Display\n" +
            "3. Buy Ticket\n" +
            "4. Cancel\n" +
            "5. Change Rate Strategy\n\n";
        
        d.drawMainMenu();
        assertEquals(menuMessage, outContent.toString());
    }
    
     /**
     * Verify that non integer input is rejected
     */
    @Test
    public void shouldRejectInvalidTextInput() throws IllegalCoinException {
        this.d = setupDisplayWithInput("Junk text input");
        int test = d.validateUserInput();
        assertEquals(-1, test);
    }
    
    /**
     * Verify that out of range input is rejected
     */
    @Test 
    public void shouldRejectOutOfRangeInput() throws IllegalCoinException {
        this.d = setupDisplayWithInput("10");
        int choice = d.selectOption(1, 3);
        assertEquals("Should return -1", -1, choice);
    }
    
    /**
     * Verify that valid input is accepted
     */
    @Test 
    public void shouldAcceptValidInput() throws IllegalCoinException {
        this.d = setupDisplayWithInput("2");
        int choice = d.selectOption(1, 3);
        assertEquals("Should return 2", 2, choice);
    }
    
    /**
     * Verify that user is prompted
     */
    @Test 
    public void shouldDrawStratMenu() throws IllegalCoinException {
        this.d = setupDisplay();

        String menuMessage =
            "Please select a new rate strategy:\n" +
            "1. Linear\n" +
            "2. Progressive\n" +
            "3. Alternating\n";

        d.drawStrategyMenu();
        assertEquals(menuMessage, outContent.toString());
    }

    /**
     * Verify that user is prompted
     */
    @Test
    public void shouldDrawCoinPrompt() throws IllegalCoinException {
        this.d = setupDisplay();

        String menuMessage =
            "Please enter the coin that you will deposit (5, 10, 25): \n";

        d.drawCoinPrompt();
        assertEquals(menuMessage, outContent.toString());
    }

}
