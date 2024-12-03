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
import org.example.pizzeriasimulator.services.loggers.HistoryPizzaLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Getter
@RequiredArgsConstructor
public class Simulation {
    @NotNull
    private final StartSimulationDto generationConfig;

    private final List<Customer> customers = new ArrayList<>();
    private final List<Cooker> cookers = new ArrayList<>();
    private final HistoryPizzaLogger pizzaLogger = new HistoryPizzaLogger();

    private final BlockingQueue<Pizza> pizzaQueue = new LinkedBlockingQueue<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
        customer.getOrder().getPizzas().forEach(pizzaLogger::subscribeOnPizza);
    }

    public void addCookers(List<Cooker> cookers) {
        this.cookers.addAll(cookers);
    }

    public void addPizza(Pizza pizza) {
        pizzaQueue.offer(pizza);
    }

    public Pizza getNextPizza() throws InterruptedException {
        return pizzaQueue.take();
    }

    public List<PizzaLog> getPizzaLogs() {
        return pizzaLogger.getPizzaLogs();
    }
}
