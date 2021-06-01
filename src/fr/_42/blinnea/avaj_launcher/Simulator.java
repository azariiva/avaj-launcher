package fr._42.blinnea.avaj_launcher;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import java.util.logging.Formatter;

public class Simulator implements Loggable {
    public static void main(String[] args) {
        if (System.getProperty("java.util.logging.config.class") == null
                && System.getProperty("java.util.logging.config.file") == null)
        {
            logger.setUseParentHandlers(false);
            logger.setLevel(Level.INFO);
            try {
                Handler handler = new FileHandler("./simulation.txt");
                handler.setFormatter(new Formatter() {
                    @Override
                    public String format(LogRecord record) {
                        return String.format("%s\n", record.getMessage());
                    }
                });
                logger.addHandler(handler);
            } catch (IOException e) {
                logger.severe(e.toString());
            }
        }

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(args[0])))) {
            int weatherChange = scanner.nextInt();
            WeatherTower weatherTower = new WeatherTower();
            ArrayList<Flyable> vehicles = new ArrayList<>();
            while (scanner.hasNext()) {
                String type = scanner.next("[\\w]+");
                String name = scanner.next("[\\w]+");
                int longitude = scanner.nextInt();
                int latitude = scanner.nextInt();
                int height = scanner.nextInt();
                vehicles.add(AircraftFactory.newAircraft(type, name, longitude, latitude, height));
            }
            for (Flyable vehicle : vehicles)
                weatherTower.register(vehicle);
            for (int i = 0; i < weatherChange; i++)
                weatherTower.changeWeather();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.printf("Provide filename as command line argument: %s\n", e.toString());
        } catch (FileNotFoundException e) {
            System.out.printf("Wrong filename %s provided: %s\n", args[0], e.toString());
        } catch (NoSuchElementException e) {
            System.out.println("Wrong file format");
        }

//        Locale locale = new Locale("ru","RU");
//        ResourceBundle messages =  ResourceBundle.getBundle("fr._42.blinnea.avaj_launcher.logmessages", locale);
//
//        System.out.println(messages.getString("RAIN"));
    }
}
