package org.example.pizzeriasimulator.models.pizza;

@FunctionalInterface
public interface PizzaPreparationStageChangedCallback {
    void handle(Pizza previousPizza, Pizza currentPizza, String reason);
}
