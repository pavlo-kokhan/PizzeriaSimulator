package org.example.pizzeriasimulator.models.cookers;

import org.example.pizzeriasimulator.models.pizza.Pizza;
import org.example.pizzeriasimulator.models.pizza.PizzaPreparationStages;

public class UltimateCooker extends Cooker {
    public UltimateCooker(String name) {
        super(name);
        workingStrategy = "Ultimate cooker";
    }

    @Override
    public Boolean canHandle(Pizza pizza) {
        return (pizza.getPreparationStage() != PizzaPreparationStages.DONE)
                && isAvailable;
    }

    @Override
    public void processPizza(Pizza pizza) {
        isAvailable = false;

        switch (pizza.getPreparationStage()) {
            case NONE -> {
                pizza.changePreparationStage(PizzaPreparationStages.DOUGH,
                        String.format("Dough started by %s", name));
                sleep(2000);
                pizza.changePreparationStage(PizzaPreparationStages.DOUGH_COMPLETED,
                        String.format("Dough completed by %s", name));

                pizza.changePreparationStage(PizzaPreparationStages.BAKING,
                        String.format("Baking started by %s", name));
                sleep(2000);
                pizza.changePreparationStage(PizzaPreparationStages.DONE,
                        String.format("Baking completed by %s", name));
            }
            case DOUGH -> {
                pizza.changePreparationStage(PizzaPreparationStages.BAKING,
                        String.format("Baking started by %s", name));
                sleep(2000);
                pizza.changePreparationStage(PizzaPreparationStages.DONE,
                        String.format("Baking completed by %s", name));
            }
            default -> throw new IllegalStateException(
                    String.format("Unexpected stage %s for pizza in %s", pizza.getPreparationStage(), name));
        }

        isAvailable = true;
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
