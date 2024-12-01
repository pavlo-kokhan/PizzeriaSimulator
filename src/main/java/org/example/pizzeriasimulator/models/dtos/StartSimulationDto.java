package org.example.pizzeriasimulator.models.dtos;

import lombok.Getter;
import org.example.pizzeriasimulator.services.customers.generation.CustomerGenerationStrategies;

@Getter
public class StartSimulationDto {
    private final Integer numberOfCooks;
    private final Integer numberOfCashRegisters;
    private final Integer pizzaMinPreparationTime;
    private final CustomerGenerationStrategies customerGenerationStrategy;

    public StartSimulationDto(Integer numberOfCooks,
                              Integer numberOfCashRegisters,
                              Integer pizzaMinPreparationTime,
                              CustomerGenerationStrategies customerGenerationStrategy) {
        this.numberOfCooks = numberOfCooks;
        this.numberOfCashRegisters = numberOfCashRegisters;
        this.pizzaMinPreparationTime = pizzaMinPreparationTime;
        this.customerGenerationStrategy = customerGenerationStrategy;
    }
}
