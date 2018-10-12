package paystation.domain;


public class PayStationApplication {
    public static void main(String [] args) {
        PayStationImpl ps = new PayStationImpl();
        startApplication(ps);
    }

    private static void startApplication(PayStation ps) {
        // TODO: currently errors out for...reasons. Fix as separate commit.
        boolean acceptingUserInput = true;
        int maxInvalidInputs = 10;
        int invalidInputs = 0;
        int maxLoops = 100;
        
        OUTER:
        for (int t = 0; t<maxLoops; t++) {
            System.out.println(ps.display().drawMenu());
            System.out.print("Please select an option numerically (1-5)\n");
            int option = ps.display().selectOption();
            
            switch (option) {
                case 1:
                    System.out.print("Please insert a coin (5, 10, 25)\n");
                    int coinValue = ps.display().getInput();
                    //System.out.println(Integer.toString(coinValue));//debugged
                    try{
                        ps.addPayment(coinValue);
                    }catch(IllegalCoinException e){
                        System.err.println(e);
                    }
                    invalidInputs = 0;
                    break;
                case 2:
                    System.out.println(ps.readDisplay());
                    invalidInputs = 0;
                    break;
                case 3:
                    System.out.println("Calling method 'Buy Ticket'");
                    invalidInputs = 0;
                    break;
                case 4:
                    System.out.println("Calling method 'Cancel'");
                    ps.cancel();
                    break OUTER;
                case 5:
                    System.out.println("Calling method 'Change Rate Strategy'");
                    invalidInputs = 0;
                    break;
                default:
                    invalidInputs++;
                    if (invalidInputs >= maxInvalidInputs) {
                        System.err.println(maxInvalidInputs+" invalid inputs entered");
                        ps.cancel();
                        break OUTER;
                    }
                    break;
            }
            System.err.flush();
        }
    }
}
