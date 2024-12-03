package org.example.pizzeriasimulator.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.pizzeriasimulator.models.cookers.Cooker;

@Getter
@AllArgsConstructor
public class CookerResponseDto {
    private final String name;
    private final Boolean isAvailable;
    private final String workingStrategy;

    public CookerResponseDto(Cooker cooker) {
        this.name = cooker.getName();
        this.isAvailable = cooker.getIsAvailable();
        this.workingStrategy = cooker.getWorkingStrategy();
    }
}
