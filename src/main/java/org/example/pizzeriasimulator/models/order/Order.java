package org.example.pizzeriasimulator.models.order;

import lombok.Getter;
import lombok.Setter;
import org.example.pizzeriasimulator.models.pizza.Pizza;

import java.util.List;

@Getter
public class Order {
    private List<Pizza> pizzas;

    @Setter
    private OrderPreparationStages preparationStage;
}
