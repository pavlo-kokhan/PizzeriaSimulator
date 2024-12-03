package org.example.pizzeriasimulator.models.simulation;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.pizzeriasimulator.models.cookers.Cooker;
import org.example.pizzeriasimulator.models.customer.Customer;
import org.example.pizzeriasimulator.models.dtos.StartSimulationDto;
import org.example.pizzeriasimulator.models.order.Order;
import org.example.pizzeriasimulator.models.order.OrderPreparationStages;
import org.example.pizzeriasimulator.models.pizza.Pizza;
import org.example.pizzeriasimulator.models.pizza.PizzaLog;
import org.example.pizzeriasimulator.models.pizza.PizzaPreparationStages;
import org.example.pizzeriasimulator.services.PizzaLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Getter
@RequiredArgsConstructor
public class Simulation {
    @NotNull
    private final StartSimulationDto generationConfig;

    private final List<Customer> customers = new ArrayList<>();
    private final List<Cooker> cookers = new ArrayList<>();
    private final PizzaLogger pizzaLogger = new PizzaLogger();

    private final BlockingQueue<Pizza> pizzaQueue = new LinkedBlockingQueue<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addCustomers(List<Customer> customers) {
        this.customers.addAll(customers);
    }

    public void addCooker(Cooker cooker) {
        cookers.add(cooker);
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

    public void startSimulation() {
        customers.forEach(customer -> {
            Order order = customer.getOrder();
            System.out.printf("Processing order for customer: %s%n", customer.getName());

            // Запускаємо обробку замовлення у фоновому потоці
            new Thread(() -> processOrder(order)).start();
        });
    }

    public void processOrder(Order order) {
        order.setPreparationStage(OrderPreparationStages.COOKING);

        for (Pizza pizza : order.getPizzas()) {
            processPizza(pizza); // Передаємо піцу на обробку
        }

        order.setPreparationStage(OrderPreparationStages.DONE);
        System.out.printf("Order completed: %s%n", order);
    }

    public void processPizza(Pizza pizza) {
        while (pizza.getPreparationStage() != PizzaPreparationStages.DONE) {
            Optional<Cooker> availableCooker = getAvailableCooker(pizza);

            if (availableCooker.isPresent()) {
                Cooker cooker = availableCooker.get();
                System.out.printf("Pizza %s being processed by %s%n", pizza, cooker.getName());

                // Викликаємо обробку піци у фоновому потоці
                new Thread(() -> cooker.processPizza(pizza)).start();

                // Зачекаємо, щоб дати час для обробки
                try {
                    Thread.sleep(500); // Перевіряємо кожну секунду
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                System.out.printf("No available cookers for pizza %s%n", pizza);
                try {
                    Thread.sleep(500); // Очікуємо, поки з'являться доступні кухарі
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        System.out.printf("Pizza %s is DONE%n", pizza);
    }

    private Optional<Cooker> getAvailableCooker(Pizza pizza) {
        return cookers.stream()
                .filter(Cooker::getIsAvailable)
                .filter(cooker -> cooker.canHandle(pizza))
                .findFirst();
    }
}
