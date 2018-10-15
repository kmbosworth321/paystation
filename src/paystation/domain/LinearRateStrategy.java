package paystation.domain;

public class LinearRateStrategy implements RateStrategy {

    @Override
    public int calculatePayment(int inserted) {

        return 2 * inserted / 5;

    }
}
