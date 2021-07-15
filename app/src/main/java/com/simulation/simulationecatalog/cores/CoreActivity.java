package com.simulation.simulationecatalog.cores;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;

import com.simulation.simulationecatalog.R;
import com.simulation.simulationecatalog.classes.Singleton;
import com.simulation.simulationecatalog.data.components.InformationDialog;

public class CoreActivity extends AppCompatActivity {

    public Dialog loading;
    protected String DEBUG_TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DEBUG_TAG = this.getClass().getSimpleName();
        Singleton.instance().restoreInstanceState(savedInstanceState);

        loading = new Dialog(this);
        loading.requestWindowFeature(Window.FEATURE_NO_TITLE);
        loading.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        loading.setContentView(R.layout.component_loading);
        loading.setCancelable(false);

    }

    protected void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    protected void onDestroy() {
        if (loading != null) {
            loading.dismiss();
        }

        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState = Singleton.instance().saveInstanceState(outState);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Singleton.instance().restoreInstanceState(savedInstanceState);

        super.onRestoreInstanceState(savedInstanceState);
    }

    protected void showDialogUnknownError(Context context) {
        if (context != null) {
            InformationDialog.show(context, context.getString(R.string.hd_dialog_title_error),
                    context.getString(R.string.txt_unknown_error), null);
        }
    }

}

