package paystation.domain;

public class ProgressiveRateStrategy implements RateStrategy {

    @Override
    public int calculatePayment(int inserted) {

        if(inserted <= 150) {
            return 2 * inserted / 5;
        } else if (inserted <= 350) {
            int time = 60;
            time += 1.5 * (inserted - 150) / 5;
            return time;
        } else {
            int time = 120;
            time += (inserted - 350) / 5;
            return time;
        }
    }
}
