package org.example.pizzeriasimulator.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.pizzeriasimulator.models.order.Order;
import org.example.pizzeriasimulator.models.order.OrderPreparationStages;
import org.example.pizzeriasimulator.models.pizza.Pizza;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderResponseDto {
    private final PizzasResponseDto pizzas;
    private final OrderPreparationStages preparationStage;

    public OrderResponseDto(Order order) {
        List<Pizza> orderPizzas = order.getPizzas();
        List<PizzaResponseDto> orderPizzaResponseDtos = new ArrayList<>();
        orderPizzas.forEach(pizza -> {
            orderPizzaResponseDtos.add(new PizzaResponseDto(pizza));
        });
        this.pizzas = new PizzasResponseDto(orderPizzaResponseDtos);
        this.preparationStage = order.getPreparationStage();
    }
}
