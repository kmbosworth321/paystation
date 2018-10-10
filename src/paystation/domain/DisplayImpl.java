/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;
import java.util.Scanner;

/**
 *
 * @author andrewditty
 */
public class DisplayImpl implements Display {
    //DisplayMenu
    //ValidateUserInput
    //Print Receipt
    
    // A main() program should be developed to simulate the PayStation operation. 

    //It puts up a menu to allow a customer to select a choice:
    
    private final int MAX_INVALID=10; 
    private final String MENU = "    1. Deposit Coins\n" +
            "    2. Display\n" +
            "    3. Buy Ticket\n" +
            "    4. Cancel\n" +
            "    5. Change Rate Strategy\n";

    
        //  Deposit Coins
        //  Display
        //  Buy Ticket
        //  Cancel
        //  Change Rate Strategy
    
    public void displayImpl(){
        //String[] menu = MENU;
    }     
    
    @Override
    public void drawMenu(){
        //int choice = 0;
        //Scanner scan = new Scanner(System.in);

        //do{
        System.out.print(MENU);
        //choice = scan.nextInt();
        //}while(choice<1 || choice>5);
    }
    
    @Override 
    public int validateUserInput(String input){
        int i = -1;
        try{
            i = Integer.parseInt(input);
        }catch(Exception e){
            System.out.print("Invalid selection. Integers only. Enter 1-5 only\n");
            return -1;
        }
        
        if (i>=1 && i<=5){
            return i;
        }else{
            System.out.print("Invalid selection. Enter 1-5 only\n");
            i = -1;
            
        }
        return i;
    }
        
    @Override
    public int selectOption(){
        int choice;
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Please select an option numerically (1-5)\n");
        String raw = null;
        
        for(int loops=0; loops<MAX_INVALID; loops++){
            try{
                raw = sc.nextLine();
            }catch(Exception e){
                System.out.print("Invalid input/n");
            }finally{
                choice = validateUserInput(raw);
            }
            
            if(choice != -1){
                return choice;
            }
        }
        
        //if this code is reached then user entered too many invalid inputs
        System.out.print(MAX_INVALID + " invalid inputs have been enter successively/n"+
                "This transaction will now cancel");
        return 4;
    }
    
}
