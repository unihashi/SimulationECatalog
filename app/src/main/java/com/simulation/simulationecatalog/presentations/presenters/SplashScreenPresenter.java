package com.simulation.simulationecatalog.presentations.presenters;

import com.simulation.simulationecatalog.classes.Singleton;
import com.simulation.simulationecatalog.classes.UseCaseResultEvent;
import com.simulation.simulationecatalog.cores.CoreUseCase;
import com.simulation.simulationecatalog.data.components.roomdatabases.entity.SelectiveTask;
import com.simulation.simulationecatalog.data.components.roomdatabases.entity.SimulationTask;
import com.simulation.simulationecatalog.domain.usecases.selective.DeleteSelectiveUseCase;
import com.simulation.simulationecatalog.domain.usecases.selective.GetSelectiveUseCase;
import com.simulation.simulationecatalog.domain.usecases.selective.InsertSelectiveUseCase;
import com.simulation.simulationecatalog.domain.usecases.simulation.DeleteCatalogUseCase;
import com.simulation.simulationecatalog.domain.usecases.simulation.GetCatalogUseCase;
import com.simulation.simulationecatalog.domain.usecases.simulation.InsertCatalogUseCase;
import com.simulation.simulationecatalog.interfaces.SplashScreenInterface;

import java.util.ArrayList;
import java.util.List;

public class SplashScreenPresenter implements SplashScreenInterface.Presenter {

    private SplashScreenInterface.View view;
    private List<CoreUseCase> useCaseList = new ArrayList<>();
    private List<SimulationTask> simulationLists;
    private List<SelectiveTask> selectiveTasks;

    private DeleteCatalogUseCase deleteCatalogUseCase;
    private GetCatalogUseCase getCatalogUseCase;
    private InsertCatalogUseCase insertCatalogUseCase;
    private DeleteSelectiveUseCase deleteSelectiveUseCase;
    private GetSelectiveUseCase getSelectiveUseCase;
    private InsertSelectiveUseCase insertSelectiveUseCase;

    public SplashScreenPresenter(SplashScreenInterface.View view) {
        this.view = view;
    }

    @Override
    public void initUseCase() {
        useCaseList.add(deleteCatalogUseCase = new DeleteCatalogUseCase(new DeleteCatalogResultEvent()));
        useCaseList.add(getCatalogUseCase = new GetCatalogUseCase(new GetCatalogResultEvent()));
        useCaseList.add(insertCatalogUseCase = new InsertCatalogUseCase(new InsertCatalogResultEvent()));
        useCaseList.add(deleteSelectiveUseCase = new DeleteSelectiveUseCase(new DeleteColorResultEvent()));
        useCaseList.add(getSelectiveUseCase = new GetSelectiveUseCase(new GetColorResultEvent()));
        useCaseList.add(insertSelectiveUseCase = new InsertSelectiveUseCase(new InsertColorResultEvent()));
    }

    @Override
    public void onDeleteCatalog() {
        deleteCatalogUseCase.execute(null);
    }

    @Override
    public void onInsertCatalogDatabase(List<SimulationTask> taskList) {
        this.simulationLists = taskList;
        onInsertCatalogDatabase(taskList.get(0));
    }

    private void onInsertCatalogDatabase(SimulationTask task) {
        insertCatalogUseCase.execute(task);
    }

    class DeleteCatalogResultEvent implements UseCaseResultEvent<Integer> {

        @Override
        public void onResult(Integer result) {
//            view.onGetCatalogUpdate();
        }

        @Override
        public void onError(Throwable t, String message) {

        }
    }

    class InsertCatalogResultEvent implements UseCaseResultEvent<Boolean> {

        @Override
        public void onResult(Boolean result) {
            simulationLists.remove(0);
            if (simulationLists != null && simulationLists.size() > 0) {
                onInsertCatalogDatabase(simulationLists.get(0));
            } else {
                onGetCatalogFromDatabase();
            }
        }

        @Override
        public void onError(Throwable t, String message) {

        }

    }

    @Override
    public void onGetCatalogFromDatabase() {
        getCatalogUseCase.execute(null);
    }

    class GetCatalogResultEvent implements UseCaseResultEvent<List<SimulationTask>> {

        @Override
        public void onResult(List<SimulationTask> result) {
            Singleton.instance().simulationTaskList = new ArrayList<>();
            Singleton.instance().simulationTaskList.addAll(result);
            view.mockUpDataSelective();
        }

        @Override
        public void onError(Throwable t, String message) {

        }

    }

    @Override
    public void onDeleteColor() {
        deleteSelectiveUseCase.execute(null);
    }

    @Override
    public void onInsertColorDatabase(List<SelectiveTask> taskList) {
        this.selectiveTasks = taskList;
        onInsertColorDatabase(taskList.get(0));
    }

    private void onInsertColorDatabase(SelectiveTask task) {
        insertSelectiveUseCase.execute(task);
    }

    class DeleteColorResultEvent implements UseCaseResultEvent<Integer> {

        @Override
        public void onResult(Integer result) {
//            view.onGetCatalogUpdate();
        }

        @Override
        public void onError(Throwable t, String message) {

        }
    }

    class InsertColorResultEvent implements UseCaseResultEvent<Boolean> {

        @Override
        public void onResult(Boolean result) {
            selectiveTasks.remove(0);
            if (selectiveTasks != null && selectiveTasks.size() > 0) {
                onInsertColorDatabase(selectiveTasks.get(0));
            } else {
                onGetColorFromDatabase();
            }
        }

        @Override
        public void onError(Throwable t, String message) {

        }

    }

    @Override
    public void onGetColorFromDatabase() {
        getSelectiveUseCase.execute(null);
    }

    class GetColorResultEvent implements UseCaseResultEvent<List<SelectiveTask>> {

        @Override
        public void onResult(List<SelectiveTask> result) {
            Singleton.instance().selectiveTaskList = new ArrayList<>();
            Singleton.instance().selectiveTaskList.addAll(result);
            view.onGettingStart();
        }

        @Override
        public void onError(Throwable t, String message) {

        }

    }

}
