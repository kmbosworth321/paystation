/**
 * Implementation of Receipt.
 *
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

import java.util.Calendar;
import java.util.Date;

public class ReceiptImpl implements Receipt {

    private int value;

    public ReceiptImpl(int value) {
        this.value = value;
    }

    @Override
    public int value() {
        return value;
    }
    
    @Override
    public String print(){
        String[] times = format();
        
        String mins = times[0];
        String now = times[1];
        String exp = times[2];
        
        return("Ticket purchased on " + now + " for "+mins+" minutes\n"
        +"Parking expires at " + exp);
    }
    
    @Override
    public String[] format(){
        Calendar cal = Calendar.getInstance();
        
        String mins = Integer.toString(value());
        int millis = value() * 60 * 1000;
        
        Date now = cal.getTime();
        Date exp = new Date(now.getTime() + millis);
        
        String[] retval = {mins, now.toString(), exp.toString()};
        return retval;
    }
}
