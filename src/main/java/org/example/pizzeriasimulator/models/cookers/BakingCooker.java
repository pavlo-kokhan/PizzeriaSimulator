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

        sleep(2000);

        pizza.changePreparationStage(PizzaPreparationStages.BAKING,
                String.format("Baking started by %s", name));

        sleep(4000);

        pizza.changePreparationStage(PizzaPreparationStages.DONE,
                String.format("Baking completed by %s", name));

        isAvailable = true;
    }
}
