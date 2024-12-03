package org.example.pizzeriasimulator.models.order;

import lombok.Getter;
import lombok.Setter;
import org.example.pizzeriasimulator.models.pizza.Pizza;
import org.example.pizzeriasimulator.models.pizza.PizzaPreparationStages;

import java.util.List;

@Getter
public class Order {
    private final List<Pizza> pizzas;

    @Setter
    private OrderPreparationStages preparationStage;

    public Order(List<Pizza> pizzas) {
        this.pizzas = pizzas;
        preparationStage = OrderPreparationStages.CUSTOMER_CREATED;
    }

    public void checkAndUpdatePreparationStage() {
        if (pizzas.stream().allMatch(pizza -> pizza.getPreparationStage() == PizzaPreparationStages.DONE)) {
            preparationStage = OrderPreparationStages.DONE;
        } else if (pizzas.stream().anyMatch(pizza -> (pizza.getPreparationStage() != PizzaPreparationStages.NONE)
                && (pizza.getPreparationStage() != PizzaPreparationStages.DONE))) {
            preparationStage = OrderPreparationStages.COOKING;
        }
    }
}
