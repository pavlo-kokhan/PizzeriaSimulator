package org.example.pizzeriasimulator.models.pizza;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PizzaLog {
    private Pizza previoudPizza;
    private Pizza currentPizza;
    private String initiation;
}
