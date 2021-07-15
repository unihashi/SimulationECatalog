package com.simulation.simulationecatalog.data.components;

public class ConfirmationDialogEvent {
    public interface ConfirmationDialogEventListener{
        void confirm();
        void cancel();
    }
}