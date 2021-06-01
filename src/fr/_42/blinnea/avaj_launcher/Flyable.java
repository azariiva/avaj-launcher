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
     * @throws UnknownWeatherTowerException when class hasn't registered {@link WeatherTower}
     */
    void updateConditions() throws UnknownWeatherTowerException;

    /**
     * Registers weatherTower by assigning it to class attribute
     */
    void registerTower(WeatherTower weatherTower);
}
