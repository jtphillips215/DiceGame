package edu.volstate.dicegame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // add reset button and results counter
    // controls and dice object
    private TextView resultsText;
    private TextView totalText;
    private TextView bonusText;
    private Menu menu;
    private final ArrayList<ImageView> diceImages = new ArrayList<>();
    private Dice dice = new Dice();
    ArrayList<Player> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Player player = new Player("");

        // shared preferences
        //SharedPreferences sharedPreferences = getSharedPreferences("stats", MODE_PRIVATE);
        //SharedPreferences.Editor myEditor = sharedPreferences.edit();

        // these were all moved at the request of the IDE
        Button rollButton;
        Button scoreboardButton;
        ImageView leftDice;
        ImageView middleDice;
        ImageView rightDice;
        TextView nameText;

        // assigning controls that need to be updated
        resultsText = findViewById(R.id.textRollResult);
        totalText = findViewById(R.id.textTotalScoreOutput);
        bonusText = findViewById(R.id.textBonusOutput);
        leftDice = findViewById(R.id.imageDiceLeft);
        middleDice = findViewById(R.id.imageDiceMiddle);
        rightDice = findViewById(R.id.imageDiceRight);

        // arraylist for dice images to be accessed when updating images
        diceImages.add(leftDice);
        diceImages.add(middleDice);
        diceImages.add(rightDice);

        // assigning controls users can interact with
        rollButton = findViewById(R.id.buttonRollDice);
        scoreboardButton = findViewById(R.id.buttonScoreboard);
        nameText = findViewById(R.id.editTextName);

        // setting initial dice images and scores
        updateUI();
        dice.rollDice();
        updateImages();

        // on click listener for button press; updated to try out lambda
        rollButton.setOnClickListener(view -> {

            bonusText.setText("");
            dice.rollDice();
            dice.setTotal();
            player.incrementRolls();

            // getting and saving the name when roll dice is clicked
            // String name = nameText.getText().toString();
            //myEditor.putString("name", name);
            // Log.d("name",  name);

            // updating roll counter in shared preferences
           // myEditor.putInt("counter", dice.getRollCounter());

            // updating roll score for user in shared preferences
            //myEditor.putInt(name, dice.getRollScore());

            // applying changes to shared preferences
            //myEditor.apply();

            // set these to bool in dice class with onClick listener in menu class
            // if else for double and triple to cal function for adding bonus to total
            // and update label for bonus. Only fires if checkbox is checked in settings
            if (dice.isTripleStatus() && dice.tripleTest()) {
                dice.totalPlusTriple();
                bonusText.setText(R.string.tripleText);
                player.incrementTriples();
            }
            else if (dice.isDoubleStatus() && dice.doubleTest()) {
                dice.totalPlusDouble();
                bonusText.setText(R.string.doubleString);
                player.incrementDoubles();
            }
            else { bonusText.setText(R.string.noBonus); }
            updateUI();
            updateImages();
        });

        // onclick listener creates new intent and passes data to scoreboard class and layout
        scoreboardButton.setOnClickListener(view -> {

            player.setName(nameText.getText().toString());
            player.setHighScore(dice.getTotal());
            players.add(player);
            Intent intent = new Intent(getBaseContext(), Scoreboard.class);
            intent.putExtra("dice_object", dice);
            intent.putParcelableArrayListExtra("players", players);
            dice.resetDice();
            resultsText.setText("0");
            totalText.setText("0");
            startActivity(intent);
        });
    }

    // new method for updating UI across all areas this is needed
    void updateUI() {
        resultsText.setText(String.valueOf(dice.getRollScore()));
        totalText.setText(String.valueOf(dice.getTotal()));
    }

    // moved switch statement to switch class
    // using switch class to return the die image to main here to separate logic from view
    void updateImages() {
        ArrayList<String> diceList = dice.getDiceList();
        for (int i = 0; i < diceImages.size(); i++) {
            diceImages.get(i).setImageResource(ImageSwitch.changeImage(diceList.get(i)));
        }
    }

    // sendDice sends dice object to menu view
    Dice sendDice() {
        return dice;
    }


    // methods to save our parcel and restore our parcel
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("parcelable", dice);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.dice = savedInstanceState.getParcelable("parcelable");
        updateUI();
    }

    // on create options menu for hamburger menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            DialogMenu dialogMenu = new DialogMenu();
            dialogMenu.show(getSupportFragmentManager(), "");
            return true;
        }

        if(id == R.id.action_about) {
            DialogAbout dialogAbout = new DialogAbout();
            dialogAbout.show(getSupportFragmentManager(), "");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}