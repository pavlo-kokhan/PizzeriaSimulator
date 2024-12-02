package org.example.pizzeriasimulator.models.cookers;

import org.example.pizzeriasimulator.models.pizza.Pizza;

public class DoughCooker extends Cooker {
    public DoughCooker(String name) {
        super(name);
        workingStrategy = "Dough cooker";
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
