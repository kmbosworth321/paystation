package paystation.domain;


public class PayStationApplication {
    public static void main(String [] args) {
        PayStationImpl ps = new PayStationImpl();
        startApplication(ps);
    }

    private static void startApplication(PayStation ps) {
        // loop to keep app running and accepting user input
    }
}
