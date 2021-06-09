package fr._42.blinnea.avaj_launcher;

import fr._42.blinnea.avaj_launcher.exceptions.IllegalFlyableException;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.*;

/**
 * Represents tower that watches after any {@link Flyable} object and
 * {@link Tower#conditionsChanged() updates conditions} when necessary
 */
public class Tower implements Loggable {
    // ConcurrentHashMap, т.к. иначе невозможно изменить Set с ошибкой ConcurrentModificationException
    private final Set<Flyable> observers = Collections.newSetFromMap(new ConcurrentHashMap<Flyable,Boolean>());

    /**
     * Registers flyable
     * @param flyable any {@link Flyable flyable} object
     * @throws IllegalFlyableException if flyable is null or if flyable is already registered
     */
    public void register(Flyable flyable) throws IllegalFlyableException {
        logger.entering("Tower", "register");
        if (flyable == null) throw new IllegalFlyableException(this.getClass().toString(), "register", "null argument");
        if (!observers.add(flyable)) throw new IllegalFlyableException(this.getClass().toString(), "register",
                String.format("%s is already registered", flyable.toString()));
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
        if (flyable == null) throw new IllegalFlyableException(this.getClass().toString(), "unregister",
                "null argument");
        observers.remove(flyable);
        logger.info(String.format("Tower says: %s unregistered from weather tower.", flyable.toString()));
        logger.exiting("Tower", "unregister");
    }

    /**
     * Calls {@link Flyable#updateConditions()} on every registered entity
     */
    protected void conditionsChanged() {
        for (Flyable observer : observers)
            observer.updateConditions();
        logger.finest("conditionsChanged");
    }

    public static void main(String[] args) {
        if (System.getProperty("java.util.logging.config.class") == null
                && System.getProperty("java.util.logging.config.file") == null)
        {
            logger.setUseParentHandlers(false);
            logger.setLevel(Level.INFO);
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
        try {
            flyables[0].registerTower(tower);
        } catch (IllegalFlyableException e) {
            System.out.println(e.toString());
        }
        tower.conditionsChanged();
        for (int i = 0; i < flyables.length; i++)
            flyables[i].registerTower(tower);
    }
}
