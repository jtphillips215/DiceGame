package edu.volstate.dicegame;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.Map;

public class Scoreboard extends AppCompatActivity {
    // stuff can go here

    //overriding onCreate and setting layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoreboard);

        // programmatic access to layout stuff
        TextView titleText = findViewById(R.id.textScoreboardTitle);
        TextView rollCounterText = findViewById(R.id.textRollCounter);
        TextView thanksText = findViewById(R.id.textThanks);
        TextView scoreboardText = findViewById(R.id.textScoreBoard);

        // getting data from intent on main activity
        Bundle bundle = getIntent().getExtras();
        Dice dice = bundle.getParcelable("edu.volstate.dicegame.Dice");
        String name = bundle.getString("name");
        SharedPreferences sharedPreferences = getSharedPreferences("stats", MODE_PRIVATE);
        // Log.d("name", name.toString());

        // setting roll counter text
        int rollCount = sharedPreferences.getInt("counter", 0);
        String rollText = String.format("%x Total Rolls", rollCount);
        rollCounterText.setText(rollText);

        // setting thanks text
        String thanks = String.format("Thanks for playing, %s!", name);
        thanksText.setText(thanks);

        // setting scoreboard text
        String scoreboard = "";
        // getting our information out of shared preferences
        Map<String, ?> allEntries = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getKey().equals("name") || entry.getKey().equals("counter")) {
                continue;
            }
            scoreboard += entry.getKey() + ": " + entry.getValue().toString() + "\n";
            Log.d("map", entry.getKey().getClass().getName() + " " + entry.getKey() +
                    " " + entry.getValue().getClass().getName() + " " +
                    entry.getValue().toString());
        }
        scoreboardText.setText(scoreboard);
    }
    // more code maybe
}