package bhlewka.countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private CountBook countBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the first counter object
        this.countBook = new CountBook();

    }
    /** This will create a new counter object, as well as display it on the screen */

    public void newCounter(View view) {
        // Should this function take us to a new screen that allows the user to
        // Type in their name and initial value then hit a new button?
        // Naa, we can make it just add a new counter to the bottom first

        this.countBook.newCounter("test", 0);


        // This intent takes us to the DisplayMessageActivity page thing
        // Lets rename it to counterDetails
        Intent intent = new Intent(this, DisplayCounterDetails.class);

        Bundle nameVal = new Bundle();

        // Get the name value
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();

        // Get the initial vlaue
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        String message2 = editText2.getText().toString();

        // Get the optional comment, not required
        EditText editText3 = (EditText) findViewById(R.id.editText3);
        String message3 = editText2.getText().toString();

        nameVal.putString("name", message);
        nameVal.putString("initialValue", message2);
        nameVal.putString("comment", message3);

        // Put the name,value bundle into the activity
        // No no bad bad, this is autistic, we have all the information we
        // need to make the counter right here, and pass that in instead
        intent.putExtras(nameVal);

        startActivity(intent);
    }
}
