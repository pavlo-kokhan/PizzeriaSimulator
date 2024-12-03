package org.example.pizzeriasimulator.controllers;

import org.example.pizzeriasimulator.models.cookers.Cooker;
import org.example.pizzeriasimulator.models.customer.Customer;
import org.example.pizzeriasimulator.models.dtos.*;
import org.example.pizzeriasimulator.models.simulation.Simulation;
import org.example.pizzeriasimulator.services.PizzeriaSimulationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzeria")
public class PizzeriaController {
    private PizzeriaSimulationService service;

    public PizzeriaController(PizzeriaSimulationService service) {
        this.service = service;
    }

    @PostMapping("/start-simulation")
    public ResponseEntity<StartSimulationResponseDto> startSimulation(@RequestBody StartSimulationDto startSimulationDto) {
        String simulationId = service.runSimulation(startSimulationDto);
        return ResponseEntity.ok(new StartSimulationResponseDto(simulationId));
    }

    @GetMapping("/cookers")
    public ResponseEntity<CookersResponseDto> getAllCookers(@RequestParam String simulationId) {
        Simulation simulation = service.getSimulations().get(simulationId);
        return ResponseEntity.ok(new CookersResponseDto(simulation.getCookers()));
    }

    @GetMapping("/customers")
    public ResponseEntity<CustomersResponseDto> getAllCustomers(String simulationId) {
        Simulation simulation = service.getSimulations().get(simulationId);
        return ResponseEntity.ok(new CustomersResponseDto(simulation.getCustomers()));
    }

    @GetMapping("/simulation-status")
    public ResponseEntity<SimulationStatusResponseDto> getSimulationStatus(String simulationId) {
        Simulation simulation = service.getSimulations().get(simulationId);

        if (simulation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        SimulationStatusResponseDto status = new SimulationStatusResponseDto(
                simulationId,
                simulation.getCustomers(),
                simulation.getCookers(),
                simulation.getPizzaLogs()
        );

        return ResponseEntity.ok(status);
    }
}
