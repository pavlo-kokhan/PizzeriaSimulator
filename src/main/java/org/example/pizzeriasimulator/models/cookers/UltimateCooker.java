package org.example.pizzeriasimulator.models.cookers;

import org.example.pizzeriasimulator.models.pizza.Pizza;

public class UltimateCooker extends Cooker {
    public UltimateCooker(String name) {
        super(name);
        workingStrategy = "Ultimate cooker";
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
