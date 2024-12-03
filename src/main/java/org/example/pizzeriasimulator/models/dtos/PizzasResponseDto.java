package org.example.pizzeriasimulator.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class PizzasResponseDto {
    private final List<PizzaResponseDto> pizzas;
}
