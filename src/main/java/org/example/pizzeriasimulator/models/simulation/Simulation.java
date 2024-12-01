package org.example.pizzeriasimulator.models.simulation;

import org.example.pizzeriasimulator.models.cookers.Cooker;
import org.example.pizzeriasimulator.models.customer.Customer;
import org.example.pizzeriasimulator.models.dtos.StartSimulationDto;
import org.example.pizzeriasimulator.models.pizza.PizzaLog;
import org.example.pizzeriasimulator.services.PizzaLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Simulation {
    private StartSimulationDto generationConfig;
    private List<Customer> customers = new ArrayList<>();
    private List<Cooker> cookers = new ArrayList<>();
    private PizzaLogger pizzaLogger = new PizzaLogger();

    public Simulation(StartSimulationDto generationConfig) {
        this.generationConfig = generationConfig;
    }

    public void addCustomer(Customer customer) {
        // todo
        customers.add(customer);
    }

    public Optional<Cooker> getAvailableCooker() {
        return cookers.stream()
                .filter(Cooker::getIsAvailable)
                .findFirst();
    }

    public List<PizzaLog> getPizzaLogs() {
        // todo
        return pizzaLogger.getPizzaLogs();
    }
}
