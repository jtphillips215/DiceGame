package edu.volstate.dicegame;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.Random;
import java.util.ArrayList;

public class Dice implements Parcelable {

    // reorganized class to put like things together
    // moved code from main activity to dice class so that settings dialog screen could interact
    // with dice class booleans for double and triple checkbox status

    // attributes
    private int total;
    private ArrayList<String> diceList = new ArrayList<>();
    private int rollScore;
    // added boolean attributes to track status of menu options for double or triple checkboxes
    private boolean doubleStatus;
    private boolean tripleStatus;
    private int rollCounter;

    // constructor for dice class making initial roll array 0
    public Dice() {
        this.diceList.add("0");
        this.diceList.add("0");
        this.diceList.add("0");
        this.doubleStatus = true;
        this.tripleStatus = true;
        this.rollCounter = 0;
    }

    // methods for accessing double and triple status
    public boolean isDoubleStatus() {
        return doubleStatus;
    }

    public boolean isTripleStatus() {
        return tripleStatus;
    }

    // methods for accessing triple status
    public void setDoubleStatus(boolean doubleStatus) {
        this.doubleStatus = doubleStatus;
    }

    public void setTripleStatus(boolean tripleStatus) {
        this.tripleStatus = tripleStatus;
    }

    // method for accessing dice list
    public ArrayList<String> getDiceList() {
        return this.diceList;
    }

    // method for random numbers
    public void rollDice() {
        Random rand = new Random();
        for(int i = 0; i < 3; i++) {
            int dice = rand.nextInt(6) + 1;
            this.diceList.set(i, String.valueOf(dice));
        }
        rollCounter++;
    }

    // method for accessing roll score
    public int getRollScore() {
        this.rollScore = 0;
        for (int i = 0; i < diceList.size(); i++) {
            this.rollScore += Integer.parseInt(diceList.get(i));
        }
        // added code to resolve roll score bug from previous assignment;
        if (this.isTripleStatus() && this.tripleTest()) {
            this.rollScore += 100;
        }
        else if (this.isDoubleStatus() && this.doubleTest()) {
            this.rollScore += 50;
        }
        if (rollCounter == 0) {
            return 0;
        }
        else {
            return this.rollScore;
        }
    }

    // method for accessing total
    public int getTotal() {
        return this.total;
    }

    // method for setting total
    public void setTotal() {
        for (int i = 0; i < diceList.size(); i++) {
            this.total += Integer.parseInt(diceList.get(i));
        }
    }

    // method for testing doubles
    public Boolean doubleTest() {
        String dice1 = diceList.get(0);
        String dice2 = diceList.get(1);
        String dice3 = diceList.get(2);
        // the ide really preferred this compared to traditional if statement
        return dice1.equals(dice2) || dice1.equals(dice3) || dice2.equals(dice3);
    }

    // method for testing triples
    public Boolean tripleTest() {
        String dice1 = diceList.get(0);
        String dice2 = diceList.get(1);
        String dice3 = diceList.get(2);
        // I used the same syntax as the double test method here
        return dice1.equals(dice2) && dice2.equals(dice3);
    }

    // method for adding double bonus
    public void totalPlusDouble() {
        this.total += 50;
    }

    // method for adding triple bonus
    public void totalPlusTriple() {
        this.total += 100;
    }

    // method for resetting scores
    public void resetDice() {
        this.rollScore = 0;
        this.total = 0;
    }

    // parcel methods saving total and dice array
    protected Dice(Parcel in) {
        total = in.readInt();
        rollScore = in.readInt();
        diceList = in.createStringArrayList();
    }

    public static final Creator<Dice> CREATOR = new Creator<Dice>() {
        @Override
        public Dice createFromParcel(Parcel in) {
            return new Dice(in);
        }

        @Override
        public Dice[] newArray(int size) {
            return new Dice[size];
        }
    };

    // parcel methods for describe contents and writing to parcel
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(total);
        parcel.writeStringList(diceList);
    }
}