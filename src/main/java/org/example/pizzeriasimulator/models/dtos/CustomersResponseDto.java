package org.example.pizzeriasimulator.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.pizzeriasimulator.models.customer.Customer;

import java.util.List;

@Getter
@AllArgsConstructor
public class CustomersResponseDto {
    private List<Customer> customers;
}
