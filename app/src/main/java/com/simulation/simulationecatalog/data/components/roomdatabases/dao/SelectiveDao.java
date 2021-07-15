package com.simulation.simulationecatalog.data.components.roomdatabases.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.simulation.simulationecatalog.data.components.roomdatabases.entity.SelectiveTask;

import java.util.List;

@Dao
public interface SelectiveDao {

    @Query("SELECT * FROM SelectiveTask")
    List<SelectiveTask> getAll();

    @Query("DELETE FROM SelectiveTask")
    int deleteAll();

    @Insert
    long insert(SelectiveTask selectiveTask);

    @Delete
    int delete(SelectiveTask selectiveTask);

    @Update
    int update(SelectiveTask selectiveTask);

}
