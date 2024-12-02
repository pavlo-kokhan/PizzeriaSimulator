package org.example.pizzeriasimulator.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.pizzeriasimulator.models.cookers.Cooker;

import java.util.List;

@Getter
@AllArgsConstructor
public class CookersResponseDto {
    private List<Cooker> cookers;
}
