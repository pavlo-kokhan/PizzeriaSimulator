package org.example.pizzeriasimulator.services.loggers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.pizzeriasimulator.models.pizza.Pizza;
import org.example.pizzeriasimulator.models.pizza.PizzaLog;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class HistoryPizzaLogger extends PizzaLogger {
    private final List<PizzaLog> pizzaLogs = new ArrayList<>();

    public void subscribeOnPizza(Pizza pizza) {
        pizza.addObserver(((previousPizza, currentPizza, reason) -> {
            pizzaLogs.add(new PizzaLog(previousPizza, currentPizza, reason));
        }));
    }
}
