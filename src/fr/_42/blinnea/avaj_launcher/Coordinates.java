package fr._42.blinnea.avaj_launcher;

import fr._42.blinnea.avaj_launcher.exceptions.IllegalCoordinatesException;
import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * Represents three dimensional point
 */
public class Coordinates {
    private final int longitude;
    private final int latitude;
    private final int height;

    /**
     * @param longitude any positive integer
     * @param latitude any positive integer
     * @param height integer between 0 and 100
     * @throws IllegalCoordinatesException when longitude, latitude or height does not satisfy conditions
     */
    Coordinates(int longitude, int latitude, int height) throws IllegalCoordinatesException {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
        if (longitude <= 0 || latitude <= 0 || height < 0 || height > 100)
            throw new IllegalCoordinatesException(this.getClass().toString(), "<init>", "coordinates out of bounds");
    }

    @Getter
    int getLongitude() {
        return longitude;
    }

    @Getter
    int getLatitude() {
        return latitude;
    }

    @Getter
    int getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null) return false;
        if (getClass() != otherObject.getClass()) return false;
        Coordinates other = (Coordinates) otherObject;
        return longitude == other.longitude && latitude == other.latitude && height == other.height;
    }

    @Override
    public int hashCode() {
        return 7 * Integer.hashCode(longitude) + 11 * Integer.hashCode(latitude) + 13 * Integer.hashCode(height);
    }

    @Override
    public String toString() {
        return String.format("%s[longitude=%d,latitude=%d,height=%d]", getClass().getName(), longitude, latitude, height);
    }


    public static void main(String[] args) {
        Coordinates coordinates, coordinates1;

        try {
             coordinates = new Coordinates(10, 20, -4);
        } catch (IllegalArgumentException e) {
            System.out.println(e.toString());
        }
        coordinates = new Coordinates(10, 20, 5);
        System.out.println(coordinates.toString());
        coordinates1 = new Coordinates(11, 23, 7);
        System.out.println(coordinates.equals(coordinates1));
        coordinates1 = coordinates;
        System.out.println(coordinates.equals(coordinates1));
    }
}
