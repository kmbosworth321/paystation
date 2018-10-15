package paystation.domain;

import java.util.Calendar;

import org.junit.Test;
import static org.junit.Assert.*;

public class AlternatingRateStrategyTest {

    /**
     * Test that appropriate strategy is called based on day of week
     */
    @Test
    public void shouldCallCorrectRateStrategy() throws IllegalCoinException {
        RateStrategy weekendStrategy = new LinearRateStrategy();
        RateStrategy weekdayStrategy = new ProgressiveRateStrategy();
        AlternatingRateStrategy ars = new AlternatingRateStrategy(weekdayStrategy, weekendStrategy);
        int[] weekdays = {
                Calendar.MONDAY,
                Calendar.TUESDAY,
                Calendar.WEDNESDAY,
                Calendar.THURSDAY,
                Calendar.FRIDAY
        };
        int[] weekendDays = {
                Calendar.SATURDAY,
                Calendar.SUNDAY
        };
        for(int i : weekdays) {
            assertEquals(weekdayStrategy.calculatePayment(500), ars.calculatePaymentWithDayOfWeek(500, i));
        }
        for(int i : weekendDays) {
            assertEquals(weekendStrategy.calculatePayment(500), ars.calculatePaymentWithDayOfWeek(500, i));
        }
    }
}