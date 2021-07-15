package com.simulation.simulationecatalog.cores;

public interface CorePresenter {

    default void onStart(){}

    default void onPause(){}

    default void onStop(){}

    default void onResume(){}

    default void onDestroy(){}

    default void onError(Throwable t){}

}