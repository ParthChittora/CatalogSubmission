import java.math.BigInteger;
import java.util.Map;

public class Main {
    
    public static void main(String[] args) {
        String jsonInput = "{\"keys\": {\"n\": 4, \"k\": 3}, \"1\": {\"base\": \"16\", \"value\": \"4\"}, \"2\": {\"base\": \"2\", \"value\": \"111\"}, \"3\": {\"base\": \"16\", \"value\": \"12\"}, \"6\": {\"base\": \"4\", \"value\": \"213\"}}";
        Map<Integer, BigInteger> shares = parseInput(jsonInput);
        BigInteger secret = reconstructSecret(shares, 3);  // k = 3
        System.out.println("The reconstructed secret is: " + secret);
    }
    }
