package org.example.pizzeriasimulator.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CustomersResponseDto {
    private final List<CustomerResponseDto> customers;
}
