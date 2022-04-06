package edu.volstate.dicegame;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Map;

public class Scoreboard extends AppCompatActivity {
    // stuff can go here
    ArrayList<Player> players = new ArrayList<>();

    //overriding onCreate and setting layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoreboard);

        Button returnButton = findViewById(R.id.buttonReturn);


        // getting data from intent on main activity
        Bundle bundle = getIntent().getExtras();
        int rollCounter = bundle.getInt("roll_count");
        Log.d("counter scoreboard", String.valueOf(rollCounter));
        players = bundle.getParcelableArrayList("players");
        Player player = players.get(players.size() - 1);

        for (int i = 0; i < players.size(); i++) {
            Log.d("scoreboard player name:", players.get(i).getName());
        }

//        SharedPreferences sharedPreferences = getSharedPreferences("stats", MODE_PRIVATE);
//        // Log.d("name", name.toString());

        RecyclerView recyclerView = findViewById(R.id.scoreboardRecycler);
        // instantiating adapter
        SbRecyclerViewAdapter adapter = new SbRecyclerViewAdapter(this, players);
        // attaching view and adapter
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // programmatic access to layout stuff
        TextView rollCounterText = findViewById(R.id.textGlobalRolls);
        TextView thanksText = findViewById(R.id.textThanks);

        // setting roll counter text
        rollCounterText.setText(String.format("Total Dice Rolls: %s", rollCounter));

        // setting thanks text
        String thanks = String.format("Thanks for playing, %s!", player.getName());
        thanksText.setText(thanks);

        returnButton.setOnClickListener(view -> finish());


//        // setting scoreboard text
//        String scoreboard = "";
//        // getting our information out of shared preferences
//        Map<String, ?> allEntries = sharedPreferences.getAll();
//        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
//            if (entry.getKey().equals("name") || entry.getKey().equals("counter")) {
//                continue;
//            }
//            scoreboard += entry.getKey() + ": " + entry.getValue().toString() + "\n";
//            Log.d("map", entry.getKey().getClass().getName() + " " + entry.getKey() +
//                    " " + entry.getValue().getClass().getName() + " " +
//                    entry.getValue().toString());
//        }
//        scoreboardText.setText(scoreboard);
    }
    // more code maybe
}