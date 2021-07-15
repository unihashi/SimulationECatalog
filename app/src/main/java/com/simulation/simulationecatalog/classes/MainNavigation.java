package com.simulation.simulationecatalog.classes;

public class MainNavigation {

//    public static final int HOME_FRAGMENT = 1;

    public interface MainNavigationListener {
        void onChangeFragment(int fragmentId);

        void onChangeFragment(int fragmentId, int position, String kw);
    }
}
