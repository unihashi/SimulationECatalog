package com.simulation.simulationecatalog.data.components;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;

import com.simulation.simulationecatalog.R;
import com.simulation.simulationecatalog.classes.Util;
import com.simulation.simulationecatalog.cores.CoreDialog;

import static com.simulation.simulationecatalog.cores.CoreDialog.currentDialog;
import static com.simulation.simulationecatalog.cores.CoreDialog.dismiss;

public class ConfirmationDialog  extends CoreDialog {

    public static void show(Context context, String title, String message, final ConfirmationDialogEvent.ConfirmationDialogEventListener listener) {
        try {
            String dialogTitle = null;

            if (!Util.isStringEmpty(title)) {
                dialogTitle = title;
            }

            dismiss();

            currentDialog = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle)
                    .setTitle(dialogTitle)
                    .setMessage(message)
                    .setCancelable(false)
                    .setPositiveButton(context.getString(R.string.btn_yes), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            if (listener != null)
                                listener.confirm();

                            dismiss();

                        }
                    })
                    .setNegativeButton(context.getString(R.string.btn_no), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            if (listener != null)
                                listener.cancel();

                            dismiss();
                        }
                    })
                    .create();

            currentDialog.show();

        } catch (WindowManager.BadTokenException | NullPointerException e) {
            Log.e("BadTokenException", "show: ", e);
        }
    }

    public static void show(Context context, String title, String message, String btnYesLabel, String btnNoLabel, final ConfirmationDialogEvent.ConfirmationDialogEventListener listener) {
        try {

            String dialogTitle = null;

            if (!Util.isStringEmpty(title)) {
                dialogTitle = title;
            }

            dismiss();

            currentDialog = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle)
                    .setTitle(dialogTitle)
                    .setMessage(message)
                    .setCancelable(false)
                    .setPositiveButton(btnYesLabel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            if (listener != null)
                                listener.confirm();

                            dismiss();

                        }
                    })
                    .setNegativeButton(btnNoLabel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            if (listener != null)
                                listener.cancel();

                            dismiss();
                        }
                    })
                    .create();

            currentDialog.show();

        } catch (WindowManager.BadTokenException | NullPointerException e) {
            Log.e("BadTokenException", "show: ", e);
        }
    }

}