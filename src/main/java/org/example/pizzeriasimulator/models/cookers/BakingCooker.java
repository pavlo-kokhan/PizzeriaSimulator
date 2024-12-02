package org.example.pizzeriasimulator.models.cookers;

import org.example.pizzeriasimulator.models.pizza.Pizza;

public class BakingCooker extends Cooker {
    public BakingCooker(String name) {
        super(name);
        workingStrategy = "Baking cooker";
    }

    @Override
    public void processPizza(Pizza pizza) {

    }

    @Override
    public Boolean canHandle(Pizza pizza) {
        return false;
    }

    @Override
    public void handle() {

    }
}
