package com.simulation.simulationecatalog.interfaces;

import com.simulation.simulationecatalog.cores.CorePresenter;
import com.simulation.simulationecatalog.cores.CoreUIView;
import com.simulation.simulationecatalog.data.components.roomdatabases.entity.SelectiveTask;
import com.simulation.simulationecatalog.data.components.roomdatabases.entity.SimulationTask;

import java.util.List;

public interface SplashScreenInterface {

    interface View extends CoreUIView {

        void onGetCatalogUpdate();

        void mockUpDataSelective();

        void onGettingStart();

    }

    interface Presenter extends CorePresenter {

        void initUseCase();

        void onDeleteCatalog();

        void onInsertCatalogDatabase(List<SimulationTask> taskList);

        void onGetCatalogFromDatabase();

        void onDeleteColor();

        void onInsertColorDatabase(List<SelectiveTask> taskList);

        void onGetColorFromDatabase();

    }

}
