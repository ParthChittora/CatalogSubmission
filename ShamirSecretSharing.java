import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import java.math.BigInteger;

public class ShamirSecretSharing {
    public static BigInteger reconstructSecret1(Map<Integer, BigInteger> shares, int k) {
        BigInteger secret = BigInteger.ZERO;

        for (Map.Entry<Integer, BigInteger> share1 : shares.entrySet()) {
            BigInteger xi = BigInteger.valueOf(share1.getKey());
            BigInteger yi = share1.getValue();

            BigInteger numerator = BigInteger.ONE;
            BigInteger denominator = BigInteger.ONE;

            for (Map.Entry<Integer, BigInteger> share2 : shares.entrySet()) {
                if (!share1.getKey().equals(share2.getKey())) {
                    BigInteger xj = BigInteger.valueOf(share2.getKey());
                    numerator = numerator.multiply(xj.negate());  // (0 - xj)
                    denominator = denominator.multiply(xi.subtract(xj));  // (xi - xj)
                }
            }

            BigInteger lagrangeTerm = yi.multiply(numerator).multiply(denominator.modInverse(BigInteger.valueOf(256)));
            secret = secret.add(lagrangeTerm).mod(BigInteger.valueOf(256)); // assuming modulo 256 for byte representation
        }

        return secret;
    }

    // Method to parse JSON input
    public static Map<Integer, BigInteger> parseInput(String jsonInput) {
        Gson gson = new Gson();
        Map<String, Object> data = gson.fromJson(jsonInput, Map.class);

        Map<Integer, BigInteger> shares = new HashMap<>();
        Map<String, Object> keys = (Map<String, Object>) data.get("keys");

        int n = ((Double) keys.get("n")).intValue();
        int k = ((Double) keys.get("k")).intValue();

        System.out.println("Total shares (n): " + n);
        System.out.println("Threshold (k): " + k);

        for (String key : data.keySet()) {
            if (key.equals("keys")) continue; // Skip the "keys" entry
            Map<String, String> shareData = (Map<String, String>) data.get(key);
            String base = shareData.get("base");
            String value = shareData.get("value");

            BigInteger shareValue = new BigInteger(value, Integer.parseInt(base));
            shares.put(Integer.parseInt(key), shareValue);
        }

        return shares;
    }

    public static BigInteger reconstructSecret(Map<Integer, BigInteger> shares, int k) {
        return reconstructSecret1(shares, k);
    }

    public static void processFile(String filePath) {
        try {
            String jsonInput = new String(Files.readAllBytes(Paths.get(filePath)));
            Map<Integer, BigInteger> shares = parseInput(jsonInput);
            int k = ((Map<String, Object>) new Gson().fromJson(jsonInput, Map.class).get("keys")).get("k");
            BigInteger secret = reconstructSecret(shares, k.intValue());

            System.out.println("Constant term (Secret): " + secret);
        } catch (IOException e) {
            System.err.println("Error: Invalid JSON or processing error.");
        }
    }

    public static void main(String[] args) {
        processFile("your_json_file.json");
    }
}
