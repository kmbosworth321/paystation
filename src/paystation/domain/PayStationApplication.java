package paystation.domain;


public class PayStationApplication {
    public static void main(String [] args) {
        PayStationImpl ps = new PayStationImpl();
        startApplication(ps);
    }

    private static void startApplication(PayStation ps) {
        // TODO: currently errors out for...reasons. Fix as separate commit.
        boolean acceptingUserInput = true;
        while(acceptingUserInput) {
            ps.display().drawMenu();
            int option = ps.display().selectOption(1, false);
            acceptingUserInput = (option != 4);
        }
        // loop to keep app running and accepting user input
    }
}
