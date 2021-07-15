package com.simulation.simulationecatalog.domain.usecases.selective;

import com.simulation.simulationecatalog.classes.DisposableObservable;
import com.simulation.simulationecatalog.classes.UseCaseResultEvent;
import com.simulation.simulationecatalog.cores.CoreUseCase;
import com.simulation.simulationecatalog.data.components.roomdatabases.database.SimulationRoomDatabase;
import com.simulation.simulationecatalog.data.components.roomdatabases.entity.SelectiveTask;

import io.reactivex.Observable;

public class InsertSelectiveUseCase extends CoreUseCase<Long, SelectiveTask> {

    private UseCaseResultEvent<Boolean> useCaseResultEvent;

    public InsertSelectiveUseCase(UseCaseResultEvent<Boolean> useCaseResultEvent) {
        this.useCaseResultEvent = useCaseResultEvent;
    }

    @Override
    public Observable<Long> buildObservable(SelectiveTask tasks) {
        return SimulationRoomDatabase.getInstance().InsertColor(tasks);
    }

    @Override
    protected DisposableObservable<Long> getDisposableObservable() {
        return new DisposableObserver();
    }

    class DisposableObserver extends DisposableObservable<Long> {

        @Override
        public void onNext(Long aLong) {
            super.onNext(aLong);
            useCaseResultEvent.onResult(aLong > -1);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            useCaseResultEvent.onError(e,"update fail");
        }
    }
}