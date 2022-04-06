package edu.volstate.dicegame;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {
    private String name;
    private int doubles = 0;
    private int triples = 0;
    private int highScore = 0;
    private int rollCount = 0;

    public Player(String name) {
        this.name = name;
    }

    protected Player(Parcel in) {
        name = in.readString();
        doubles = in.readInt();
        triples = in.readInt();
        highScore = in.readInt();
        rollCount = in.readInt();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    // getters and setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getDoubles() {
        return doubles;
    }
    public void setDoubles(int doubles) {
        this.doubles = doubles;
    }
    public void incrementDoubles() {
        this.doubles++;
    }

    public int getTriples() {
        return triples;
    }
    public void setTriples(int triples) {
        this.triples = triples;
    }
    public void incrementTriples() {
        this.triples++;
    }

    public int getHighScore() {
        return  highScore;
    }
    public void setHighScore(int highScore) {
        if (highScore > this.highScore) {
            this.highScore = highScore;
        }
    }

    public int getRollCount() {
        return rollCount;
    }
    public void setRollCount(int rollCount) {
        this.rollCount = rollCount;
    }
    // method for incrementing roll count
    public void incrementRolls() {
        this.rollCount++;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(doubles);
        parcel.writeInt(triples);
        parcel.writeInt(highScore);
        parcel.writeInt(rollCount);
    }
}
