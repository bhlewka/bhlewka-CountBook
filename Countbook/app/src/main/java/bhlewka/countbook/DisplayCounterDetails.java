package bhlewka.countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

public class DisplayCounterDetails extends AppCompatActivity {

    private Counter counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Gson gson = new Gson();
        // I'd like to think this sets what activity we are moving to
        setContentView(R.layout.activity_display_details);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String counterJson = bundle.getString("counter");
        this.counter = gson.fromJson(counterJson, Counter.class);
        this.updateScreen();

        // This should all be condensed into an update method
        // So when buttons are pressed their outcomes can be seen
        // But we also have to make sure the edits done to the counter are
        // also changed in the countBook list of counters
        // Assign the counter variables
//        String name = this.counter.getName();
//        Integer value = this.counter.getCurrentValue();
//        String comment = this.counter.getComment();
//
//
//        // Capture the layout's TextView and set the string as its text
//        TextView counterName = (TextView) findViewById(R.id.counterName);
//        counterName.setText(name);
//
//        // We need to convert value to a string
//        TextView counterCount = (TextView) findViewById(R.id.counterCount);
//        counterCount.setText(value.toString());
//
//        TextView counterComment = (TextView) findViewById(R.id.counterComment);
//        counterComment.setText(comment);

    }
    // Updates the screen
    private void updateScreen(){
        String name = this.counter.getName();
        Integer value = this.counter.getCurrentValue();
        String comment = this.counter.getComment();


        // Capture the layout's TextView and set the string as its text
        TextView counterName = (TextView) findViewById(R.id.counterName);
        counterName.setText(name);

        // We need to convert value to a string
        TextView counterCount = (TextView) findViewById(R.id.counterCount);
        counterCount.setText(value.toString());
//
        TextView counterComment = (TextView) findViewById(R.id.counterComment);
        counterComment.setText(comment);
    }

    public void incrementButton(View view){
        this.counter.increment();
        this.updateScreen();
    }
    public void decrementButton(View view){
        this.counter.decrement();
        this.updateScreen();
    }
    public void resetButton(View view){
        this.counter.reset();
        this.updateScreen();
    }
    public void editButton(View view){}
    public void deleteButton(View view){}
}
