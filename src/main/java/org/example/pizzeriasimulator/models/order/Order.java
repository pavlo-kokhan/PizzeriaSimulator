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
        // Піц немає, або всі готові
        if (pizzas.isEmpty() || pizzas.stream()
                .allMatch(pizza -> pizza.getPreparationStage() == PizzaPreparationStages.DONE)) {
            preparationStage = OrderPreparationStages.DONE;
            return;
        }

        // Всі піци ще не почали готуватися
        if (pizzas.stream()
                .allMatch(pizza -> pizza.getPreparationStage() == PizzaPreparationStages.NONE)) {
            preparationStage = OrderPreparationStages.SUBMITTED_TO_COOK;
            return;
        }

        // Якщо хоча б одна піца ще не готова
        if (pizzas.stream()
                .anyMatch(pizza -> pizza.getPreparationStage() != PizzaPreparationStages.DONE)) {
            preparationStage = OrderPreparationStages.COOKING;
            return;
        }

        preparationStage = OrderPreparationStages.DONE;
    }
}
