package fr._42.blinnea.avaj_launcher;

import fr._42.blinnea.avaj_launcher.exceptions.IllegalCoordinatesException;
import fr._42.blinnea.avaj_launcher.exceptions.UnknownWeatherTowerException;

public class Helicopter extends Aircraft implements Flyable, Loggable {
    private WeatherTower weatherTower;

    public Helicopter(String name, Coordinates coordinates) throws IllegalCoordinatesException {
        super(name, coordinates);
        weatherTower  = null;
        logger.finest("Helicopter created");
    }

    private void updateCoordinates(String weather) {
        int     longitude = coordinates.getLongitude(),
                latitude = coordinates.getLatitude(),
                height = coordinates.getHeight();

        switch (weather) {
            case "SUN":
                longitude += 10;
                height += 2;
                break;
            case "RAIN":
                longitude += 5;
                break;
            case "FOG":
                longitude += 1;
                break;
            case "SNOW":
                height -= 12;
                break;
        }
        logger.fine(String.format("%s: height%+d", toString(), height - coordinates.getHeight()));
        coordinates = new Coordinates(longitude, latitude, Math.max(0, Math.min(height, 100)));
    }

    @Override
    public void updateConditions() throws UnknownWeatherTowerException {
        if (weatherTower == null)
            throw new UnknownWeatherTowerException(this.getClass().toString(),
                    "updateConditions", "could not update conditions if weatherTower had not been registered");
        String weather = weatherTower.getWeather(coordinates);
        logger.info(String.format("%s: %s", toString(), weather));
        updateCoordinates(weather);
        if (coordinates.getHeight() == 0) {
            logger.info(String.format("%s landing at (%d, %d)", toString(), coordinates.getLatitude(), coordinates.getLongitude()));
            weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) throws UnknownWeatherTowerException {
        logger.entering("Helicopter", "registerTower");
        if (this.weatherTower != null)
            throw new UnknownWeatherTowerException(this.getClass().toString(),
                    "registerTower", "could not re-register weatherTower");
        this.weatherTower = weatherTower;
        logger.finest(String.format("WeatherTower@%x registered", weatherTower.hashCode()));
        logger.exiting("Helicopter", "registerTower");
    }

    @Override
    public String toString() {
        return String.format("Helicopter#%s(%d)", name, id);
    }
}
