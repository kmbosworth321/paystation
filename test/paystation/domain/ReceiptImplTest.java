/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

import java.util.Calendar;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andrewditty
 */
public class ReceiptImplTest {
    
    public ReceiptImplTest() {
    }
    
    //PayStation ps;
    //Display d;
    Receipt r;

    @Before
    public void setup() {
        //ps = new PayStationImpl();
        //d = new DisplayImpl();
        r = new ReceiptImpl(30);
    }


    /**
     * If a receipt with 30 minutes is entered then the amount of minutes should
     * be printed as well as the year
     */
    @Test
    public void shouldReturnTimes()
        throws IllegalCoinException{
        
        int value = 30;
        Receipt test = new ReceiptImpl(value);
        String[] res = r.format();
        
        String mins = res[0];
        String now = res[1];
        String exp = res[2];
        
        Calendar cal = Calendar.getInstance();
        int today = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        
        assertEquals("Should return correct minutes ", mins, Integer.toString(value));
        assertThat("Now should contain ", now, containsString(Integer.toString(year)));
        assertThat("exp should contain ", exp, containsString(Integer.toString(year)));
    }
    
    /**
     * If a receipt is printed it should contain certain Strings
     */
    @Test
    public void shouldPrintReceipt()
        throws IllegalCoinException {
        int value = 30;
        Receipt test = new ReceiptImpl(value);
        String receipt = r.print();
        //assertEquals("Should print the recipt", value+" minutes", getOutput());
        assertThat("Should return string", receipt, containsString("Ticket purchased on "));
    }
}
