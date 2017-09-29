package bhlewka.countbook;

import java.util.Date;

/**
 * Created by Ben on 9/29/2017.
 */

public class Counter {
    private String name;
    private Date date;
    private Integer currentValue;
    private Integer initialValue;
    private String comment = "";

    public Counter(String name, Integer initialValue) {
        // Initialize the Counter
        this.name = name;
        this.date = new Date();
        this.initialValue = initialValue;
        this.currentValue = initialValue;
    }

    public Counter(String name, Integer initialValue, String comment) {
        // Initialize the Counter
        this.name = name;
        this.date = new Date();
        this.initialValue = initialValue;
        this.currentValue = initialValue;
        this.comment = comment;
    }

    public void increment(){

    }

    public void decrement(){

    }

    public void reset(){

    }

    public void viewDetails(){

    }

    public void editCount(){}

    public void editComment(){}
    public void delete(){}
}

