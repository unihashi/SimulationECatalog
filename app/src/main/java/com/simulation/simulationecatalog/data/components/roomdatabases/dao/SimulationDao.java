package com.simulation.simulationecatalog.data.components.roomdatabases.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.simulation.simulationecatalog.data.components.roomdatabases.entity.SimulationTask;

import java.util.List;

@Dao
public interface SimulationDao {

    @Query("SELECT * FROM SimulationTask")
    List<SimulationTask> getAll();

    @Query("DELETE FROM SimulationTask")
    int deleteAll();

    @Insert
    long insert(SimulationTask simulationTask);

    @Delete
    int delete(SimulationTask simulationTask);

    @Update
    int update(SimulationTask simulationTask);

}
