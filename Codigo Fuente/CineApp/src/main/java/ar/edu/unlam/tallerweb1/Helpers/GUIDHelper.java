package ar.edu.unlam.tallerweb1.Helpers;

import java.util.UUID;

public class GUIDHelper {
    public static String getRandomGUID() {
        return UUID.randomUUID().toString();
    }
}
