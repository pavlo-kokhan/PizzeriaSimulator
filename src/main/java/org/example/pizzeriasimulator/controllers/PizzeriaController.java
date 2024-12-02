package org.example.pizzeriasimulator.controllers;

import org.example.pizzeriasimulator.models.cookers.Cooker;
import org.example.pizzeriasimulator.models.customer.Customer;
import org.example.pizzeriasimulator.models.dtos.CookersResponseDto;
import org.example.pizzeriasimulator.models.dtos.CustomersResponseDto;
import org.example.pizzeriasimulator.models.dtos.StartSimulationDto;
import org.example.pizzeriasimulator.models.dtos.StartSimulationResponseDto;
import org.example.pizzeriasimulator.models.simulation.Simulation;
import org.example.pizzeriasimulator.services.PizzeriaSimulationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pizzeria")
public class PizzeriaController {
    private PizzeriaSimulationService service;

    public PizzeriaController(PizzeriaSimulationService service) {
        this.service = service;
    }

    @PostMapping("/start-simulation")
    public ResponseEntity<StartSimulationResponseDto> startSimulation(StartSimulationDto startSimulationDto) {
        String simulationId = service.runSimulation(startSimulationDto);
        return ResponseEntity.ok(new StartSimulationResponseDto(simulationId));
    }

    @GetMapping("/cookers")
    public ResponseEntity<CookersResponseDto> getAllCookers(String simulationId) {
        Simulation simulation = service.getSimulations().get(simulationId);
        List<Cooker> cookers = simulation.getCookers();
        return ResponseEntity.ok(new CookersResponseDto(cookers));
    }

    @GetMapping("/customers")
    public ResponseEntity<CustomersResponseDto> getAllCustomers(String simulationId) {
        Simulation simulation = service.getSimulations().get(simulationId);
        List<Customer> customers = simulation.getCustomers();
        return ResponseEntity.ok(new CustomersResponseDto(customers));
    }
}
