package edu.volstate.dicegame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.fragment.app.DialogFragment;

public class DialogMenu extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // using builder to generate our menu view
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View menuView = inflater.inflate(R.layout.activity_menu, null);

        // getting reference to calling class and dice object from that class
        MainActivity callingActivity = (MainActivity) getActivity();
        Dice menuDice = callingActivity.sendDice();

        // allowing for programmatic control of widgets
        CheckBox CheckBoxDouble;
        CheckBox CheckBoxTriple;
        Button ButtonReset;
        Button ButtonSave;
        Button ButtonCancel;

        CheckBoxDouble = menuView.findViewById(R.id.checkBoxDouble);
        CheckBoxTriple = menuView.findViewById(R.id.checkBoxTriple);
        ButtonReset = menuView.findViewById(R.id.buttonReset);
        ButtonSave = menuView.findViewById(R.id.buttonSave);
        ButtonCancel = menuView.findViewById(R.id.buttonCancel);

        // checking if dice checkboxes should be checked when menu is created
        CheckBoxDouble.setChecked(menuDice.isDoubleStatus());
        CheckBoxTriple.setChecked(menuDice.isTripleStatus());

        // title of dialog box
        builder.setView(menuView).setMessage("Settings");

        // handle the reset button
        ButtonReset.setOnClickListener(view -> {
            menuDice.resetDice();
            callingActivity.updateUI();
            callingActivity.getDice(menuDice);
            dismiss();
        });

        // handle the cancel button
        ButtonCancel.setOnClickListener(view -> dismiss());

        // handle the save button
        ButtonSave.setOnClickListener(view -> {
            menuDice.setDoubleStatus(CheckBoxDouble.isChecked());
            menuDice.setTripleStatus(CheckBoxTriple.isChecked());
            callingActivity.getDice(menuDice);
            dismiss();
        });

        // returning back to main activity
        return builder.create();
    }

}
