package org.example.pizzeriasimulator.models.cookers;

import lombok.*;
import org.example.pizzeriasimulator.models.pizza.Pizza;

import javax.management.ConstructorParameters;

@Getter
public abstract class Cooker {
    @Setter
    protected String name;
    @Setter
    protected Cooker nextCooker;
    @Setter
    protected Boolean isAvailable;

    protected String workingStrategy;

    public Cooker(String name) {
        this.name = name;
    }

    public abstract void processPizza(Pizza pizza);
    public abstract Boolean canHandle(Pizza pizza);
    public abstract void handle();
}
