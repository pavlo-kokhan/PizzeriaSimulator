package org.example.pizzeriasimulator.controllers;

import org.example.pizzeriasimulator.models.dtos.StartSimulationDto;
import org.example.pizzeriasimulator.models.dtos.StartSimulationResponseDto;
import org.example.pizzeriasimulator.services.PizzeriaSimulationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // todo
    // GetMapping for Customers and Orders data
    // GetMapping for Pizzas in Order data
    // GetMapping for Cookers data
}
