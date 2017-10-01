package bhlewka.countbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

public class DisplayCounterDetails extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    private Counter counter;
    private ArrayList<Counter> countBook;
    private Integer position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // I'd like to think this sets what activity we are moving to
        setContentView(R.layout.activity_display_details);

        loadFromFile();
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        this.position = intent.getExtras().getInt("position");
        this.counter = countBook.get(this.position);


        this.updateScreen();

    }

    // Updates the screen
    private void updateScreen() {
        String name = this.counter.getName();
        Integer value = this.counter.getCurrentValue();
        String comment = this.counter.getComment();
        String date = this.counter.getDate();


        // Capture the layout's TextView and set the string as its text
        TextView counterName = (TextView) findViewById(R.id.counterName);
        counterName.setText(name);

        // We need to convert value to a string
        TextView counterCount = (TextView) findViewById(R.id.counterCount);
        counterCount.setText(value.toString());
//
        TextView counterComment = (TextView) findViewById(R.id.counterComment);
        counterComment.setText(comment);

        TextView counterDate = (TextView) findViewById(R.id.counterDate);
        counterDate.setText(date);
    }

    public void incrementButton(View view) {
        this.counter.increment();
        this.updateScreen();
        this.saveInFile();
    }

    public void decrementButton(View view) {
        this.counter.decrement();
        this.updateScreen();
        this.saveInFile();
    }

    public void resetButton(View view) {
        this.counter.reset();
        this.updateScreen();
        this.saveInFile();

    }

    public void editButton(View view) {
        Intent intent = new Intent(this, CreateEditActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
        finish();
    }

    public void deleteButton(View view) {
        this.countBook.remove(counter);
        saveInFile();
        finish();
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //Taken from https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-09-19
            Type listType = new TypeToken<ArrayList<Counter>>() {
            }.getType();
            countBook = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            countBook = new ArrayList<Counter>();
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

}
