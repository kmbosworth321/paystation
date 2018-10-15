/**
 * Testcases for the Pay Station system.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
package paystation.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.util.HashMap;

public class PayStationImplTest {

    PayStationImpl ps;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    /**
     * Tests existence of linear rate strategy in setup
     */
    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        ps = new PayStationImpl();
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    /**
     * Rewrote readDisplay, so this is broken
     * Entering 5 cents should make the display report 2 minutes parking time.
     */
    @Test
    public void shouldDisplay2MinFor5Cents() throws IllegalCoinException {
        ps.addPayment(5);
        ps.readDisplay();
        assertEquals("Should display 2 min for 5 cents", "\n\n5 cents buys 2 minutes\n\n\n", outContent.toString());
    }

    /**
     * Rewrote readDisplay, so this is broken
     * Entering 25 cents should make the display report 10 minutes parking time.
     */
    @Test
    public void shouldDisplay10MinFor25Cents() throws IllegalCoinException {
        ps.addPayment(25);
        ps.readDisplay();
        assertEquals("Should display 10 min for 25 cents", "\n\n25 cents buys 10 minutes\n\n\n", outContent.toString());
    }

    /**
     * Verify that illegal coin values are rejected.
     */
    @Test(expected = IllegalCoinException.class)
    public void shouldRejectIllegalCoin() throws IllegalCoinException {
        ps.addPayment(17);
    }

    /**
     * Rewrote readDisplay, so this is broken
     * Entering 10 and 25 cents should be valid and return 14 minutes parking
     */
    @Test
    public void shouldDisplay14MinFor10And25Cents() throws IllegalCoinException {
        ps.addPayment(10);
        ps.addPayment(25);
        ps.readDisplay();
        assertEquals("Should display 15 min for 10+25 cents", "\n\n35 cents buys 14 minutes\n\n\n", outContent.toString());
    }

    /**
     * Buy should return a valid receipt of the proper amount of parking time
     */
    @Test
    public void shouldPrintCorrectReceiptWhenBuy() throws IllegalCoinException {
        ps.addPayment(5);
        ps.addPayment(10);
        ps.addPayment(25);
        String printedReceipt = ps.buy();
        assertEquals("Receipt value must be 16 min.", true , printedReceipt.contains("16 minutes"));
    }

    /**
     * Buy for 100 cents and verify the receipt
     */
    @Test
    public void shouldReturnReceiptWhenBuy100c() throws IllegalCoinException {
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(25);
        ps.addPayment(25);
        String printedReceipt = ps.buy();
        assertNotNull("Should return printed receipt", printedReceipt);
    }

    /**
     * Rewrote readDisplay, so this is broken
     * Verify that the pay station is cleared after a buy scenario
     */
    @Test
    public void shouldClearAfterBuy() throws IllegalCoinException {
        ps.addPayment(25);
        ps.buy(); // I do not care about the result
        ps.readDisplay();
        assertEquals("\n\n0 cents buys 0 minutes\n\n\n", outContent.toString());
    }

    /**
     * Rewrote readDisplay, so this is broken
     * Verify that cancel clears the pay station
     */
    @Test
    public void shouldClearAfterCancel() throws IllegalCoinException {
        ps.addPayment(10);
        ps.cancel();
        ps.readDisplay();
        assertEquals("\n\n0 cents buys 0 minutes\n\n\n", outContent.toString());
    }

    /**
     * Call to cancel should clear the map.
     */
    @Test
    public void shouldClearMapAfterCancel() throws IllegalCoinException {
        ps.addPayment(10);
        ps.cancel();
        HashMap<Integer, Integer> emptyMap = (HashMap<Integer, Integer>) ps.cancel();
        assertTrue("Cancel should clear map", emptyMap.isEmpty());
    }

    /**
     * Call to cancel should return a map containing one coin entered.
     */
    @Test
    public void cancelShouldReturnMapContainingOneCoin() throws IllegalCoinException {
        ps.addPayment(25);
        HashMap<Integer, Integer> returnedMap = (HashMap<Integer, Integer>) ps.cancel();
        assertEquals("Cancel should return a map containing one coin entered",
                1, returnedMap.size());
        assertEquals("Cancel should return a map containing one coin entered",
                1, (int) returnedMap.get(25));
    }

    /**
     * Call to cancel should return a map containing a mixture of coins entered.
     */
    @Test
    public void cancelShouldReturnMapContainingMixtureOfCoins() throws IllegalCoinException {
        ps.addPayment(25);
        ps.addPayment(5);
        ps.addPayment(10);
        ps.addPayment(5);
        HashMap<Integer, Integer> returnedMap = (HashMap<Integer, Integer>) ps.cancel();
        assertEquals("Cancel should return a map containing a mixture of coins entered",
                3, returnedMap.size());
        assertEquals("Cancel should return a map containing a mixture of coins entered",
                1, (int) returnedMap.get(25));
        assertEquals("Cancel should return a map containing a mixture of coins entered",
                1, (int) returnedMap.get(10));
        assertEquals("Cancel should return a map containing a mixture of coins entered",
                2, (int) returnedMap.get(5));
    }

    /**
     * Call to cancel should return a map that does not contain a key for a coin not entered.
     */
    @Test
    public void cancelShouldReturnMapNotContainingKeyNotEntered() throws IllegalCoinException {
        assertFalse("Returned map should not create key for a coin not entered",
                ps.cancel().containsKey(25));
        ps.addPayment(25);
        assertFalse("Returned map should not create key for a coin not entered",
                ps.cancel().containsKey(5));
    }

    /**
     * Call to empty returns the total amount entered.
     */
    @Test
    public void emptyReturnsTotalEntered() throws IllegalCoinException {
        ps.addPayment(10);
        ps.buy();
        assertEquals("Empty should show total amount entered",
                10, ps.empty());
    }

    /**
     * Call to empty returns the total amount entered.
     */
    @Test
    public void cancelDoesNotAddFromEmpty() throws IllegalCoinException {
        ps.addPayment(25);
        ps.buy();
        ps.addPayment(25);
        ps.cancel();
        assertEquals("Empty should not include cancelled amount entered",
                25, ps.empty());
    }

    /**
     * Call to empty resets totalCollected to 0
     */
    @Test
    public void emptyResetsTotalCollected() throws IllegalCoinException {
        ps.addPayment(25);
        ps.buy();
        ps.addPayment(25);
        ps.buy();
        assertEquals("Empty should return total collected.",
                50, ps.empty());
        assertEquals("Previous empty should have reset total to zero.",
                0, ps.empty());
    }

    /**
     * Call to buy should clear the map.
     */
    @Test
    public void shouldClearMapAfterBuy() throws IllegalCoinException {
        ps.addPayment(25);
        ps.buy();
        HashMap<Integer, Integer> emptyMap = (HashMap<Integer, Integer>) ps.cancel();
        assertTrue("Buy should clear map.", emptyMap.isEmpty());
    }
    
 
}
