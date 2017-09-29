package bhlewka.countbook;

import java.util.ArrayList;

/**
 * Created by Ben on 9/29/2017.
 */

public class CountBook {

    private ArrayList<Counter> counterArray;

    public CountBook() {
        this.counterArray = new ArrayList<Counter>();
    }

    public void newCounter(String name, Integer initialValue){
        // We want this class to somehow create a new counter that
        // appears on our list of counters

        // This creates the new counter and appends it to the array of counters
        Counter newCounter = new Counter(name, initialValue);
        this.counterArray.add(newCounter);
    }
}
