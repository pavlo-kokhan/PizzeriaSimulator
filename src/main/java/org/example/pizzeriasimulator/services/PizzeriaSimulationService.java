package org.example.pizzeriasimulator.services;

import jakarta.annotation.PreDestroy;
import lombok.Getter;
import org.example.pizzeriasimulator.models.cookers.Cooker;
import org.example.pizzeriasimulator.models.customer.Customer;
import org.example.pizzeriasimulator.models.order.Order;
import org.example.pizzeriasimulator.models.order.OrderPreparationStages;
import org.example.pizzeriasimulator.models.pizza.Pizza;
import org.example.pizzeriasimulator.models.pizza.PizzaPreparationStages;
import org.example.pizzeriasimulator.models.simulation.Simulation;
import org.example.pizzeriasimulator.models.dtos.StartSimulationDto;
import org.example.pizzeriasimulator.services.cookers.generation.CookerGenerator;
import org.example.pizzeriasimulator.services.customers.generation.CustomerGenerator;
import org.example.pizzeriasimulator.services.loggers.ConsolePizzaLogger;
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

        simulation.startSimulation();

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

            if (simulation.getCustomers().size() + customers.size() <= 20) {
                customers.forEach(customer -> {
                    simulation.addCustomer(customer);
                    customer.getOrder().getPizzas().forEach(simulation::addPizza);
                    subscribeOnCustomerPizzas(customer);
                });
            } else {
                System.out.printf("Restaurant is not available, skipping %d customers%n",
                        customers.size());
            }
        }, 0, 15, TimeUnit.SECONDS);
    }

    private void startCookerProcessing(Simulation simulation) {
        simulation.getCookers().forEach(cooker -> executor.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Pizza pizza = simulation.getNextPizza();
                    cooker.processPizza(pizza);

                    List<Customer> customers = simulation.getCustomers();
                    customers.stream()
                            .map(Customer::getOrder)
                            .forEach(Order::checkAndUpdatePreparationStage);

                    customers.stream()
                            .filter(c -> c != null && c.getOrder().getPreparationStage() == OrderPreparationStages.DONE)
                            .forEach(customers::remove);

                } catch (Exception e) {
                    if (e instanceof InterruptedException) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
        }));
    }

    private void subscribeOnCustomerPizzas(Customer customer) {
        ConsolePizzaLogger consolePizzaLogger = new ConsolePizzaLogger();
        customer.getOrder().getPizzas().forEach(consolePizzaLogger::subscribeOnPizza);
    }

    @PreDestroy
    public void shutdownExecutor() {
        executor.shutdown();
    }
}
