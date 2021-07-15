package com.simulation.simulationecatalog.data.components.roomdatabases.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class SimulationTask implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "header_name")
    private String headerName;

    @ColumnInfo(name = "description_info")
    private String descriptionInfo;

    @ColumnInfo(name = "category_type")
    private String categoryType;

    @ColumnInfo(name = "original_image")
    private Integer originalImage;

    @ColumnInfo(name = "simulation_image")
    private Integer simulationImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public String getDescriptionInfo() {
        return descriptionInfo;
    }

    public void setDescriptionInfo(String descriptionInfo) {
        this.descriptionInfo = descriptionInfo;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public Integer getOriginalImage() {
        return originalImage;
    }

    public void setOriginalImage(Integer originalImage) {
        this.originalImage = originalImage;
    }

    public Integer getSimulationImage() {
        return simulationImage;
    }

    public void setSimulationImage(Integer simulationImage) {
        this.simulationImage = simulationImage;
    }
}