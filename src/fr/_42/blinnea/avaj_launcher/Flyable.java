package fr._42.blinnea.avaj_launcher;

import fr._42.blinnea.avaj_launcher.exceptions.UnknownWeatherTowerException;

/**
 * Representing any flyable object
 */
public interface Flyable
{
    //TODO: How to decorate methods to indicate that they may change object state?

    /**
     * Logs current conditions and updates them according to subject
     * @throws UnknownWeatherTowerException when {@link WeatherTower} was not registered
     */
    void updateConditions() throws UnknownWeatherTowerException;

    /**
     * Registers weatherTower by assigning it to class attribute
     * @throws UnknownWeatherTowerException when {@link WeatherTower} already has been registered
     */
    void registerTower(WeatherTower weatherTower) throws UnknownWeatherTowerException;
}
