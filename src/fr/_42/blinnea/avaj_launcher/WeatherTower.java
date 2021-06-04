package fr._42.blinnea.avaj_launcher;

public class WeatherTower extends Tower {
    /**
     * @return weather from {@link WeatherProvider}
     */
    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    /**
     * Wrapper around {@link Tower#conditionsChanged()}
     */
    void changeWeather() {
        super.conditionsChanged();
    }

    @Override
    public void register(Flyable flyable) {
        super.register(flyable);
        flyable.registerTower(this);
    }
}
