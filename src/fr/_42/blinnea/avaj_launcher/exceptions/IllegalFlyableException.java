package fr._42.blinnea.avaj_launcher.exceptions;

import fr._42.blinnea.avaj_launcher.Aircraft;
import fr._42.blinnea.avaj_launcher.Tower;

public class IllegalFlyableException extends IllegalArgumentException {
    public IllegalFlyableException(Tower tower) {
        super(tower.toString());
    }
}
