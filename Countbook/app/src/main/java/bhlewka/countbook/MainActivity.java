package bhlewka.countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //private CountBook countBook;
    private ArrayList<Counter> countBook;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the first counter object
        this.countBook = new ArrayList<Counter>();

    }
    /** This will create a new counter object, as well as display it on the screen */

    public void newCounter(View view) {
        // Should this function take us to a new screen that allows the user to
        // Type in their name and initial value then hit a new button?
        // Naa, we can make it just add a new counter to the bottom first

        Counter counter;


        // This intent takes us to the DisplayMessageActivity page thing
        // Lets rename it to counterDetails
        Intent intent = new Intent(this, DisplayCounterDetails.class);

        Bundle bundle = new Bundle();

        // Get the name value
        EditText editText = (EditText) findViewById(R.id.nameInput);
        String name = editText.getText().toString();

        // Get the initial vlaue
        EditText editText2 = (EditText) findViewById(R.id.valueInput);
        String initialValue = editText2.getText().toString();

        // Ensure input thing is a digit
        // How do we go about this?
        // We use a number box, and ensure no blanks are entered basically
        try{
            Integer value = Integer.valueOf(initialValue);
        }
        catch(Exception e) {
            return;
        }

        Integer value = Integer.valueOf(initialValue);
        // Get the optional comment, not required
        EditText editText3 = (EditText) findViewById(R.id.commentInput);
        String comment = editText3.getText().toString();

        counter = new Counter(name, value, comment);
        // Adds the counter to the main activities memory thing
        this.countBook.add(counter);

        // Convert the counter to a Gson object
        Gson gson = new Gson();
        String counter2 = gson.toJson(counter);

        bundle.putString("counter",counter2);


        intent.putExtras(bundle);

        startActivity(intent);
    }

    // This will update the screen after a new counter is added to the list
    public void updateScreen(){}
}
