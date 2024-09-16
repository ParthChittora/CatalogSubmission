import java.math.BigInteger;
import java.util.*;

public class ShamirSecretSharing {
    public static BigInteger convertToDecimal(String value, int base) {
        return new BigInteger(value, base);
    }

    public static void main(String[] args) {
        Map<String, Map<String, String>> points = new HashMap<>();
        points.put("1", new HashMap<String, String>(){{put("base", "10"); put("value", "28735619723837");}});
        points.put("2", new HashMap<String, String>(){{put("base", "16"); put("value", "1A228867F0CA");}});
        points.put("3", new HashMap<String, String>(){{put("base", "12"); put("value", "32811A4AA0B7B");}});
        points.put("4", new HashMap<String, String>(){{put("base", "11"); put("value", "917978721331A");}});
        points.put("5", new HashMap<String, String>(){{put("base", "16"); put("value", "1A22886782E1");}});
        points.put("6", new HashMap<String, String>(){{put("base", "10"); put("value", "28735619654702");}});
        points.put("7", new HashMap<String, String>(){{put("base", "14"); put("value", "71AB5070CC4B");}});
        points.put("8", new HashMap<String, String>(){{put("base", "9"); put("value", "122662581541670");}});
        points.put("9", new HashMap<String, String>(){{put("base", "8"); put("value", "642121030037605");}});

        Map<String, BigInteger> decimalPoints = new HashMap<>();
        for (Map.Entry<String, Map<String, String>> entry : points.entrySet()) {
            String key = entry.getKey();
            Map<String, String> point = entry.getValue();
            int base = Integer.parseInt(point.get("base"));
            String value = point.get("value");
            decimalPoints.put(key, convertToDecimal(value, base));
        }

        List<List<String>> generateCombinations(List<String> arr, int k) {
            List<List<String>> results = new ArrayList<>();
            generateComb(arr, k, 0, new ArrayList<>(), results);
            return results;
        }

        void generateComb(List<String> arr, int k, int start, List<String> combo, List<List<String>> results) {
            if (combo.size() == k) {
                results.add(new ArrayList<>(combo));
                return;
            }
            for (int i = start; i < arr.size(); i++) {
                combo.add(arr.get(i));
                generateComb(arr, k, i + 1, combo, results);
                combo.remove(combo.size() - 1);
            }
        }

        BigInteger lagrangeInterpolation(List<BigInteger> x, List<BigInteger> y, BigInteger x0) {
            BigInteger total = BigInteger.ZERO;
            for (int i = 0; i < x.size(); i++) {
                BigInteger xi = x.get(i);
                BigInteger yi = y.get(i);
                BigInteger li = BigInteger.ONE;
                for (int j = 0; j < x.size(); j++) {
                    if (i != j) {
                        li = li.multiply(x0.subtract(x.get(j))).divide(xi.subtract(x.get(j)));
                    }
                }
                total = total.add(li.multiply(yi));
            }
            return total;
        }

        List<String> identifyIncorrectPoints(int k) {
            List<String> keys = new ArrayList<>(decimalPoints.keySet());
            List<BigInteger> xValues = new ArrayList<>();
            List<BigInteger> yValues = new ArrayList<>();
            for (String key : keys) {
                xValues.add(new BigInteger(key));
                yValues.add(decimalPoints.get(key));
            }

            List<String> incorrectPoints = new ArrayList<>();
            BigInteger minError = BigInteger.valueOf(Long.MAX_VALUE);

            List<List<String>> combinations = generateCombinations(keys, k);

            for (List<String> combo : combinations) {
                List<BigInteger> xCombo = new ArrayList<>();
                List<BigInteger> yCombo = new ArrayList<>();
                for (String key : combo) {
                    xCombo.add(new BigInteger(key));
                    yCombo.add(decimalPoints.get(key));
                }

                try {
                    BigInteger totalError = BigInteger.ZERO;
                    List<String> currentIncorrectPoints = new ArrayList<>();

                    for (String key : keys) {
                        if (!combo.contains(key)) {
                            BigInteger xTest = new BigInteger(key);
                            BigInteger yTest = decimalPoints.get(key);
                            BigInteger yPred = lagrangeInterpolation(xCombo, yCombo, xTest);
                            BigInteger error = yTest.subtract(yPred).abs();
                            totalError = totalError.add(error);
                            if (error.compareTo(BigInteger.ONE) > 0) { // Threshold for considering a point incorrect
                                currentIncorrectPoints.add(key);
                            }
                        }
                    }

                    if (totalError.compareTo(minError) < 0) {
                        minError = totalError;
                        incorrectPoints = currentIncorrectPoints;
                    }
                } catch (Exception e) {
                    // Handle errors in interpolation
                }
            }

            return incorrectPoints;
        }

        int kValue = 6; // Number of correct points required
        List<String> incorrectPoints = identifyIncorrectPoints(kValue);
        System.out.println("The incorrect points are: " + incorrectPoints);
    }
}
