/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;
import java.util.Scanner;

public class DisplayImpl implements Display {
    
    private final String MAIN_MENU =
        "Please select an option numerically (1-5)\n" +
        "1. Deposit Coins\n" +
        "2. Display\n" +
        "3. Buy Ticket\n" +
        "4. Cancel\n" +
        "5. Change Rate Strategy\n";

    private final String COIN_PROMPT =
        "Please enter the coin that you will deposit (5, 10, 25): ";

    private final String STRAT_MENU =
        "Please select a new rate strategy:\n" +
        "1. Linear\n" +
        "2. Progressive\n" +
        "3. Alternating";

    private final int BADRETURNVALUE = -1;

    private Scanner sc;

    public DisplayImpl(Scanner sc) {
        this.sc = sc;
    }
    
    @Override
    public void drawMainMenu(){
        System.out.println(MAIN_MENU);
    }

    @Override
    public void drawCoinPrompt() {
        System.out.println(COIN_PROMPT);
    }

    @Override
    public void drawStrategyMenu(){
        System.out.println(STRAT_MENU);
    }

    @Override
    public int validateUserInput(){
        int i;
        try{
            i = sc.nextInt();
        }catch(Exception e){
            sc.nextLine(); // flush bad value
            return BADRETURNVALUE;
        }
        return i;
    }
    
        
    @Override
    public int selectOption(int start, int end) {
        int choice = validateUserInput();
        if (choice >= start && choice <= end){
            return choice;
        } else {
            System.out.println("Invalid selection. " + start + "-" + end + " is the valid range for this menu.\n");
            return BADRETURNVALUE;
        }
    }
    
    @Override
    public int getInput() {
//        int selection = sc.nextInt();
//        return validateUserInput(selection);
        return 1;
    }
    
    @Override
    public String read(int timeBought, int insertedSoFar){
        StringBuilder retVal = new StringBuilder();
        retVal.append("\n\n");
        retVal.append(Integer.toString(insertedSoFar));
        retVal.append(" cents buys ");
        retVal.append(Integer.toString(timeBought));
        retVal.append(" minutes\n\n");
        return retVal.toString();
    }
}
