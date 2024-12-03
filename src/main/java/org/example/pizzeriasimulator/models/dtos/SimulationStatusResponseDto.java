package org.example.pizzeriasimulator.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.pizzeriasimulator.models.cookers.Cooker;
import org.example.pizzeriasimulator.models.customer.Customer;
import org.example.pizzeriasimulator.models.pizza.PizzaLog;

import java.util.List;

@Getter
@AllArgsConstructor
public class SimulationStatusResponseDto {
    private String simulationId;
    private List<Customer> customers;
    private List<Cooker> cookers;
    private List<PizzaLog> pizzaLogs;
}
