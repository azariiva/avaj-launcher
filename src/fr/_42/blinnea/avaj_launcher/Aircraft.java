package fr._42.blinnea.avaj_launcher;

import fr._42.blinnea.avaj_launcher.exceptions.IllegalAircraftException;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents an aircraft with name, id and coordinates
 */
public class Aircraft {
    protected final long id;
    protected final String name;
    protected Coordinates coordinates;
    private static int idCounter = 0;

    /**
     * Creates aircraft with given name and coordinates assign unique id using {@link Aircraft#nextId()}
     * @param name name of aircraft
     * @param coordinates coordinates of aircraft
     * @throws IllegalAircraftException when name or coordinates is null
     */
    protected Aircraft(String name, Coordinates coordinates) throws IllegalAircraftException {
        this.id = nextId();
        this.name = name;
        this.coordinates = coordinates;
        if (name == null || coordinates == null)
            throw new IllegalAircraftException(this.getClass().toString(), "<init>",
                    "name or coordinates can not be null");
    }

    /**
     * Calculates unique id by adding 1 to {@link Aircraft#idCounter}
     * @return unique id
     */
    private static long nextId() {
        idCounter += 1;
        return idCounter;
    }

    /**
     * HashCode of the Aircraft represented by {@link Aircraft#id}
     */
    @Override
    public int hashCode() {
        return idCounter % Integer.MAX_VALUE;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null) return false;
        if (getClass() != otherObject.getClass()) return false;
        Aircraft other = (Aircraft) otherObject;
        return id == other.id && Objects.equals(name, other.name) && Objects.equals(coordinates, other.coordinates);
    }

    public static void main(String[] args) {
        String[] names = {"B1", "B2", "H1", "H2"};
        Coordinates[] coordinates = {new Coordinates(10, 11, 12),
                new Coordinates(101, 121, 13), new Coordinates(11, 12, 13),
                new Coordinates(13, 14, 15)};
        ArrayList<Aircraft> aircrafts = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            aircrafts.add(new Aircraft(names[i], coordinates[i]) {
                @Override
                public String toString() {
                    return String.format("AircraftTest#%s(%d)", name, id);
                }
            });
        }
        for (Aircraft aircraft : aircrafts)
            System.out.println(aircraft.toString());
    }
}
