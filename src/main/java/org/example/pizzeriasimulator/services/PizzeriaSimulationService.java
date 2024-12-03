package org.example.pizzeriasimulator.services;

import jakarta.annotation.PreDestroy;
import lombok.Getter;
import org.example.pizzeriasimulator.models.cookers.Cooker;
import org.example.pizzeriasimulator.models.customer.Customer;
import org.example.pizzeriasimulator.models.order.Order;
import org.example.pizzeriasimulator.models.pizza.Pizza;
import org.example.pizzeriasimulator.models.pizza.PizzaPreparationStages;
import org.example.pizzeriasimulator.models.simulation.Simulation;
import org.example.pizzeriasimulator.models.dtos.StartSimulationDto;
import org.example.pizzeriasimulator.services.cookers.generation.CookerGenerator;
import org.example.pizzeriasimulator.services.customers.generation.CustomerGenerator;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class PizzeriaSimulationService {
    @Getter
    private final HashMap<String, Simulation> simulations = new HashMap<>();
    private final CustomerGenerator customerGenerator = new CustomerGenerator();
    private final CookerGenerator cookerGenerator = new CookerGenerator();

    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);

    public String runSimulation(StartSimulationDto startSimulationDto) {
        Simulation simulation = createSimulation(startSimulationDto);
        String id = generateSimulationId();
        simulations.put(id, simulation);

        List<Cooker> cookers = cookerGenerator.generateCookers(startSimulationDto.getCookersCount());
        simulation.addCookers(cookers);

        // Запускаємо генерацію нових клієнтів
        startCustomerGeneration(simulation);

        // Запускаємо обробку піц кухарями
        startCookerProcessing(simulation);

        return id;
    }

    private Simulation createSimulation(StartSimulationDto startGenerationDto) {
        return new Simulation(startGenerationDto);
    }

    private String generateSimulationId() {
        return "SIMULATION-" + UUID.randomUUID();
    }

    private void startCustomerGeneration(Simulation simulation) {
        executor.scheduleAtFixedRate(() -> {
            List<Customer> customers = customerGenerator
                    .generateCustomers(simulation
                            .getGenerationConfig()
                            .getCustomerGenerationStrategy());

            customers.forEach(customer -> {
                simulation.addCustomer(customer);
                customer.getOrder().getPizzas().forEach(simulation::addPizza);
                subscribeOnCustomerPizzas(customer); // ?
            });
        }, 0, 5, TimeUnit.SECONDS); // Генеруємо клієнтів кожні 5 секунд
    }

    private void startCookerProcessing(Simulation simulation) {
        simulation.getCookers().forEach(cooker -> executor.submit(() -> {
            while (true) {
                try {
                    Optional<Pizza> pizzaOpt = simulation.getCustomers().stream()
                            .flatMap(customer -> customer.getOrder().getPizzas().stream())
                            .filter(pizza -> cooker.canHandle(pizza) && pizza.getPreparationStage() != PizzaPreparationStages.DONE)
                            .findFirst();

                    if (pizzaOpt.isPresent()) {
                        Pizza pizza = pizzaOpt.get();
                        cooker.processPizza(pizza);

                        simulation.getCustomers().stream()
                                .map(Customer::getOrder)
                                .forEach(Order::checkAndUpdatePreparationStage);
                    } else {
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }));
    }

    @PreDestroy
    public void shutdownExecutor() {
        executor.shutdown();
    }

    private void subscribeOnCustomerPizzas(Customer customer) {
        customer.getOrder().getPizzas().forEach(pizza -> {
            pizza.addObserver((previousPizza, currentPizza, reason) -> {
                System.out.println("Pizza " + previousPizza.getType() + " changed stage from "
                        + previousPizza.getPreparationStage() + " to " + currentPizza.getPreparationStage() +
                        ". Reason: " + reason);
            });
        });
    }
}
