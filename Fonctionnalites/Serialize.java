package Fonctionnalites;

import java.io.*;
import java.nio.file.Paths;

public class Serialize {
    public static void serialize(int score) {
        // Serialize an object
        try (
                FileOutputStream fileOut = new FileOutputStream(Paths.get("saved.er").toString());
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(score);

        } catch (IOException e) {
            new Error("Serialize error");
        }
        return;
    }

    public static int deserialize() {
        int score = 0;
        try (
                FileInputStream fileIn = new FileInputStream(Paths.get("saved.er").toString());
                ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            score = (Integer) objectIn.readObject();
            return score;

        } catch (IOException | ClassNotFoundException e) {
            return 0;
        }
    }
}
