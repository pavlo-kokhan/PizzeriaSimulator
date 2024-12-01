package org.example.pizzeriasimulator.models.customer;

import lombok.Getter;
import org.example.pizzeriasimulator.models.order.Order;

@Getter
public class Customer {
    private String name;
    private Order order;

    public Customer(String name) {
        this.name = name;
    }

    // todo
}
