package bhlewka.countbook;

import java.util.Date;

/**
 * Created by Ben on 9/29/2017.
 */

// 2 constructors even though we only ever call the one
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

    // Getter
    public Integer getCurrentValue(){
        return this.currentValue;
    }

    // Getter
    public String getName() {
        return this.name;
    }

    // Getter
    public String getDate() {
        String formattedDate = String.valueOf(this.date.getYear() + 1900) + "-";
        formattedDate += String.format("%02d", this.date.getMonth() + 1) + "-";
        formattedDate += String.format("%02d", this.date.getDate());
        return formattedDate;
    }

    // Getter
    public String getComment() {
        return this.comment;
    }

    // Increments the counter by 1, changes the date
    public void increment(){
        this.currentValue += 1;
        this.date = new Date();
    }

    // Increments the counter by 1, changes the date
    public void decrement(){
        if(currentValue > 0)
            this.currentValue -= 1;
            this.date = new Date();
    }
    // Sets the current value to the initial value
    public void reset(){
        this.currentValue = this.initialValue;
        this.date = new Date();
    }

    // Sets the name
    public void setName(String name){
        this.name = name;
    }

    // Sets the initial value, sets the current value
    public void setValue(Integer count){
        this.currentValue = count;
        this.initialValue = count;
    }
    // Sets the comment
    public void setComment(String comment){
        this.comment = comment;
    }

    @Override
    // String representation of our counter
    // Its pretty ugly
    public String toString(){
        String date = this.getDate();
        return this.name + "     -     " + this.currentValue + "    -    " + date;
    }
}

