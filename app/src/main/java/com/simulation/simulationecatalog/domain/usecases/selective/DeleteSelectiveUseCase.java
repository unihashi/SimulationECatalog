package com.simulation.simulationecatalog.domain.usecases.selective;

import com.simulation.simulationecatalog.classes.DisposableObservable;
import com.simulation.simulationecatalog.classes.UseCaseResultEvent;
import com.simulation.simulationecatalog.cores.CoreUseCase;
import com.simulation.simulationecatalog.data.components.roomdatabases.database.SimulationRoomDatabase;

import io.reactivex.Observable;

public class DeleteSelectiveUseCase extends CoreUseCase<Integer, Integer> {

    private UseCaseResultEvent<Integer> useCaseResultEvent;

    public DeleteSelectiveUseCase(UseCaseResultEvent<Integer> useCaseResultEvent) {
        this.useCaseResultEvent = useCaseResultEvent;
    }

    @Override
    public Observable<Integer> buildObservable(Integer delete) {
        return SimulationRoomDatabase.getInstance().deleteAllColor();
    }

    @Override
    protected DisposableObservable<Integer> getDisposableObservable() {
        return new DisposableObserver();
    }

    class DisposableObserver extends DisposableObservable<Integer> {

        @Override
        public void onNext(Integer result) {
            super.onNext(result);
            useCaseResultEvent.onResult(result);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            useCaseResultEvent.onError(e,"delete color fail");
        }
    }
}