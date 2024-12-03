package org.example.pizzeriasimulator.models.pizza;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PizzaLog {
    private Pizza previoudPizza;
    private Pizza currentPizza;
    private String initiation;
}
