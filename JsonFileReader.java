import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class JsonFileReader {
    public static void main(String[] args) {
        Gson gson = new Gson();
        try {
            // Create a FileReader object for the JSON file
            FileReader reader = new FileReader("data.json");

            // Parse the JSON file into a JsonObject
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

            // Extract 'keys'
            JsonObject keysObject = jsonObject.getAsJsonObject("keys");
            int n = keysObject.get("n").getAsInt();
            int k = keysObject.get("k").getAsInt();

            System.out.println("n: " + n);
            System.out.println("k: " + k);

            // Extract other elements
            for (Map.Entry<String, ?> entry : jsonObject.entrySet()) {
                if (!entry.getKey().equals("keys")) {
                    JsonObject item = (JsonObject) entry.getValue();
                    String base = item.get("base").getAsString();
                    String value = item.get("value").getAsString();
                    System.out.println("Key: " + entry.getKey() + ", Base: " + base + ", Value: " + value);
                }
            }

            // Close the FileReader
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
