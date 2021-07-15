package com.simulation.simulationecatalog.cores;

import android.content.Context;

public interface CoreUIView {

    Context context();

    default void showLoading() {
    }

    default void hideLoading() {
    }

    default void showError(String message) {
    }

    default void showToastMessage(String message) {
    }

    default void showInformationDialog(String title, String message){}

    default void showUnknownError(){}

}
