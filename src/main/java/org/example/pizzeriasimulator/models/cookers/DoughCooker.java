package org.example.pizzeriasimulator.models.cookers;

import org.example.pizzeriasimulator.models.pizza.Pizza;
import org.example.pizzeriasimulator.models.pizza.PizzaPreparationStages;

public class DoughCooker extends Cooker {
    public DoughCooker(String name) {
        super(name);
        workingStrategy = "Dough cooker";
    }

    @Override
    public Boolean canHandle(Pizza pizza) {
        return (pizza.getPreparationStage() == PizzaPreparationStages.NONE)
                && isAvailable;
    }

    @Override
    public void processPizza(Pizza pizza) {
        isAvailable = false;
        pizza.changePreparationStage(PizzaPreparationStages.DOUGH,
                String.format("Dough started by %s", name));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        pizza.changePreparationStage(PizzaPreparationStages.DOUGH_COMPLETED,
                String.format("Dough completed by %s", name));
        isAvailable = true;
    }
}
