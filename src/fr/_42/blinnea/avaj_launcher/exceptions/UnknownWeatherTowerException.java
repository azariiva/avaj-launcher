package fr._42.blinnea.avaj_launcher.exceptions;

import fr._42.blinnea.avaj_launcher.Aircraft;

public class UnknownWeatherTowerException extends IllegalStateException {
    public UnknownWeatherTowerException(Aircraft aircraft) {
        super(aircraft.toString());
    }
}
