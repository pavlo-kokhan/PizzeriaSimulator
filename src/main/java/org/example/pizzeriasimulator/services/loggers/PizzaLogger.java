package org.example.pizzeriasimulator.services.loggers;

import org.example.pizzeriasimulator.models.pizza.Pizza;
import org.example.pizzeriasimulator.models.pizza.PizzaLog;

import java.util.ArrayList;
import java.util.List;

public abstract class PizzaLogger {
    protected final List<PizzaLog> pizzaLogs = new ArrayList<>();

    public abstract void subscribeOnPizza(Pizza pizza);
}
