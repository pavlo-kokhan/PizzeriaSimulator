package org.example.pizzeriasimulator.models.pizza;

public interface PizzaPreparationStageChangedCallback {
    void handle(Pizza previousPizza, Pizza currentPizza, String reason);
}
