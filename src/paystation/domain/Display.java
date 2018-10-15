/*
 * The Display of a Parking Pay Station.
 *
 * Responsibilities:
 *
 * 1) Print Menu
 * 2) Take user Input
 * 3) Validate user unput
 * 4) Format Receipts for visibility
 * 5) Print Receipts
 *
 */
package paystation.domain;

/**
 *
 * @author andrewditty
 */
public interface Display {
    
    /**
     * Prints private String MAIN_MENU
     */
    void drawMainMenu();

    /**
     * Prints private String MAIN_MENU
     */
    void drawCoinPrompt();
    
    /**
     * Confirms that User inputs is valid
     * 
     * @return either the valid selection or BADRETURNVALUE to signify an invalid selection
     */
    int validateUserInput();
    
    
    /**
     * Gets menu selection from user. It forces the user to enter a valid 
     * selection in a range provided via start-end params.
     * 
     * @return a valid selection, an integer between start and end
     */
    int selectOption(int start, int end);
    
    /**
     * Read the machine's display. The display shows a numerical description of
     * the amount of parking time accumulated so far based on inserted payment.
     *
     * @return the String with balance so far and timeBought to display on the 
     * pay station display
     */
    String read(int timeBought, int insertedSoFar);
    
    /**
     * TODO
     */
    int getInput();
    
    /**
     * TODO
     */
    void drawStrategyMenu();
}
