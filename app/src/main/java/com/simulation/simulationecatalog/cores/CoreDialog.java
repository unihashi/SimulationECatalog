package com.simulation.simulationecatalog.cores;

import androidx.appcompat.app.AlertDialog;

public class CoreDialog {

    public static AlertDialog currentDialog = null;

    public static void dismiss() {
        if (currentDialog != null) {
            currentDialog.dismiss();
            currentDialog = null;
        }
    }
}
