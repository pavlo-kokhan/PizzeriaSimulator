package org.example.pizzeriasimulator.services;

import lombok.Getter;
import org.example.pizzeriasimulator.models.customer.Customer;
import org.example.pizzeriasimulator.models.simulation.Simulation;
import org.example.pizzeriasimulator.models.dtos.StartSimulationDto;
import org.example.pizzeriasimulator.services.cookers.generation.CookerGenerator;
import org.example.pizzeriasimulator.services.customers.generation.CustomerGenerator;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class PizzeriaSimulationService {
    @Getter
    private HashMap<String, Simulation> simulations;
    private final CustomerGenerator customerGenerator;
    private final CookerGenerator cookerGenerator;

    public PizzeriaSimulationService() {
        simulations = new HashMap<>();
        customerGenerator = new CustomerGenerator();
        cookerGenerator = new CookerGenerator();
    }

    public String runSimulation(StartSimulationDto startGenerationDto) {
        Simulation simulation = createSimulation(startGenerationDto);
        String id = generateSimulationId();
        simulations.put(id, simulation);

        // todo

        return id;
    }

    private Simulation createSimulation(StartSimulationDto startGenerationDto) {
        return new Simulation(startGenerationDto);
    }

    private String generateSimulationId() {
        return "SIM-" + UUID.randomUUID();
    }

    private void subscribeOnCustomerPizzas(Customer customer) {
        // todo
    }
}
