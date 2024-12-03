package org.example.pizzeriasimulator.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.pizzeriasimulator.models.pizza.Pizza;
import org.example.pizzeriasimulator.models.pizza.PizzaPreparationStages;
import org.example.pizzeriasimulator.models.pizza.PizzaTypes;

@Getter
@AllArgsConstructor
public class PizzaResponseDto {
    private final PizzaTypes type;
    private final PizzaPreparationStages preparationStage;

    public PizzaResponseDto(Pizza pizza) {
        this.type = pizza.getType();
        this.preparationStage = pizza.getPreparationStage();
    }
}
