
package paystation.domain;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;

public class LinearRateStrategyTests {
    /**
     * Test Cases for behavior of PayStation Using LinearRateStrategyTests
     */
    PayStationImpl ps;
    RateStrategy strategy;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    /**
     * Tests existence of linear rate strategy in setup
     */
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        ps = new PayStationImpl();
        strategy = new LinearRateStrategy();
        ps.setRateStrategy(strategy);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    /**
     * Assert that meter starts with no time on it
     */
    @Test
    public void shouldHaveNoTimeAfterNoMoney(){
        ps.readDisplay();
        assertEquals("\n\n0 cents buys 0 minutes\n\n\n", outContent.toString());
    }

    /**
     * Entering 5 cents should make the display report 2 minutes parking time.
     */
    @Test
    public void shouldDisplay2MinFor5Cents()
            throws IllegalCoinException {
        ps.addPayment(5);
        ps.readDisplay();
        assertEquals("\n\n5 cents buys 2 minutes\n\n\n", outContent.toString());
    }

    /**
     * Entering 25 cents should make the display report 10 minutes parking time.
     */
    @Test
    public void shouldDisplay10MinFor25Cents() throws IllegalCoinException {
        ps.addPayment(25);
        ps.readDisplay();
        assertEquals("\n\n25 cents buys 10 minutes\n\n\n", outContent.toString());
    }

    /**
     * Check that linear price model continued into second hour.
     *
     * Checks divergence between Alpha and Beta.
     * Uses whole number of minutes to avoid complication with testing whether time is double or int.
     */
    @Test
    public void shouldDisplay70Minfor175Cents() throws IllegalCoinException {
        for (int i = 0; i < 7; i++) {
            ps.addPayment(25);
        }
        ps.readDisplay();
        assertEquals("\n\n175 cents buys 70 minutes\n\n\n", outContent.toString());
    }

    /**
     * Check that linear price model continues into third hour.
     */
    @Test
    public void shouldDisplay200MinFor500Cents() throws IllegalCoinException {
        for(int i = 0; i < 20; i++) {
            ps.addPayment(25);
        }
        ps.readDisplay();
        assertEquals("\n\n500 cents buys 200 minutes\n\n\n", outContent.toString());
    }

}
