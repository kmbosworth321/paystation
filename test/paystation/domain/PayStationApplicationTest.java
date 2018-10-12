/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
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
public class PayStationApplicationTest {
    
    public PayStationApplicationTest() {
    }
    
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;
    
    Display d;
    
    //helper class
    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
    
    //helper class
    private String getOutput() {
        return testOut.toString();
    }


    @Before
    public void setup() {
        d = new DisplayImpl();
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @After
    public void teardown() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }
    /**
     * Test of main method, of class PayStationApplication.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        PayStationApplication.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
