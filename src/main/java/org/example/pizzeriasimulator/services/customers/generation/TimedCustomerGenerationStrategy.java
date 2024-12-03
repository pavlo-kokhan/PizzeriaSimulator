package org.example.pizzeriasimulator.services.customers.generation;

import org.example.pizzeriasimulator.models.customer.Customer;
import org.example.pizzeriasimulator.models.order.Order;
import org.example.pizzeriasimulator.models.pizza.Pizza;
import org.example.pizzeriasimulator.models.pizza.PizzaTypes;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TimedCustomerGenerationStrategy implements CustomerGenerationStrategy {
    private final Integer minCustomersCount;
    private final Integer averageCustomersCount;
    private final Integer maxCustomersCount;

    public TimedCustomerGenerationStrategy(Integer minCustomersCount,
                                           Integer averageCustomersCount,
                                           Integer maxCustomersCount) {
        this.minCustomersCount = minCustomersCount;
        this.averageCustomersCount = averageCustomersCount;
        this.maxCustomersCount = maxCustomersCount;
    }

    @Override
    public List<Customer> generateCustomers() {
        List<Customer> customers = new ArrayList<>();
        Random random = new Random();
        int count = calculateCustomersCount();

        for (int i = 0; i < count; i++) {
            List<Pizza> pizzas = new ArrayList<>();
            int pizzasCount = random.nextInt(5) + 1;

            for (int j = 0; j < pizzasCount; j++) {
                pizzas.add(new Pizza(PizzaTypes.values()[random.nextInt(PizzaTypes.values().length)]));
            }

            customers.add(new Customer(RandomNamesProvider.getRandomName(), new Order(pizzas)));
        }

        return customers;
    }

    private Integer calculateCustomersCount() {
        LocalTime currentTime = LocalTime.now();

        if (isWithinTimeRange(currentTime, LocalTime.of(9, 0), LocalTime.of(12, 0))) {
            return minCustomersCount;
        } else if (isWithinTimeRange(currentTime, LocalTime.of(12, 0), LocalTime.of(13, 0))) {
            return maxCustomersCount;
        } else if (isWithinTimeRange(currentTime, LocalTime.of(13, 0), LocalTime.of(18, 0))) {
            return averageCustomersCount;
        } else if (isWithinTimeRange(currentTime, LocalTime.of(18, 0), LocalTime.of(22, 0))) {
            return maxCustomersCount;
        } else {
            return 0;
        }
    }

    private boolean isWithinTimeRange(LocalTime currentTime, LocalTime start, LocalTime end) {
        return !currentTime.isBefore(start) && !currentTime.isAfter(end);
    }
}
