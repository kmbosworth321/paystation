package paystation.domain;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class AlternatingRateStrategy implements RateStrategy {
    private RateStrategy weekdayStrategy;
    private RateStrategy weekendStrategy;
    private HashMap<Integer, Integer> rateMap;

    public AlternatingRateStrategy() {
        this.weekdayStrategy = new LinearRateStrategy();
        this.weekendStrategy  = new ProgressiveRateStrategy();
        this.rateMap= new HashMap<>();
        rateMap.put(1, 0);//Monday's rateStrategy
        rateMap.put(2, 0);//Tuesday
        rateMap.put(3, 0);//Wednesday
        rateMap.put(4, 0);//Thursday
        rateMap.put(5, 0);//Friday
        rateMap.put(6, 1);//Saturday
        rateMap.put(7, 1);//Sunday
    }

    @Override
    public int calculatePayment(int inserted) {
        int strategy;
        int day = Calendar.DAY_OF_WEEK;
        
        strategy = rateMap.get(day);
        if (strategy == 1){
            return weekendStrategy.calculatePayment(inserted);
        }else{
            return weekdayStrategy.calculatePayment(inserted);
        }//Note: add more strategies by adding more options
    }
}
