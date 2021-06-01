package fr._42.blinnea.avaj_launcher.exceptions;

import fr._42.blinnea.avaj_launcher.Aircraft;
import fr._42.blinnea.avaj_launcher.Baloon;

public class IllegalAircraftException extends IllegalArgumentException {
    public IllegalAircraftException(Aircraft aircraft) {
        super(aircraft.toString());
    }

    public static void main(String[] args) {
        Aircraft baloon = new Baloon(null, null);
    }
}
