package paystation.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the pay station.
 *
 * Responsibilities:
 *
 * 1) Accept payment;
 * 2) Calculate parking time based on payment;
 * 3) Know earning, parking time bought;
 * 4) Issue receipts;
 * 5) Handle buy and cancel events.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class PayStationImpl implements PayStation {

    private int insertedSoFar;
    private HashMap<Integer, Integer> insertedMap;  /* map recording coin types and quantities */
    private int timeBought;
    private int totalCollected; /* Stores the total amount (in cents) collected by paystation */
    private Display display; /* Handles all screen interaction with paystation */
    private RateStrategy rateStrategy;

    public PayStationImpl() {
        insertedSoFar = 0;
        insertedMap = new HashMap<>();
        timeBought = 0;
        totalCollected = 0;
        display = new DisplayImpl();
        this.rateStrategy = new LinearRateStrategy();//default to avoid errors
    }

    @Override
    public Display display(){
        return display;
    }
    
    @Override
    public String readDisplay() {
        return display.read(timeBought, insertedSoFar);
    }

    @Override
    public void addPayment(int coinValue)
            throws IllegalCoinException {
        switch (coinValue) {
            case 5: break;
            case 10: break;
            case 25: break;
            default:
                throw new IllegalCoinException("Invalid coin: " + coinValue);
        }
        insertedSoFar += coinValue;
        if (insertedMap.containsKey(coinValue))
            insertedMap.put(coinValue, insertedMap.get(coinValue) + 1);
        else
            insertedMap.put(coinValue, 1);
        timeBought = rateStrategy.calculatePayment(insertedSoFar);
    }

    @Override
    public String buy() {
        Receipt r = new ReceiptImpl(timeBought);
        totalCollected += insertedSoFar; /* Each buy action accrues more total money collected */
        reset();
        return r.print();
    }

    @Override
    public Map<Integer, Integer> cancel() {
        HashMap<Integer, Integer> returnedMap = (HashMap<Integer, Integer>) insertedMap.clone();
        reset();
        return returnedMap;
    }

    private void reset() {
        timeBought = insertedSoFar = 0;
        insertedMap.clear();
    }

    @Override
    /* Returns total money collected in the paystation since the last call to empty, and resets total */
    public int empty() {
        int retval = totalCollected;
        totalCollected = 0;
        return retval;
    }

    @Override
    public void setRateStrategy(int selection) {
        switch (selection) {
            case 1:
                this.rateStrategy= new LinearRateStrategy();
                break;
            case 2:
                this.rateStrategy= new ProgressiveRateStrategy();
                break;
            case 3:
                this.rateStrategy= new AlternatingRateStrategy();
                break;
            default://input could be out of range
                System.err.println("Invalid Selection. Rate Strategy not set");
                break;
        }
        timeBought = rateStrategy.calculatePayment(insertedSoFar);
    }
}
