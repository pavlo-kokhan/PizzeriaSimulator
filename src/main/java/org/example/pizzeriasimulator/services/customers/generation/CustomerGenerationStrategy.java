package org.example.pizzeriasimulator.services.customers.generation;

import org.example.pizzeriasimulator.models.customer.Customer;

import java.util.List;

public interface CustomerGenerationStrategy {
    List<Customer> generateCustomers();
}
