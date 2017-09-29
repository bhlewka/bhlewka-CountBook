package bhlewka.countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayCounterDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // I'd like to think this sets what activity we are moving to
        setContentView(R.layout.activity_display_details);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra("name");
        String message2 = intent.getStringExtra("initialValue");
        String message3 = intent.getStringExtra("comment");

        // Capture the layout's TextView and set the string as its text
        TextView counterName = (TextView) findViewById(R.id.counterName);
        counterName.setText(message);

        TextView counterCount = (TextView) findViewById(R.id.counterCount);
        counterCount.setText(message2);

        TextView counterComment = (TextView) findViewById(R.id.counterComment);
        counterComment.setText(message3);

    }
}
