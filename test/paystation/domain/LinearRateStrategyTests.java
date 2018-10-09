
package paystation.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import static paystation.domain.Strategy.LINEAR;

import org.junit.Before;

public class LinearRateStrategyTests {
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
        ps.setRateStrategy(LINEAR);
    }

//    @Test
//    public void existsClassLinearRateStrategy() {
//        rs = new LinearRateStrategy();
//    }
    // Don't use @Test -- test in setup

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
        assertEquals("Should display 70 minutes for $1.75.", 70, ps.readDisplay());
    }

    /**
     * Check that linear price model continues into third hour.
     */
    @Test
    public void shouldDisplay130MinFor310Cents() throws IllegalCoinException {
        for(int i = 0; i < 13; i++) {
            ps.addPayment(25);
        }
        assertEquals("Should display 130 minutes for $3.25", 130, ps.readDisplay());
    }

}
