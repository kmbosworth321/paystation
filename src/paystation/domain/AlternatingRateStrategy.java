package paystation.domain;

import java.util.Calendar;

public class AlternatingRateStrategy implements RateStrategy {
    private RateStrategy weekdayStrategy;
    private RateStrategy weekendStrategy;

    public AlternatingRateStrategy(RateStrategy weekdayStrategy, RateStrategy weekendStrategy) {
        this.weekdayStrategy = weekdayStrategy;
        this.weekendStrategy  = weekendStrategy;
    }

    @Override
    public int calculatePayment(int inserted) {
        int currentDay = Calendar.DAY_OF_WEEK;
        return calculatePaymentWithDayOfWeek(inserted, currentDay);
    }

    protected int calculatePaymentWithDayOfWeek(int inserted, int dayOfWeek) {
        boolean isWeekday = ((dayOfWeek >= Calendar.MONDAY) && (dayOfWeek <= Calendar.FRIDAY));
        int retval = 0;

        if (isWeekday){
            retval = weekdayStrategy.calculatePayment(inserted);
        } else{
            retval = weekendStrategy.calculatePayment(inserted);
        }
        return retval;
    }
}
