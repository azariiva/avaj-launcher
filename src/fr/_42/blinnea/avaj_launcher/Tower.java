package fr._42.blinnea.avaj_launcher;

import fr._42.blinnea.avaj_launcher.exceptions.IllegalFlyableException;

import java.util.ArrayList;
import java.util.logging.*;

/**
 * Represents tower that watches after any {@link Flyable} object and
 * {@link Tower#conditionsChanged() updates conditions} when necessary
 */
public class Tower implements Loggable {
    private final ArrayList<Flyable> observers;

    Tower() {
        observers = new ArrayList<>();
    }

    /**
     * Registers flyable
     * @param flyable any {@link Flyable flyable} object
     * @throws IllegalFlyableException if flyable is null
     */
    public void register(Flyable flyable) throws IllegalFlyableException {
        logger.entering("Tower", "register");
        if (flyable == null) throw new IllegalFlyableException(this);
        observers.add(flyable);
        logger.info(String.format("Tower says: %s registered to weather tower.", flyable.toString()));
        logger.exiting("Tower", "register");
    }

    /**
     * Unregisters flyable
     * @param flyable any {@link Flyable flyable} object
     * @throws IllegalFlyableException if flyable is null
     */
    public void unregister(Flyable flyable) throws IllegalFlyableException {
        logger.entering("Tower", "unregister");
        if (flyable == null) throw new IllegalFlyableException(this);
        observers.remove(flyable);
        logger.info(String.format("Tower says: %s unregistered from weather tower.", flyable.toString()));
        logger.exiting("Tower", "unregister");
    }

    /**
     * Calls {@link Flyable#updateConditions()} on every registered entity
     */
    protected void conditionsChanged() {
        for (int i = 0; i < observers.size(); i++)
            observers.get(i).updateConditions();
        logger.finest("conditionsChanged");
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

        class TestFlyable implements Flyable {
            private Tower tower = null;

            TestFlyable() {}

            @Override
            public void updateConditions() {
                assert tower != null : "tower was not registered";
                tower.unregister(this);
            }

            @Override
            public void registerTower(WeatherTower weatherTower) {}

            public void registerTower(Tower tower) {
                this.tower = tower;
                tower.register(this);
            }
        }
        TestFlyable[] flyables = {new TestFlyable(), new TestFlyable()};
        Tower tower = new Tower();
        for (int i = 0; i < flyables.length; i++)
            flyables[i].registerTower(tower);
        tower.conditionsChanged();
    }
}
