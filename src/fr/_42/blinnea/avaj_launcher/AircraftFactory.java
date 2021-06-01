package fr._42.blinnea.avaj_launcher;

import java.util.logging.*;

/**
 * Factory class that constructs {@link Flyable flyable} {@link Aircraft aircraft}
 */
public class AircraftFactory {
    /**
     *
     * @param type can be {@link Baloon}, {@link Helicopter} or {@link JetPlane}
     * @return newly constructed {@link Flyable flyable} {@link Aircraft aircraft} of given type
     */
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        assert type != null : "type=null";
        switch (type) {
            case "Helicopter":
                return new Helicopter(name, new Coordinates(longitude, latitude, height));
            case "JetPlane":
                return new JetPlane(name, new Coordinates(longitude, latitude, height));
            case "Baloon":
                return new Baloon(name, new Coordinates(longitude, latitude, height));
            default:
                throw new IllegalArgumentException(String.format("type %s is not supported", type));
        }
    }

    public static void main(String[] args) {
        if (System.getProperty("java.util.logging.config.class") == null
                && System.getProperty("java.util.logging.config.file") == null)
        {
            Logger logger = Logger.getLogger("fr._42.blinnea.avaj_launcher", "fr._42.blinnea.avaj_launcher.logmessages");
            logger.setLevel(Level.INFO);
            Handler handler = new Handler(){
                @Override
                public void publish(LogRecord record)
                {
                    if (getFormatter() == null) setFormatter(new SimpleFormatter());
                    String message = getFormatter().format(record);
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

        WeatherTower tower = new WeatherTower();
        tower.register(AircraftFactory.newAircraft("Baloon", "B1", 10, 11, 12));
        tower.register(AircraftFactory.newAircraft("JetPlane", "J1", 10, 11, 12));
        tower.register(AircraftFactory.newAircraft("Helicopter", "H1", 10, 11, 12));
        for (int i = 0; i < 10; i++)
            tower.changeWeather();
    }
}
