package org.example.pizzeriasimulator.models.dtos;

import lombok.Getter;

@Getter
public class StartSimulationResponseDto {
    private final String simulationId;

    public StartSimulationResponseDto(String simulationId) {
        this.simulationId = simulationId;
    }
}
