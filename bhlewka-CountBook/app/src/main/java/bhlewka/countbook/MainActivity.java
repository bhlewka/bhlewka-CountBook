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
import android.widget.SimpleCursorAdapter;

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

    // Initialize the
    private static final String FILENAME = "file.sav";
    private ArrayList<Counter> countBook;
    private ArrayAdapter<Counter> adapter;
    private ListView counterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button newCounter = (Button) findViewById(R.id.button3);

        newCounter.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                setResult(RESULT_OK);
                newCounter(view);
                adapter.notifyDataSetChanged();
                saveInFile();
            }
        });

        // Create the first counter object
        this.countBook = new ArrayList<Counter>();
        counterList = (ListView) findViewById(R.id.list);

        // Create a message handling object as an anonymous class.
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

    }
    // Create on start method
    @Override
    protected void onStart(){
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Counter>(this, android.R.layout.simple_list_item_1, countBook);
        counterList.setAdapter(adapter);

    }

    /** This will create a new counter object, as well as display it on the screen */

    public void displayDetails(View view, Integer position){

        // Wait but how do we get that specific object in the list?

        Intent intent = new Intent(this, DisplayCounterDetails.class);

        intent.putExtra("position",position);

        startActivity(intent);
    }

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


    public void newCounter(View view) {
        // Should this function take us to a new screen that allows the user to
        // Type in their name and initial value then hit a new button?
        // Naa, we can make it just add a new counter to the bottom first

        Counter counter;


        // This intent takes us to the DisplayMessageActivity page thing
        // Lets rename it to counterDetails
        //Intent intent = new Intent(this, DisplayCounterDetails.class);


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

        // This down here will be required for the update setting

        // Convert the counter to a Gson object
//        Gson gson = new Gson();
//        String counter2 = gson.toJson(counter);
//
//        bundle.putString("counter",counter2);
//
//
//        intent.putExtras(bundle);
//
//        startActivity(intent);
    }

    // This will update the screen after a new counter is added to the list
    //public void updateScreen(){}
}
