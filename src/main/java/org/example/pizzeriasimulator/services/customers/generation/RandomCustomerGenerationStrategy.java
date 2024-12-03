package org.example.pizzeriasimulator.services.customers.generation;

import org.example.pizzeriasimulator.models.customer.Customer;
import org.example.pizzeriasimulator.models.order.Order;
import org.example.pizzeriasimulator.models.pizza.Pizza;
import org.example.pizzeriasimulator.models.pizza.PizzaTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomCustomerGenerationStrategy implements CustomerGenerationStrategy {
    private final Integer minCustomersCount;
    private final Integer maxCustomersCount;

    public RandomCustomerGenerationStrategy(Integer minCustomersCount,
                                            Integer maxCustomersCount) {
        this.minCustomersCount = minCustomersCount;
        this.maxCustomersCount = maxCustomersCount;
    }

    @Override
    public List<Customer> generateCustomers() {
        List<Customer> customers = new ArrayList<>();
        Random random = new Random();
        int count = random.nextInt(maxCustomersCount - minCustomersCount + 1) + minCustomersCount;
        List<Pizza> pizzas = new ArrayList<>();

        for (int i = 0; i < random.nextInt(5) + 1; i++) {
            pizzas.add(new Pizza(PizzaTypes.values()[random.nextInt(PizzaTypes.values().length)]));
        }

        for (int i = 0; i < count; i++) {
            customers.add(new Customer(RandomNamesProvider.getRandomName(), new Order(pizzas)));
        }

        return customers;
    }
}
