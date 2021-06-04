package fr._42.blinnea.avaj_launcher.exceptions;

public class AvajIllegalArgumentException extends IllegalArgumentException {
    /**
     * @param aClass - class where error occurred
     * @param method - method where error occurred
     * @param description - additional description
     */
    public AvajIllegalArgumentException(String aClass, String method, String description) {
        super(String.format("%s::%s: %s", aClass, method, description));
    }

    public AvajIllegalArgumentException() {
        super();
    }
}
