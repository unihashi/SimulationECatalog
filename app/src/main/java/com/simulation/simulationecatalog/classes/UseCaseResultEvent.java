package com.simulation.simulationecatalog.classes;

public interface UseCaseResultEvent<T>{

    void onResult(T result);

    void onError(Throwable t, String message);

}
