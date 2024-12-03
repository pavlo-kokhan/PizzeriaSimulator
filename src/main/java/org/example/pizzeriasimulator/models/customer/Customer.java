package org.example.pizzeriasimulator.models.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.pizzeriasimulator.models.order.Order;
import org.example.pizzeriasimulator.models.pizza.Pizza;

import java.util.List;

@Getter
public class Customer {
    private final String name;
    private final Order order;

    public Customer(String name, Order order) {
        this.name = name;
        this.order = order;
    }
}
