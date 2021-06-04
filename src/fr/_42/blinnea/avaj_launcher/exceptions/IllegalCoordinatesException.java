package fr._42.blinnea.avaj_launcher.exceptions;

import fr._42.blinnea.avaj_launcher.Coordinates;
import fr._42.blinnea.avaj_launcher.WeatherProvider;

public class IllegalCoordinatesException extends AvajIllegalArgumentException {
    public IllegalCoordinatesException(String aClass, String method, String description) {
        super(aClass, method, description);
    }

    public IllegalCoordinatesException() {
        super();
    }
}
