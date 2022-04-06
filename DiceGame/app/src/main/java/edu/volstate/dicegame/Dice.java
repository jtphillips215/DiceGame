package edu.volstate.dicegame;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;
import java.util.ArrayList;

public class Dice implements Parcelable {

    // attributes
    private int total;
    private ArrayList<String> diceList = new ArrayList<>();

    // constructor for dice class making initial roll array 0
    public Dice() {
        this.diceList.add("0");
        this.diceList.add("0");
        this.diceList.add("0");
    }

    // parcel methods saving total and dice array
    protected Dice(Parcel in) {
        total = in.readInt();
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

    // method for accessing total
    public int getTotal() {
        return this.total;
    }

    // method for setting total
    public void setTotal() {
        for (int i = 0; i < diceList.size(); i++) {
            int roll = Integer.parseInt(diceList.get(i));
            this.total += roll;
        }
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
    }

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