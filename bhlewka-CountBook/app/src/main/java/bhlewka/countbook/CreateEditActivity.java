package bhlewka.countbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

public class CreateEditActivity extends AppCompatActivity {
    // declare the variables we will need for this edit activity
    private static final String FILENAME = "file.sav";
    private Counter counter;
    private ArrayList<Counter> countBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit);
        Intent intent = getIntent();
        loadFromFile();
        this.counter = countBook.get(intent.getExtras().getInt("position"));
    }

    // Takes values set in the boxes and edits the counter with those new values
    // Allows the user to leave certain values blank and not affect the code
    // Ensures a proper integer is entered
    public void updateValues(View view){


        EditText editName = (EditText) findViewById(R.id.editName);

        if (!editName.getText().toString().equals(""))
            this.counter.setName(editName.getText().toString());

        EditText editValue = (EditText) findViewById(R.id.editValue);
        String intValue = editValue.getText().toString();

        EditText editComment = (EditText) findViewById(R.id.editComment);
        if (!editComment.getText().toString().equals(""))
            this.counter.setComment(editComment.getText().toString());

        try{
            Integer value = Integer.valueOf(intValue);
        }
        catch(Exception e) {
            saveInFile();
            finish();
            return;
        }


        Integer value = Integer.valueOf(intValue);
        this.counter.setValue(value);
        saveInFile();
        finish();

//
//

        // https://stackoverflow.com/questions/10407159/how-to-manage-startactivityforresult-on-android
        // September 30th, 2017
    }
    // ""
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

    // ""
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
