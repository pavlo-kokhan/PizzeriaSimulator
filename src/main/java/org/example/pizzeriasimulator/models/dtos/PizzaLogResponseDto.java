package org.example.pizzeriasimulator.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.pizzeriasimulator.models.pizza.PizzaLog;

@Getter
@AllArgsConstructor
public class PizzaLogResponseDto {
    private final PizzaResponseDto previousPizza;
    private final PizzaResponseDto currentPizza;
    private final String initiation;

    public PizzaLogResponseDto(PizzaLog pizzaLog) {
        previousPizza = new PizzaResponseDto(pizzaLog.getPrevioudPizza());
        currentPizza = new PizzaResponseDto(pizzaLog.getCurrentPizza());
        this.initiation = pizzaLog.getInitiation();
    }
}
