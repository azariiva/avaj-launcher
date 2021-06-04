package fr._42.blinnea.avaj_launcher.exceptions;

public class UnknownWeatherTowerException extends IllegalStateException {
    public UnknownWeatherTowerException() {
        super();
    }

    public UnknownWeatherTowerException(String aClass, String method, String description) {
        super(String.format("%s::%s: %s", aClass, method, description));
    }
}
