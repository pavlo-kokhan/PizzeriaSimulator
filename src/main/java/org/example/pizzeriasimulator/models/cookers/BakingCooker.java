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
        return (pizza.getPreparationStage() == PizzaPreparationStages.DOUGH_COMPLETED)
                && isAvailable;
    }

    @Override
    public void processPizzaCore(Pizza pizza) {
        if (pizza.getPreparationStage() == PizzaPreparationStages.DONE) {
            return;
        }

        sleep(1000);

        pizza.changePreparationStage(PizzaPreparationStages.BAKING,
                String.format("Baking started by %s", name));

        sleep(3000);

        pizza.changePreparationStage(PizzaPreparationStages.DONE,
                String.format("Baking completed by %s", name));
    }
}
