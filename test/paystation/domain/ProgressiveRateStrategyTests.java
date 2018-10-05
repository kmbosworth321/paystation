
package paystation.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import static paystation.domain.Strategy.PROGRESSIVE;

import org.junit.Before;

public class ProgressiveRateStrategyTests {
    /**
     * Test Cases for behavior of PayStation Using LinearRateStrategyTests
     */
    PayStationImpl ps;

    /**
     * Tests existence of linear rate strategy in setup
     */
    @Before
    public void setup() {
        ps = new PayStationImpl();
        ps.setRateStrategy(PROGRESSIVE);
    }

    /**
     * Assert that meter starts with no time on it
     */
    @Test
    public void shouldHaveNoTimeAfterNoMoney(){
        assertEquals("Time should be zero until payment", 0, ps.readDisplay());
    }

    /**
     * Entering 5 cents should make the display report 2 minutes parking time.
     */
    @Test
    public void shouldDisplay2MinFor5Cents()
            throws IllegalCoinException {
        ps.addPayment(5);
        assertEquals("Should display 2 min for 5 cents",
                2, ps.readDisplay());
    }

    /**
     * Entering 25 cents should make the display report 10 minutes parking time.
     */
    @Test
    public void shouldDisplay10MinFor25Cents() throws IllegalCoinException {
        ps.addPayment(25);
        assertEquals("Should display 10 min for 25 cents",
                10, ps.readDisplay());
    }

    /**
     * Check that progressive price model begins in second hour.
     *
     * Checks divergence between Alpha and Beta.
     * Uses whole number of minutes to avoid complication with testing whether time is double or int.
     */
    @Test
    public void shouldDisplay63Minfor160Cents() throws IllegalCoinException {
        for (int i = 0; i < 6; i++) {
            ps.addPayment(25);
        }
        ps.addPayment(10);
        assertEquals("Should display 63 minutes for $1.60.", 63, ps.readDisplay());
    }

    /**
     * Check that progressive price model implements third hour pricing.
     *
     * Uses whole numbers.
     */
    @Test
    public void shouldDisplay121MinFor355Cents() throws IllegalCoinException {
        for(int i = 0; i < 14; i++) {
            ps.addPayment(25);
        } // Pay $3.50 for first two hours.
        ps.addPayment(5);
        assertEquals("Should display 121 minutes for $3.55", 121, ps.readDisplay());
    }

}
