package com.simulation.simulationecatalog.data.components;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;

import com.simulation.simulationecatalog.R;
import com.simulation.simulationecatalog.classes.Util;
import com.simulation.simulationecatalog.cores.CoreDialog;

public class InformationDialog extends CoreDialog {

    public static void show(Context context, String title, String message, final InformationDialogEvent listener){
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
                    .setNegativeButton(context.getString(R.string.btn_info_dialog_close), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            if (listener != null)
                                listener.onCloseDialog();

                            dismiss();
                        }
                    }).create();

            currentDialog.show();
        } catch (WindowManager.BadTokenException | NullPointerException e) {
            Log.d("BadTokenException", "show: ", e);
        }
    }
}