package fr._42.blinnea.avaj_launcher;

import fr._42.blinnea.avaj_launcher.exceptions.IllegalCoordinatesException;
import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.logging.Logger;

/**
 * Represents a weather provider<br>
 */
public class WeatherProvider {
    private static WeatherProvider weatherProvider = null;
    private static final String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};
    private static final Logger logger = Logger.getLogger("fr._42.blinnea.avaj_launcher", "fr._42.blinnea.avaj_launcher.logmessages");

    private WeatherProvider() {}

    @Getter
    public static WeatherProvider getProvider() {
        if (weatherProvider == null) weatherProvider = new WeatherProvider();
        return weatherProvider;
    }

    private static int coordinatesToIndex(Coordinates coordinates) {
        return (coordinates.getHeight() % 4 + coordinates.getLatitude() % 4 + coordinates.getLongitude() % 4) % 4;
    }

    /**
     * @return current weather from {@link WeatherProvider#weather}
     * @throws IllegalCoordinatesException if coordinates is null
     */
    public String getCurrentWeather(Coordinates coordinates) throws IllegalCoordinatesException {
        logger.entering("WeatherProvider", "getCurrentWeather");
        if (coordinates == null) throw new IllegalCoordinatesException(this.getClass().toString(), "getCurrentWeather",
                "null pointer passed to method");
        String weather = WeatherProvider.weather[coordinatesToIndex(coordinates)];
        logger.exiting("WeatherProvider", "getCurrentWeather");
        return weather;
    }

    public static void main(String[] args) {
        Coordinates[] coordinates = {new Coordinates(10, 11, 12),
                new Coordinates(101, 121, 13), new Coordinates(11, 12, 13)};
        for (Coordinates coordinates1 : coordinates)
            System.out.println(WeatherProvider.getProvider().getCurrentWeather(coordinates1));
        for (Coordinates coordinates1 : coordinates)
            System.out.println(WeatherProvider.getProvider().getCurrentWeather(coordinates1));
    }
}
