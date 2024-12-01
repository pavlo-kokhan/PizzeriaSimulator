package org.example.pizzeriasimulator.services.customers.generation;

import java.util.List;
import java.util.Random;

public class RandomNamesProvider {
    private static final List<String> NAMES = List.of(
            "John", "Emily", "Michael", "Sarah", "David",
            "Anna", "James", "Laura", "Robert", "Emma",
            "William", "Joseph", "Paul", "Anthony", "Elisabeth",
            "Joshua", "Robert", "Joe", "Donald", "Kevin", "Mary",
            "Alexander", "Jose", "Ivanka", "Susanna", "Madonna"
    );

    private static final Random RANDOM = new Random();

    public static String getRandomName() {
        return NAMES.get(RANDOM.nextInt(NAMES.size()));
    }
}
