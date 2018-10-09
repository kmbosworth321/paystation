/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;
import java.util.Arrays;
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
    public int validateUserInput(){
        int choice = 0;
        Scanner scan = new Scanner(System.in);

        do{
            System.out.print(MENU);
            choice = scan.nextInt();
        }while(choice<1 || choice>5);
    }
    
}
