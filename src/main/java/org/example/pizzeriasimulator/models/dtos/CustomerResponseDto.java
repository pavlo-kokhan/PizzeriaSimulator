package org.example.pizzeriasimulator.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.pizzeriasimulator.models.customer.Customer;

@Getter
@AllArgsConstructor
public class CustomerResponseDto {
    private final String name;
    private final OrderResponseDto order;

    public CustomerResponseDto(Customer customer) {
        this.name = customer.getName();
        this.order = new OrderResponseDto(customer.getOrder());
    }
}
