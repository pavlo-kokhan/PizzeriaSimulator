package org.example.pizzeriasimulator.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.pizzeriasimulator.services.customers.generation.CustomerGenerationStrategies;

@Getter
@AllArgsConstructor
public class StartSimulationDto {
    private final Integer cookersCount;
    private final Integer cashRegistersCount;
    private final Integer pizzaMinPreparationTime;
    private final CustomerGenerationStrategies customerGenerationStrategy;
}
