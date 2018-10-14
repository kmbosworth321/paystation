package paystation.domain;

public interface RateStrategy {

    int calculatePayment(int inserted);
}
