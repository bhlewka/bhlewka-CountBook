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

    public void append(Counter counter){
        this.counterArray.add(counter);
    }
}
