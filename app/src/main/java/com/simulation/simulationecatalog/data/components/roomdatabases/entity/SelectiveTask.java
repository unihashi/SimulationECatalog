package com.simulation.simulationecatalog.data.components.roomdatabases.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class SelectiveTask implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "color_name")
    private String colorName;

    @ColumnInfo(name = "source_color")
    private Integer sourceColor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public Integer getSourceColor() {
        return sourceColor;
    }

    public void setSourceColor(Integer sourceColor) {
        this.sourceColor = sourceColor;
    }
}
