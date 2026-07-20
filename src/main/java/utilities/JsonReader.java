package utilities;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import models.UserData;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonReader {

	private static UserData data;

    static {
        try {
            FileReader reader = new FileReader("./src/test/resources/userData.json");
            // GSON automatically converts the entire JSON file into our Java object structure
            data = new Gson().fromJson(reader, UserData.class);
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Critical Error: Failed to parse user_data.json matrix.", e);
        }
    }

    public static UserData.UserProfile getValidUser() {
        return data.validUser;
    }
    
    public static UserData.InvalidProfile getInvalidUser() {
        return data.invalidUser;
    }
    
}
