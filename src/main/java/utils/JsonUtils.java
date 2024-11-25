package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class JsonUtils {

    public static void writeResultsToJson(Map<String, Object> results, String filename) {

        final File file = new File(filename);
        if (file.exists() && !file.delete()) {
            System.err.println("Failed to delete file " + filename);
        }

        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(results, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
