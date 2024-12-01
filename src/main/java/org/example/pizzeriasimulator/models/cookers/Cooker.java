package org.example.pizzeriasimulator.models.cookers;

import lombok.Getter;
import lombok.Setter;
import org.example.pizzeriasimulator.models.pizza.Pizza;

@Getter
@Setter
public abstract class Cooker {
    protected Cooker nextCooker;
    protected Boolean isAvailable;

    public Cooker() {}

    public Cooker(Cooker nextCooker, Boolean isAvailable) {
        this.nextCooker = nextCooker;
        this.isAvailable = isAvailable;
    }

    public abstract void processPizza(Pizza pizza);
    public abstract Boolean canHandle(Pizza pizza);
    public abstract void handle();
}
