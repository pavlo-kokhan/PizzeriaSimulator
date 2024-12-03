package org.example.pizzeriasimulator.services.loggers;

import org.example.pizzeriasimulator.models.pizza.Pizza;
import org.example.pizzeriasimulator.models.pizza.PizzaLog;

public class ConsolePizzaLogger extends PizzaLogger {
    @Override
    public void subscribeOnPizza(Pizza pizza) {
        pizza.addObserver(((previousPizza, currentPizza, reason) -> {
            pizzaLogs.add(new PizzaLog(previousPizza, currentPizza, reason));
            System.out.printf("Previous pizza: %s %s, Current pizza: %s %s, Reason: %s%n",
                    previousPizza.getType(), previousPizza.getPreparationStage(),
                    currentPizza.getType(), currentPizza.getPreparationStage(), reason);
        }));
    }
}
