package org.example.pizzeriasimulator.services.customers.generation;

public class CustomerGenerationStrategyFactory {
    public CustomerGenerationStrategy getCustomerGenerationStrategy(CustomerGenerationStrategies strategy) {
        return switch (strategy) {
            // todo - get customers count from configs?
            case RANDOM -> new RandomCustomerGenerationStrategy(1, 5);
            case TIMED -> new TimedCustomerGenerationStrategy(1, 3, 5);
        };
    }
}