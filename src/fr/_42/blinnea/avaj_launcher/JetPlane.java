package fr._42.blinnea.avaj_launcher;

import fr._42.blinnea.avaj_launcher.exceptions.IllegalCoordinatesException;
import fr._42.blinnea.avaj_launcher.exceptions.UnknownWeatherTowerException;

public class JetPlane extends Aircraft implements Flyable, Loggable {
    private WeatherTower weatherTower;

    public JetPlane(String name, Coordinates coordinates) throws IllegalCoordinatesException {
        super(name, coordinates);
        weatherTower = null;
        logger.finest("JetPlane created");
    }

    private void updateCoordinates(String weather) {
        int     longitude = coordinates.getLongitude(),
                latitude = coordinates.getLatitude(),
                height = coordinates.getHeight();

        switch (weather) {
            case "SUN":
                latitude += 10;
                height += 2;
                break;
            case "RAIN":
                latitude += 5;
                break;
            case "FOG":
                latitude += 1;
                break;
            case "SNOW":
                height -= 7;
                break;
        }
        logger.fine(String.format("%s: height%+d", toString(), height - coordinates.getHeight()));
        coordinates = new Coordinates(longitude, latitude, Math.max(0, Math.min(height, 100)));
    }

    @Override
    public void updateConditions() throws UnknownWeatherTowerException {
        if (weatherTower == null)
            throw new UnknownWeatherTowerException(this);
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
        logger.entering("JetPlane", "registerTower");
        if (this.weatherTower != null) logger.warning("weatherTower was already registered");
        this.weatherTower = weatherTower;
        logger.finest(String.format("WeatherTower@%x registered", weatherTower.hashCode()));
        logger.exiting("WeatherTower", "registerTower");
    }

    @Override
    public String toString() {
        return String.format("JetPlane#%s(%d)", name, id);
    }
}
