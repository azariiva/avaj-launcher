package fr._42.blinnea.avaj_launcher.simulator;

import fr._42.blinnea.avaj_launcher.simulator.vehicles.Flyable;

import java.util.ArrayList;

public class Tower {
    private final ArrayList<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
    }

    protected void conditionsChanged() {
        // TODO: Implement method
    }
}
