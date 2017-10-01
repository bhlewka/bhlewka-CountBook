package bhlewka.countbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Initialize the variables that we will be using

    private static final String FILENAME = "file.sav";
    private ArrayList<Counter> countBook;
    private ArrayAdapter<Counter> adapter;
    private ListView counterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button newCounter = (Button) findViewById(R.id.button3);

        // This is the listener for the new button press
        // Calls the newCounter method
        newCounter.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                setResult(RESULT_OK);
                newCounter(view);
                adapter.notifyDataSetChanged();
                saveInFile();
            }
        });

        // Initialize the counter array, and the counter listview
        this.countBook = new ArrayList<Counter>();
        counterList = (ListView) findViewById(R.id.list);

        // Create a message handling object as an anonymous class.
        // load from file is called before and after to ensure data is up to date
        AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                //displayDetails(v, position);
                loadFromFile();
                displayDetails(v, position);
                loadFromFile();
            }
        };

        counterList.setOnItemClickListener(mMessageClickedHandler);
        // from https://developer.android.com/guide/topics/ui/declaring-layout.html#AdapterViews
        // September 30th, 2017

    }
    // Create on start method
    // Initializes the array adapter which is used to display our list of counters
    @Override
    protected void onStart(){
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Counter>(this, android.R.layout.simple_list_item_1, countBook);
        counterList.setAdapter(adapter);

    }

    // This will display the details of a counter
    // Passes position in so the activity can load from file and access the proper counter
    public void displayDetails(View view, Integer position){

        Intent intent = new Intent(this, DisplayCounterDetails.class);

        intent.putExtra("position",position);

        startActivity(intent);
    }

    // This is the load from file function from our lonely twitter
    // Its called often, as it is how we have persistent data
    // May not be the most efficient, especially with larger files
    // Fragments possible the answer? Or activityWithResult, however
    // I could not figure it out
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //Taken from https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-09-19
            Type listType = new TypeToken<ArrayList<Counter>>(){}.getType();
            countBook = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            countBook = new ArrayList<Counter>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }

    }

    // This is the save from file function from our lonely twitter
    // Its called often, as it is how we have persistent data
    // May not be the most efficient, especially with larger files
    // Fragments possible the answer? Or activityWithResult, however
    // I could not figure it out
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(countBook, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    // This function is called after the new counter button is pressed, and takes the values written in
    // to create a new counter
    // It ensures a proper integer is put in
    // This is a small 32bit int however, and could have been created as a long, or double
    // But in the scope of this app, if they increment decrement by 1, I don't see
    // a user requiring much larger integers
    public void newCounter(View view) {

        Counter counter;

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

    }

}
