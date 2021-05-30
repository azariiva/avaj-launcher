package fr._42.blinnea.avaj_launcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.printf("Filename not found: %s\n", e.toString());
            System.exit(0);
        }
        catch (FileNotFoundException e) {
            System.out.printf("Scenario file not found: %s", e.toString());
            System.exit(0);
        }
    }
}
