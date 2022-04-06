package edu.volstate.dicegame;

public class ImageSwitch {
    // using this as a helper class to separate logic from view
    public static int changeImage (String pips) {
        switch (pips) {
            case "1":
                return R.drawable.die_1;
            case "2":
                return R.drawable.die_2;
            case "3":
                return R.drawable.die_3;
            case "4":
                return R.drawable.die_4;
            case "5":
                return R.drawable.die_5;
            case "6":
                return R.drawable.die_6;
        }
        return 0;
    }
}
