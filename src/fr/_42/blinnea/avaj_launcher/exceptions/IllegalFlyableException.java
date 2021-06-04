package fr._42.blinnea.avaj_launcher.exceptions;

public class IllegalFlyableException extends AvajIllegalArgumentException {
    public IllegalFlyableException(String aClass, String method, String description) {
        super(aClass, method, description);
    }

    public IllegalFlyableException() {
        super();
    }
}
