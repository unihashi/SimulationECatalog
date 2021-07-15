package com.simulation.simulationecatalog.domain.usecases.selective;

import com.simulation.simulationecatalog.classes.DisposableObservable;
import com.simulation.simulationecatalog.classes.UseCaseResultEvent;
import com.simulation.simulationecatalog.cores.CoreUseCase;
import com.simulation.simulationecatalog.data.components.roomdatabases.database.SimulationRoomDatabase;
import com.simulation.simulationecatalog.data.components.roomdatabases.entity.SelectiveTask;

import java.util.List;

import io.reactivex.Observable;

public class GetSelectiveUseCase extends CoreUseCase<List<SelectiveTask>, SelectiveTask> {

    private UseCaseResultEvent<List<SelectiveTask>> useCaseResultEvent;

    public GetSelectiveUseCase(UseCaseResultEvent<List<SelectiveTask>> useCaseResultEvent) {
        this.useCaseResultEvent = useCaseResultEvent;
    }

    @Override
    public Observable<List<SelectiveTask>> buildObservable(SelectiveTask tasks) {
        return SimulationRoomDatabase.getInstance().getAllColor();
    }

    @Override
    protected DisposableObservable<List<SelectiveTask>> getDisposableObservable() {
        return new DisposableObserver();
    }

    class DisposableObserver extends DisposableObservable<List<SelectiveTask>> {

        @Override
        public void onNext(List<SelectiveTask> list) {
            super.onNext(list);
            if (list != null) {
                useCaseResultEvent.onResult(list);
            } else {
                useCaseResultEvent.onError(new NullPointerException(),"no color could be found");
            }
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            useCaseResultEvent.onError(e,"get color fail");
        }
    }
}