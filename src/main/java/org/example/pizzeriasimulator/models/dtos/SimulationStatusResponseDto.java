package org.example.pizzeriasimulator.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SimulationStatusResponseDto {
    private final CustomersResponseDto customers;
    private final CookersResponseDto cookers;
    private final PizzaLogsResponseDto pizzaLogs;
}
