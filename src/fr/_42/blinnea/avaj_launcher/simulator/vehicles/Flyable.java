package fr._42.blinnea.avaj_launcher.simulator.vehicles;

import fr._42.blinnea.avaj_launcher.simulator.WeatherTower;

public interface Flyable
{
    void updateConditions();
    void registerTower(WeatherTower weatherTower);
}
