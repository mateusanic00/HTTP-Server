package dev.mateus.config;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ConfigurationManager {
    public static Configuration configuration = new Configuration();
    public static void loadConfig() {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            FileReader fileReader = new FileReader("src/main/resources/http.json");
            Gson gson = new Gson();
            configuration = gson.fromJson(fileReader, Configuration.class);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
