package com.simulation.simulationecatalog.classes;

import android.os.Bundle;

import com.simulation.simulationecatalog.data.components.roomdatabases.entity.SelectiveTask;
import com.simulation.simulationecatalog.data.components.roomdatabases.entity.SimulationTask;

import java.util.List;

public class Singleton {

    private static Singleton _instance = null;
    //Parameter
    public List<SimulationTask> simulationTaskList = null;
    public List<SelectiveTask> selectiveTaskList = null;

    protected Singleton() {

    }

    public static Singleton instance() {

        if (_instance == null) {
            _instance = new Singleton();
        }

        return _instance;
    }

    public static Singleton getInstance() {
        return _instance;
    }

    public Bundle saveInstanceState(Bundle outState) {
        return outState;
    }

    public void restoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {

        }
    }

}
