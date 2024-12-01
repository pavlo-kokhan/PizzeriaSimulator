package org.example.pizzeriasimulator.models.pizza;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Pizza {
    @Getter
    @Setter
    private PizzaPreparationStages preparationStage;

    private List<PizzaPreparationStageChangedCallback> callbacks;

    public Pizza() {}

    public Pizza(PizzaPreparationStages preparationStage,
                 List<PizzaPreparationStageChangedCallback> callbacks) {
        this.preparationStage = preparationStage;
        this.callbacks = callbacks;
    }

    public void notifyObservers() {
        // todo
    }
}
