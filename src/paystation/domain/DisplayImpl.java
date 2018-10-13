/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;

public class DisplayImpl implements Display {
    
    private final String MENU = "    1. Deposit Coins\n" +
            "    2. Display\n" +
            "    3. Buy Ticket\n" +
            "    4. Cancel\n" +
            "    5. Change Rate Strategy\n";
    
    private final int BADRETURNVALUE = 0;

    public void displayImpl(){
    }     
    
    @Override
    public String drawMenu(){
        return MENU;
    }
    
    @Override
    public int validateUserInput(String input){
        input = input.trim();
        int i;
        try{
            i = Integer.parseInt(input);
        }catch(NumberFormatException e){
            System.err.print("Invalid selection. Integers only.\n");
            System.err.flush();
            return BADRETURNVALUE;
        }catch(Exception e){
            System.err.print("Invalid selection!\n");
            System.err.flush();
            return BADRETURNVALUE;
        }
        return i;
    }
    
        
    @Override
    public int selectOption(){
        int choice = getInput();
        if (choice>=1 && choice<=5){
            return choice;
        }else{
            System.err.print("Invalid selection. Enter 1-5 only\n");
            System.err.flush();
            return BADRETURNVALUE;
        }
    }
    
    @Override
    public int getInput(){
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        String raw = "";
        int cleanInput = BADRETURNVALUE;
        
        try{
            //raw = sc.readLine();
            raw = sc.readLine();
        }catch(NullPointerException e){
            System.err.print("No input\n");
            System.err.flush();
            return BADRETURNVALUE;
        }catch(Exception e){
            System.err.print("Invalid input ("+raw+")\n");
            System.err.flush();
            return BADRETURNVALUE;
        }
        cleanInput = validateUserInput(raw);
        //if(cleanInput is Integer)
        return cleanInput;
    }
    
    @Override
    public String read(int timeBought, int insertedSoFar){
        StringBuilder retVal = new StringBuilder();
        retVal.append(Integer.toString(insertedSoFar));
        retVal.append(" cents buys ");
        retVal.append(Integer.toString(timeBought));
        retVal.append(" minutes");
        return retVal.toString();
    }  
}
