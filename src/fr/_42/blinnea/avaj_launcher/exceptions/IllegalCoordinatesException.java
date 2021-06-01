package fr._42.blinnea.avaj_launcher.exceptions;

import fr._42.blinnea.avaj_launcher.Coordinates;
import fr._42.blinnea.avaj_launcher.WeatherProvider;

public class IllegalCoordinatesException extends IllegalArgumentException {
    public IllegalCoordinatesException(WeatherProvider weatherProvider) {super(weatherProvider.toString());}
    public IllegalCoordinatesException(Coordinates coordinates) {
        super(coordinates.toString());
    }
}
