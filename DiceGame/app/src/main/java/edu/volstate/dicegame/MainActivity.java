package edu.volstate.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // controls and dice object
    private TextView resultsText;
    private TextView totalText;
    private TextView bonusText;
    private ArrayList<ImageView> diceImages = new ArrayList();
    private Dice dice = new Dice();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // these were all moved at the request of the IDE
        Button rollButton;
        CheckBox checkBoxDouble;
        CheckBox checkBoxTriple;
        ImageView leftDice;
        ImageView middleDice;
        ImageView rightDice;

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

        //assigning controls users can interact with
        rollButton = findViewById(R.id.buttonRollDice);
        checkBoxDouble = findViewById(R.id.checkBoxDouble);
        checkBoxTriple = findViewById(R.id.checkBoxTriple);
        checkBoxDouble.setChecked(true);
        checkBoxTriple.setChecked(true);

        // setting initial dice images and scores
        updateUI();
        dice.rollDice();
        updateImages();

        // on click listener for button press; updated to try out lambda
        rollButton.setOnClickListener(view -> {
            bonusText.setText("");
            dice.rollDice();
            dice.setTotal();
            // if else for double and triple to cal function for adding bonus to total
            // and update label for bonus. Only fires if checkbox is checked
            if (checkBoxTriple.isChecked() && dice.tripleTest()) {
                dice.totalPlusTriple();
                bonusText.setText(R.string.tripleText);
            }
            else if (checkBoxDouble.isChecked() && dice.doubleTest()) {
                dice.totalPlusDouble();
                bonusText.setText(R.string.doubleString);
            }
            else { bonusText.setText(R.string.noBonus); }
            updateUI();
            updateImages();
        });
    }

    // methods to save our parcel and restore our parcel
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("parcelable", dice);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.dice = savedInstanceState.getParcelable("parcelable");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // restoring our text fields on resuming the app after screen rotation
        updateUI();
    }

    // new method for updating UI across all areas this is needed
    void updateUI() {
        resultsText.setText(String.valueOf(dice.getRollScore()));
        totalText.setText(String.valueOf(dice.getTotal()));
        // resetting score to zero on initial startup
        if (dice.getTotal() == 0) {
            resultsText.setText("0");
        }
    }

    // I used an array list to try and minimize the switch chain here
    void updateImages() {
        ArrayList<String> diceList = dice.getDiceList();
        for (int i = 0; i < diceImages.size(); i++)
            switch (diceList.get(i)) {
                case "1":
                    diceImages.get(i).setImageResource(R.drawable.die_1);
                    break;
                case "2":
                    diceImages.get(i).setImageResource(R.drawable.die_2);
                    break;
                case "3":
                    diceImages.get(i).setImageResource(R.drawable.die_3);
                    break;
                case "4":
                    diceImages.get(i).setImageResource(R.drawable.die_4);
                    break;
                case "5":
                    diceImages.get(i).setImageResource(R.drawable.die_5);
                    break;
                case "6":
                    diceImages.get(i).setImageResource(R.drawable.die_6);
                    break;
            }
    }
}