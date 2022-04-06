package edu.volstate.dicegame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;

public class DialogAbout extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // using builder to generate our menu view
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View menuView = inflater.inflate(R.layout.activity_about, null);

        // setting title
        builder.setView(menuView).setMessage("About");

        // adding button and handling button
        Button buttonClose;
        buttonClose = menuView.findViewById(R.id.buttonClose);

        buttonClose.setOnClickListener(view -> dismiss());

        return builder.create();
    }

}
