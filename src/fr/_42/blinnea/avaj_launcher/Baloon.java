package fr._42.blinnea.avaj_launcher;

import fr._42.blinnea.avaj_launcher.exceptions.IllegalCoordinatesException;
import fr._42.blinnea.avaj_launcher.exceptions.UnknownWeatherTowerException;

import java.util.logging.*;

public class Baloon extends Aircraft implements Flyable, Loggable {
    private WeatherTower weatherTower;

    public Baloon(String name, Coordinates coordinates) throws IllegalCoordinatesException {
        super(name, coordinates);
        weatherTower = null;
        logger.finest("Baloon created");
    }

    private void updateCoordinates(String weather) {
        assert weather != null : "weather=null";
        int     longitude = coordinates.getLongitude(),
                latitude = coordinates.getLatitude(),
                height = coordinates.getHeight();
        switch (weather) {
            case "SUN":
                longitude += 2;
                height += 4;
                break;
            case "RAIN":
                height -= 5;
                break;
            case "FOG":
                height -= 3;
                break;
            case "SNOW":
                height -= 15;
                break;
        }
        logger.fine(String.format("%s: height%+d", toString(), height - coordinates.getHeight()));
        coordinates = new Coordinates(longitude, latitude, Math.max(0, Math.min(height, 100)));
    }

    @Override
    public void updateConditions() throws UnknownWeatherTowerException {
        if (weatherTower == null) throw new UnknownWeatherTowerException(this);
        String weather = weatherTower.getWeather(coordinates);
        logger.info(String.format("%s: %s", toString(), weather));
        updateCoordinates(weather);
        if (coordinates.getHeight() == 0) {
            logger.info(String.format("%s landing at (%d, %d)", toString(), coordinates.getLatitude(), coordinates.getLongitude()));
            weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        logger.entering("Baloon", "registerTower");
        if (this.weatherTower != null) {
            logger.warning("weatherTower was already registered");
            weatherTower.unregister(this);
        }
        this.weatherTower = weatherTower;
        logger.finest(String.format("WeatherTower@%x registered", weatherTower.hashCode()));
        logger.exiting("Baloon", "registerTower");
    }

    @Override
    public String toString() {
        return String.format("Baloon#%s(%d)", name, id);
    }

    public static void main(String[] args) {
        if (System.getProperty("java.util.logging.config.class") == null
                && System.getProperty("java.util.logging.config.file") == null)
        {
            logger.setLevel(Level.ALL);
            Handler handler = new Handler(){
                @Override
                public void publish(LogRecord record)
                {
                    if (getFormatter() == null) setFormatter(new SimpleFormatter());
                    String message = getFormatter().format(record);
                    if (record.getLevel().intValue() >= Level.WARNING.intValue())
                        System.err.print(message);
                    else
                        System.out.print(message);
                }

                @Override
                public void close() {
                    flush();
                }

                @Override
                public void flush() {
                    System.err.flush();
                    System.out.flush();
                }
            };
            logger.addHandler(handler);
        }

        WeatherTower weatherTower = new WeatherTower();
        Baloon baloon = new Baloon("B1", new Coordinates(100, 234, 15));
        baloon.registerTower(weatherTower);
        for (int i = 0; i < 100; i++) weatherTower.changeWeather();
    }
}
