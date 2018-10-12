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
            int option = ps.display().selectOption(10, false);
            
            //System.out.println(Integer.toString(option));
            if(option == 1){
                System.out.println("Calling method 'Deposit Coins'");
            }else if(option == 2){
                System.out.println("Calling method 'Display'");
            }else if(option == 3){
                System.out.println("Calling method 'Buy Ticket'");
            }else if(option == 4){
                System.out.println("Calling method 'Cancel'");
            }else if(option == 5){
                System.out.println("Calling method 'Change Rate Strategy'");
            }
            
            acceptingUserInput = (option != 4);
        }
        // loop to keep app running and accepting user input
    }
}
