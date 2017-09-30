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
    private String comment = "Test Comment";

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

    public Integer getCurrentValue(){
        return this.currentValue;
    }

    public String getName() {
        return this.name;
    }

    public Date getDate() {
        return this.date;
    }

    public String getComment() {
        return this.comment;
    }

    public void increment(){
        this.currentValue += 1;
    }

    public void decrement(){
        if(currentValue > 0)
            this.currentValue -= 1;
    }

    public void reset(){
        this.currentValue = this.initialValue;
    }

    public void viewDetails(){}
    public void setName(){}
    public void setCount(){}
    public void setComment(){}
    public void delete(){}
}

