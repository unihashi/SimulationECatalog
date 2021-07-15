package com.simulation.simulationecatalog.presentations.presenters;

import com.simulation.simulationecatalog.interfaces.SlideInterface;

public class SlidePresenter implements SlideInterface.Presenter {

    private SlideInterface.View view;

    public SlidePresenter(SlideInterface.View view) {
        this.view = view;
    }
}
