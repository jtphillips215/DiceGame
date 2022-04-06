package edu.volstate.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button rollButton;
    private TextView resultsText;
    private TextView totalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assigning controls that need to be updated
        rollButton = findViewById(R.id.buttonRollDice);
        resultsText = findViewById(R.id.textRollResult);
        totalText = findViewById(R.id.textTotalScoreOutput);

        // on click listener for button press
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int roll = Dice.rollDice();
                Dice.setTotal(roll);
                resultsText.setText(String.valueOf(roll));
                totalText.setText(String.valueOf(Dice.getTotal()));
            }
        });

    }
}