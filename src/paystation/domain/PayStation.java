/**
 * The business logic of a Parking Pay Station.
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
package paystation.domain;

import java.util.Map;

public interface PayStation {

    /**
     * Useful for accessing methods within Display
     * @return the display() object
     */
    public Display display();

    /**
     * Insert coin into the pay station and adjust state accordingly.
     *
     * @param coinValue is an integer value representing the coin in cent. That
     * is, a quarter is coinValue=25, etc.
     * @throws IllegalCoinException in case coinValue is not a valid coin value
     */
    void addPayment(int coinValue) throws IllegalCoinException;

    /**
     * Read the machine's display. The display shows a numerical description of
     * the amount of parking time accumulated so far based on inserted payment.
     *
     * @return the String with balance so far and timeBought to display on the 
     * pay station display
     */
    void readDisplay();

    /**
     * Buy parking time. Terminate the ongoing transaction and return a parking
     * receipt. A non-null object is always returned.
     *
     * @return a valid parking receipt object.
     */
    String buy();

    /**
     * Cancel the present transaction. Resets the paystation for a new transaction.
     * @return A Map defining the coins returned to the user.
     * The key is the coin type and the associated value is the number of these coins that are
     * returned.
     * The Map object is never null even if no coins are returned.
     * The Map will only contain only keys for coins to be returned.
     * The Map will be cleared after a cancel or buy.
     */
    Map<Integer, Integer> cancel();

    /**
     * Return the total amount of money collected since the last call and empties
     * it, setting the total to zero. Note: Money only collected after call to Buy
     */
    int empty();

    /**
     * Set the rateStrategy, which calculates the timeBought from insertedSoFar
     * 
     * @param takes an integer that signifies which rate strategy to use
     * 1 for Linear (Default)
     * 2 for Progressive 
     * 3 for Alternating
     */
    void setRateStrategy(RateStrategy rateStrategy);

}
