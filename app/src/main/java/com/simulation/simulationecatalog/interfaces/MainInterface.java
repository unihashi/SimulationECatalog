package com.simulation.simulationecatalog.interfaces;

import com.simulation.simulationecatalog.cores.CorePresenter;
import com.simulation.simulationecatalog.cores.CoreUIView;
import com.simulation.simulationecatalog.data.components.roomdatabases.entity.SimulationTask;

import java.util.List;

public interface MainInterface {

    interface View extends CoreUIView {
        void onResultList(List<SimulationTask> result);
    }

    interface Presenter extends CorePresenter {
        void initUseCase();

        void onGetCatalogFromDatabase();
    }

}
