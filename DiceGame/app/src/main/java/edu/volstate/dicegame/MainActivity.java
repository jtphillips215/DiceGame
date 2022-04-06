package edu.volstate.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button rollButton;
    private TextView resultsText;
    private TextView totalText;
    private Dice dice = new Dice();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assigning controls that need to be updated
        rollButton = findViewById(R.id.buttonRollDice);
        resultsText = findViewById(R.id.textRollResult);
        totalText = findViewById(R.id.textTotalScoreOutput);

        // setting initial values for dice roll and total
        resultsText.setText(String.format("%s\t\t%s\t\t%s\t\t", dice.getDiceList().get(0),
                dice.getDiceList().get(1), dice.getDiceList().get(2)));
        totalText.setText(String.valueOf(dice.getTotal()));

        // on click listener for button press
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dice.rollDice();
                dice.setTotal();
                resultsText.setText(String.format("%s\t\t%s\t\t%s\t\t", dice.getDiceList().get(0),
                        dice.getDiceList().get(1), dice.getDiceList().get(2)));
                totalText.setText(String.valueOf(dice.getTotal()));
            }
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
        resultsText.setText(String.format("%s\t\t%s\t\t%s\t\t", dice.getDiceList().get(0),
                dice.getDiceList().get(1), dice.getDiceList().get(2)));
        totalText.setText(String.valueOf(dice.getTotal()));
    }
}