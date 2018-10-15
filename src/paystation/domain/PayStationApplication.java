package paystation.domain;


public class PayStationApplication {
    private static final int MAX_INVALID_INPUTS = 10;
    private static final int MAX_LOOPS = 100;

    public static void main(String [] args) {
        PayStationImpl ps = new PayStationImpl();
        startApplication(ps);
    }

    private static void startApplication(PayStation ps) {
        boolean acceptingUserInput = true;
        int invalidInputs = 0;
        int loops = 0;

        while(acceptingUserInput) {
            loops++;
            acceptingUserInput = loops < MAX_LOOPS;
            ps.display().drawMainMenu();
            int option = ps.display().selectOption(1, 5);

            switch (option) {
                case 1:
                    ps.display().drawCoinPrompt();
                    try{
                        int coinValue = ps.display().validateUserInput();
                        if(coinValue != -1) {
                            ps.addPayment(coinValue);
                            System.out.println(coinValue + " cents successfully entered.\n");
                        } else {
                            System.out.println("Invalid coin amount entered.\n");
                        }
                    } catch(IllegalCoinException e){
                        System.out.println("Invalid coin entered.\n");
                    }
                    invalidInputs = 0;
                    break;
                case 2:
                    ps.readDisplay();
                    invalidInputs = 0;
                    break;
                case 3:
                    String printedReceipt = ps.buy();
                    if(printedReceipt == null) {
                        System.out.println("Cannot complete transaction. No coins entered.");
                    } else {
                        System.out.println("\n\n\n\n\n\n\n" + printedReceipt +
                                "\n\nThank you!\nGoodbye\n");
                    }
                    break;
                case 4:
                    System.out.println("Good Day");
                    System.out.println("Coins Returned: " + ps.cancel().toString() + "\n");
                    break;
                case 5:
                    ps.display().drawStrategyMenu();
                    int newStrategy = ps.display().selectOption(1, 3);
                    switch (newStrategy) {
                        case 1:
                            ps.setRateStrategy(new LinearRateStrategy());
                            break;
                        case 2:
                            ps.setRateStrategy(new ProgressiveRateStrategy());
                            break;
                        case 3:
                            RateStrategy weekendStrategy = new LinearRateStrategy();
                            RateStrategy weekdayStrategy = new ProgressiveRateStrategy();
                            ps.setRateStrategy(new AlternatingRateStrategy(weekdayStrategy, weekendStrategy));
                            break;
                        default://input could be out of range
                            System.out.println("Invalid Selection. Rate Strategy not set\n");
                            break;
                    }
                    invalidInputs = 0;
                    break;
                default:
                    invalidInputs++;
                    if (invalidInputs >= MAX_INVALID_INPUTS) {
                        acceptingUserInput = false;
                        System.err.println(MAX_INVALID_INPUTS + " invalid inputs entered\n");
                        ps.cancel();
                        break;
                    }
                    break;
            }
        }
    }
}
