package com.simulation.simulationecatalog.domain.usecases.simulation;

import com.simulation.simulationecatalog.classes.DisposableObservable;
import com.simulation.simulationecatalog.classes.UseCaseResultEvent;
import com.simulation.simulationecatalog.cores.CoreUseCase;
import com.simulation.simulationecatalog.data.components.roomdatabases.database.SimulationRoomDatabase;
import com.simulation.simulationecatalog.data.components.roomdatabases.entity.SimulationTask;

import java.util.List;

import io.reactivex.Observable;

public class GetCatalogUseCase extends CoreUseCase<List<SimulationTask>, SimulationTask> {

    private UseCaseResultEvent<List<SimulationTask>> useCaseResultEvent;

    public GetCatalogUseCase(UseCaseResultEvent<List<SimulationTask>> useCaseResultEvent) {
        this.useCaseResultEvent = useCaseResultEvent;
    }

    @Override
    public Observable<List<SimulationTask>> buildObservable(SimulationTask tasks) {
        return SimulationRoomDatabase.getInstance().getAllCatalog();
    }

    @Override
    protected DisposableObservable<List<SimulationTask>> getDisposableObservable() {
        return new DisposableObserver();
    }

    class DisposableObserver extends DisposableObservable<List<SimulationTask>> {

        @Override
        public void onNext(List<SimulationTask> list) {
            super.onNext(list);
            if (list != null) {
                useCaseResultEvent.onResult(list);
            } else {
                useCaseResultEvent.onError(new NullPointerException(),"no catalog could be found");
            }
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            useCaseResultEvent.onError(e,"get catalog fail");
        }
    }
}