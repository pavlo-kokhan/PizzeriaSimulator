package org.example.pizzeriasimulator.models.cookers;

import lombok.*;
import org.example.pizzeriasimulator.models.pizza.Pizza;

import javax.management.ConstructorParameters;

@Getter
public abstract class Cooker {
    @Setter
    protected Cooker nextCooker;
    protected String name;
    protected Boolean isAvailable = true;

    protected String workingStrategy;

    public Cooker(String name) {
        this.name = name;
    }

    public abstract Boolean canHandle(Pizza pizza);
    public abstract void processPizzaCore(Pizza pizza);

    public void processPizza(Pizza pizza) {
        if (canHandle(pizza)) {
            try {
                isAvailable = false;
                processPizzaCore(pizza);

            } finally {
                isAvailable = true;
            }
        }

        if (nextCooker != null) {
            nextCooker.processPizza(pizza);
        }
    }

    protected void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
