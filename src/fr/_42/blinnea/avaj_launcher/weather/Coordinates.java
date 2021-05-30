package fr._42.blinnea.avaj_launcher.weather;

public class Coordinates {
    private final int longitude;
    private final int latitude;
    private final int height;

    Coordinates(int longitude, int latitude, int height) {
        assert longitude >= 0 : String.format("longitude=%d", longitude);
        this.longitude = longitude;
        assert latitude >= 0 : String.format("latitude=%d", latitude);
        this.latitude = latitude;
        assert height >= 0 && height <= 100 : String.format("height=%d", height);
        this.height = height;
    }

    int getLongitude() {
        return longitude;
    }

    int getLatitude() {
        return latitude;
    }

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
        return String.format("%s[longitude=%d,latitude=%d,height=%d", getClass().getName(), longitude, latitude, height);
    }

    public static void main(String[] args) {
        Coordinates coordinates = new Coordinates(10, 20, -4);
    }
}
