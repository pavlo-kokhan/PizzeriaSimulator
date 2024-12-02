package org.example.pizzeriasimulator.models.pizza;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Pizza {
    private PizzaTypes type;
    private PizzaPreparationStages preparationStage;
    private List<PizzaPreparationStageChangedCallback> callbacks = new ArrayList<>();

    public Pizza(PizzaTypes type) {
        this.type = type;
        preparationStage = PizzaPreparationStages.NONE;
    }

    public void addObserver(PizzaPreparationStageChangedCallback callback) {
        callbacks.add(callback);
    }

    public void removeObserver(PizzaPreparationStageChangedCallback callback) {
        callbacks.remove(callback);
    }

    public void changePreparationStage(PizzaPreparationStages newStage, String reason) {
        Pizza previousPizza = this.copy();
        this.preparationStage = newStage;
        notifyObservers(previousPizza, this, reason);
    }

    private Pizza copy() {
        Pizza copy = new Pizza(this.type);
        copy.setPreparationStage(this.preparationStage);
        return copy;
    }

    private void notifyObservers(Pizza previousPizza, Pizza currentPizza, String reason) {
        for (PizzaPreparationStageChangedCallback callback : callbacks) {
            callback.handle(previousPizza, currentPizza, reason);
        }
    }
}
