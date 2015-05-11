package com.example.marcelba.mystudyschedule;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;


/**
 * Created by marcel on 08/05/2015.
 */
public class DialogMessage extends DialogFragment {

    public static DialogMessage newInstance(int message, boolean decision) {
        DialogMessage f = new DialogMessage();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("message", message);
        args.putBoolean("decision", decision);
        f.setArguments(args);

        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int message = getArguments().getInt("message");
        final boolean decision = getArguments().getBoolean("decision");

        AlertDialog.Builder alertDialog =  new AlertDialog.Builder(getActivity());
        alertDialog.setTitle(message);
        alertDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Utils.dialogPositive();
                        if(decision){
                            ((Subjects)getActivity()).DeleteCurrentSubject();
                        }
                    }
                });
        if(decision) {
            alertDialog.setNegativeButton("NO",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Utils.dialogNegative();
                        }
                    });
        }
        return alertDialog.create();
    }


}
