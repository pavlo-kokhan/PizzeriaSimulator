package org.example.pizzeriasimulator.models.simulation;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.pizzeriasimulator.models.cookers.Cooker;
import org.example.pizzeriasimulator.models.customer.Customer;
import org.example.pizzeriasimulator.models.dtos.StartSimulationDto;
import org.example.pizzeriasimulator.models.order.Order;
import org.example.pizzeriasimulator.models.pizza.Pizza;
import org.example.pizzeriasimulator.models.pizza.PizzaLog;
import org.example.pizzeriasimulator.models.pizza.PizzaPreparationStages;
import org.example.pizzeriasimulator.services.loggers.HistoryPizzaLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Getter
public class Simulation {
    @NotNull
    private final StartSimulationDto generationConfig;

    private final List<Customer> customers = new ArrayList<>();
    private final List<Cooker> cookers;
    private final HistoryPizzaLogger pizzaLogger = new HistoryPizzaLogger();

    public Simulation(StartSimulationDto generationConfig, List<Cooker> cookers) {
        this.generationConfig = generationConfig;
        this.cookers = cookers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        customer.getOrder().getPizzas().forEach(pizzaLogger::subscribeOnPizza);
    }

    public Optional<Pizza> getNotPreparedPizza() {
        synchronized (customers) {
            return customers.stream()
                    .flatMap(c -> c.getOrder().getPizzas().stream())
                    .filter(p -> p.getPreparationStage() != PizzaPreparationStages.DONE)
                    .findFirst();
        }
    }

    public synchronized List<Customer> getCustomers() {
        return customers;
    }

    public List<PizzaLog> getPizzaLogs() {
        return pizzaLogger.getPizzaLogs();
    }
}
