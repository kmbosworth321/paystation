package paystation.domain;

public class AlternatingRateStrategy implements RateStrategy {
    private RateStrategy weekdayStrategy;
    private RateStrategy weekendStrategy;

    public AlternatingRateStrategy(RateStrategy weekdayStrategy, RateStrategy weekendStrategy) {
        this.weekdayStrategy = weekdayStrategy;
        this.weekendStrategy = weekendStrategy;
    }


    @Override
    public int calculatePayment(int inserted) {
        return 0;
    }
}
