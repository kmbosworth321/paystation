/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;
import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;

public class DisplayImpl implements Display {
    
    private final String MENU = "    1. Deposit Coins\n" +
            "    2. Display\n" +
            "    3. Buy Ticket\n" +
            "    4. Cancel\n" +
            "    5. Change Rate Strategy\n";
    

    public void displayImpl(){
    }     
    
    @Override
    public void drawMenu(){
        System.out.print(MENU);
    }
    
    @Override
    public int validateUserInput(String input){
        input = input.trim();
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
    public int selectOption(int maxInvalidInputs, boolean isTest){
        int choice = -1;
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Please select an option numerically (1-5)\n");
        String raw = null;
        
        for(int l=0; l<maxInvalidInputs; l++){
            try{
                raw = sc.nextLine();
            }catch(Exception e){
                System.out.print("Invalid input ("+l+")\n");
            }finally{
                if(!isTest){
                    choice = validateUserInput(raw);
                }
                if(choice != -1){
                    sc.close();
                    return choice;
                }
            }
        }
        if (!isTest){
            //if this code is reached then user entered too many invalid inputs
            System.out.print(maxInvalidInputs + " invalid inputs have been enter successively\n"+
                    "This transaction will now cancel");
        }
        sc.close();
        return 4;
    }
    
    @Override
    public void printReceipt(Receipt r){
        String[] times = calculateTimes(r);
        
        String mins = times[0];
        String now = times[1];
        String exp = times[2];
        
        System.out.print("Ticket purchased on " + now + " for "+mins+" minutes\n");
        System.out.print("Parking expires at " + exp);
    }
    
    @Override
    public String[] calculateTimes(Receipt r){
        Calendar cal = Calendar.getInstance();
        
        String mins = Integer.toString(r.value());
        int millis = r.value() * 60 * 1000;
        
        Date now = cal.getTime();
        Date exp = new Date(now.getTime() + millis);
        
        String[] retval = {mins, now.toString(), exp.toString()};
        return retval;
    }
}
