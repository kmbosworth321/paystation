package paystation.domain;

import java.util.Calendar;
import java.util.HashMap;

public class AlternatingRateStrategy implements RateStrategy {
    private RateStrategy weekdayStrategy;
    private RateStrategy weekendStrategy;

    public AlternatingRateStrategy(RateStrategy weekdayStrategy, RateStrategy weekendStrategy) {
        this.weekdayStrategy = weekdayStrategy;
        this.weekendStrategy = weekendStrategy;
    }


    @Override
    public int calculatePayment(int inserted) {
        //find out what day it is
        
        int strategy;
        //Get day
        int day = Calendar.DAY_OF_WEEK;
 
        //check what strategy is employed on that day
        int defaultRate = 0;//this is returned in case of error
        HashMap<Integer, Integer> rateMap= new HashMap<>();
        rateMap.put(1, 0);//Monday's rateStrategy
        rateMap.put(2, 0);//Tuesday
        rateMap.put(3, 0);//Wednesday
        rateMap.put(4, 0);//Thursday
        rateMap.put(5, 0);//Friday
        rateMap.put(6, 1);//Saturday
        rateMap.put(7, 1);//Sunday
        
        strategy = rateMap.get(day);
        
        if (strategy == 1){//special rate (progressive)
            return weekendStrategy.calculatePayment(inserted);
        }else{//default rate (linear)
            return weekendStrategy.calculatePayment(inserted);
        }//Note: add more strategies by adding more options
    }
}
