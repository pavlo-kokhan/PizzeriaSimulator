package org.example.pizzeriasimulator.services.customers.generation;

import org.example.pizzeriasimulator.models.customer.Customer;

import java.util.List;

public class CustomerGenerator {
    private final CustomerGenerationStrategyFactory factory;

    public CustomerGenerator() {
        factory = new CustomerGenerationStrategyFactory();
    }

    public List<Customer> generateCustomers(CustomerGenerationStrategies strategyType) {
        CustomerGenerationStrategy strategy = factory.getCustomerGenerationStrategy(strategyType);
        return strategy.generateCustomers();
    }
}