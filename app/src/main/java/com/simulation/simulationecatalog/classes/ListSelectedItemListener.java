package com.simulation.simulationecatalog.classes;

import java.util.Date;

public interface ListSelectedItemListener {

    default void onListSelectedItemListener(int position, String name) {

    }

    default void onListNameSelectedItemListener(String name) {

    }

    default void onRefreshItem(int size) {

    }

    default void onAddCalendar(int position, Date dateStart, Date dateEnd, String liveTitleData, String liveDescription) {
    }
}