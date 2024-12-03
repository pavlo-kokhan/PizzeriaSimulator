package org.example.pizzeriasimulator.models.cookers;

import org.example.pizzeriasimulator.models.pizza.Pizza;
import org.example.pizzeriasimulator.models.pizza.PizzaPreparationStages;

public class BakingCooker extends Cooker {
    public BakingCooker(String name) {
        super(name);
        workingStrategy = "Baking cooker";
    }

    @Override
    public Boolean canHandle(Pizza pizza) {
        return (pizza.getPreparationStage() == PizzaPreparationStages.DOUGH)
                && isAvailable;
    }

    @Override
    public void processPizzaCore(Pizza pizza) {
        isAvailable = false;
        pizza.changePreparationStage(PizzaPreparationStages.BAKING,
                String.format("Baking started by %s", name));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        pizza.changePreparationStage(PizzaPreparationStages.DONE,
                String.format("Baking completed by %s", name));
        isAvailable = true;
    }
}
