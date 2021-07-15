package com.simulation.simulationecatalog.data.components.roomdatabases.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.simulation.simulationecatalog.classes.App;
import com.simulation.simulationecatalog.data.components.roomdatabases.dao.SelectiveDao;
import com.simulation.simulationecatalog.data.components.roomdatabases.dao.SimulationDao;
import com.simulation.simulationecatalog.data.components.roomdatabases.entity.SelectiveTask;
import com.simulation.simulationecatalog.data.components.roomdatabases.entity.SimulationTask;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;

@Database(entities = {SimulationTask.class, SelectiveTask.class}, version = 1, exportSchema = false)
public abstract class SimulationRoomDatabase extends RoomDatabase {

    private static SimulationRoomDatabase instance;

    public abstract SimulationDao simulationTaskDao();
    public abstract SelectiveDao selectiveTaskDao();

    public static SimulationRoomDatabase getInstance() {
        if (instance == null) {
            instance = Room.databaseBuilder(App.instance(), SimulationRoomDatabase.class, "db_simulation_catalog").build();
        }
        return instance;
    }

    public Observable<Long> InsertCatalog(SimulationTask simulationTask) {
        return Observable.fromCallable(() -> instance.simulationTaskDao().insert(simulationTask));
    }

    public Observable<List<SimulationTask>> getAllCatalog() {
        return Observable.fromCallable(
                new Callable<List<SimulationTask>>() {
                    @Override
                    public List<SimulationTask> call() throws Exception {
                        return instance.simulationTaskDao().getAll();
                    }
                });
    }

    public Observable<Integer> deleteAllCatalog() {
        return Observable.fromCallable(
                new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        return instance.simulationTaskDao().deleteAll();
                    }
                });
    }

    public Observable<Long> InsertColor(SelectiveTask selectiveTask) {
        return Observable.fromCallable(() -> instance.selectiveTaskDao().insert(selectiveTask));
    }

    public Observable<List<SelectiveTask>> getAllColor() {
        return Observable.fromCallable(
                new Callable<List<SelectiveTask>>() {
                    @Override
                    public List<SelectiveTask> call() throws Exception {
                        return instance.selectiveTaskDao().getAll();
                    }
                });
    }

    public Observable<Integer> deleteAllColor() {
        return Observable.fromCallable(
                new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        return instance.selectiveTaskDao().deleteAll();
                    }
                });
    }

}