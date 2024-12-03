package org.example.pizzeriasimulator.services.cookers.generation;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.NoArgsConstructor;
import org.example.pizzeriasimulator.models.cookers.BakingCooker;
import org.example.pizzeriasimulator.models.cookers.Cooker;
import org.example.pizzeriasimulator.models.cookers.DoughCooker;
import org.example.pizzeriasimulator.models.cookers.UltimateCooker;
import org.example.pizzeriasimulator.services.customers.generation.RandomNamesProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@NoArgsConstructor
public class CookerGenerator {
    private static final int MIN_COOKERS = 3;
    private static final int MAX_COOKERS = 10;

    public List<Cooker> generateCookers(@Min(MIN_COOKERS)
                                        @Max(MAX_COOKERS)
                                        int cookersCount) {
        List<Cooker> cookers = new ArrayList<>(generateBaseCookers());

        int totalCookers = cookersCount - 3;

        int bakingCookersCount = (int) Math.round(totalCookers * 0.4); // 40% на BakingCooker
        int doughCookersCount = (int) Math.round(totalCookers * 0.3);  // 30% на DoughCooker
        int ultimateCookersCount = totalCookers - bakingCookersCount - doughCookersCount; // Решта

        for (int i = 0; i < doughCookersCount; i++) {
            cookers.add(new DoughCooker(RandomNamesProvider.getRandomName()));
        }

        for (int i = 0; i < bakingCookersCount; i++) {
            cookers.add(new BakingCooker(RandomNamesProvider.getRandomName()));
        }

        for (int i = 0; i < ultimateCookersCount; i++) {
            cookers.add(new UltimateCooker(RandomNamesProvider.getRandomName()));
        }

        for (int i = 0; i < cookers.size(); i++) {
            Cooker currentCooker = cookers.get(i);
            if (i + 1 < cookers.size()) {
                Cooker nextCooker = cookers.get(i + 1);
                currentCooker.setNextCooker(nextCooker);
            }
        }

        return cookers;
    }

    private List<Cooker> generateBaseCookers() {
        List<Cooker> cookers = new ArrayList<>();
        cookers.add(new DoughCooker(RandomNamesProvider.getRandomName()));
        cookers.add(new BakingCooker(RandomNamesProvider.getRandomName()));
        cookers.add(new UltimateCooker(RandomNamesProvider.getRandomName()));

        return cookers;
    }
}
