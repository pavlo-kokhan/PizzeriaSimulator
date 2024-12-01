package org.example.pizzeriasimulator.services;

import lombok.Getter;
import org.example.pizzeriasimulator.models.pizza.Pizza;
import org.example.pizzeriasimulator.models.pizza.PizzaLog;
import org.example.pizzeriasimulator.models.pizza.PizzaPreparationStageChangedCallback;

import java.util.List;

@Getter
public class PizzaLogger {
    private List<PizzaLog> pizzaLogs;

    public PizzaLogger() {}

    public void subscribeOnPizza(Pizza pizza, PizzaPreparationStageChangedCallback callback) {
        // todo
    }
}
