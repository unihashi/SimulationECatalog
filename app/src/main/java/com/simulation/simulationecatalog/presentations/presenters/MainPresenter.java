package com.simulation.simulationecatalog.presentations.presenters;

import com.simulation.simulationecatalog.classes.Singleton;
import com.simulation.simulationecatalog.classes.UseCaseResultEvent;
import com.simulation.simulationecatalog.cores.CoreUseCase;
import com.simulation.simulationecatalog.data.components.roomdatabases.entity.SimulationTask;
import com.simulation.simulationecatalog.domain.usecases.simulation.GetCatalogUseCase;
import com.simulation.simulationecatalog.interfaces.MainInterface;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter implements MainInterface.Presenter {

    private MainInterface.View view;

    public MainPresenter(MainInterface.View view) {
        this.view = view;
    }

    private List<CoreUseCase> useCaseList = new ArrayList<>();

    private GetCatalogUseCase getCatalogUseCase;

    @Override
    public void initUseCase() {
        useCaseList.add(getCatalogUseCase = new GetCatalogUseCase(new onGetStockResultEvent()));
    }

    @Override
    public void onGetCatalogFromDatabase() {
        getCatalogUseCase.execute(null);
    }

    class onGetStockResultEvent implements UseCaseResultEvent<List<SimulationTask>> {

        @Override
        public void onResult(List<SimulationTask> result) {
            Singleton.instance().simulationTaskList = new ArrayList<>();
            Singleton.instance().simulationTaskList.addAll(result);
            view.onResultList(result);
        }

        @Override
        public void onError(Throwable t, String message) {

        }

    }
}
