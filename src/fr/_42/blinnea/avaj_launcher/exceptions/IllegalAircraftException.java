package fr._42.blinnea.avaj_launcher.exceptions;

import fr._42.blinnea.avaj_launcher.Aircraft;
import fr._42.blinnea.avaj_launcher.Baloon;

public class IllegalAircraftException extends AvajIllegalArgumentException {
    public IllegalAircraftException(String aClass, String method, String description) {
        super(aClass, method, description);
    }

    public IllegalAircraftException() {
        super();
    }

    public static void main(String[] args) {
        Aircraft baloon = new Baloon(null, null);
    }
}
