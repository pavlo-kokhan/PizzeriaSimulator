package org.example.pizzeriasimulator.models.simulation;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.pizzeriasimulator.models.cookers.Cooker;
import org.example.pizzeriasimulator.models.customer.Customer;
import org.example.pizzeriasimulator.models.dtos.StartSimulationDto;
import org.example.pizzeriasimulator.models.pizza.PizzaLog;
import org.example.pizzeriasimulator.services.PizzaLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public class Simulation {
    @NotNull
    private final StartSimulationDto generationConfig;

    private List<Customer> customers = new ArrayList<>();
    private List<Cooker> cookers = new ArrayList<>();
    private PizzaLogger pizzaLogger = new PizzaLogger();

    public void addCustomer(Customer customer) {
        // todo
        customers.add(customer);
    }

    public void addCustomers(List<Customer> customers) {
        // todo
        this.customers.addAll(customers);
    }

    public void addCooker(Cooker cooker) {
        // todo
        cookers.add(cooker);
    }

    public void addCookers(List<Cooker> cookers) {
        // todo
        this.cookers.addAll(cookers);
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
