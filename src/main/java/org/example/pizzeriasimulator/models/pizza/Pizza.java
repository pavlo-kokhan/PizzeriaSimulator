package org.example.pizzeriasimulator.models.pizza;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Getter
@Setter
public class Pizza {
    private PizzaTypes type;
    private PizzaPreparationStages preparationStage;
    private List<PizzaPreparationStageChangedCallback> callbacks = new ArrayList<>();
    private boolean isBeingProcessed = false;

    private final ReentrantLock lock = new ReentrantLock();

    public Pizza(PizzaTypes type) {
        this.type = type;
        preparationStage = PizzaPreparationStages.NONE;
    }

    public boolean tryLock() {
        return lock.tryLock();
    }

    public void unlock() {
        lock.unlock();
    }

    public synchronized boolean startProcessing() {
        if (isBeingProcessed) return false;
        isBeingProcessed = true;
        return true;
    }

    public synchronized void finishProcessing() {
        isBeingProcessed = false;
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
