import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

class Day1 {
    public static void main(String[] args) {
        String filePath = "C:/Users/chang-kiat.foo/Desktop/TEST/src/main/resources/day1InputValue.txt"; // Replace with the actual file path

        try {
            String input = readFile(filePath);

            String[] inputArray = input.split("\n");
            int total =0;

            for(String inputColumn: inputArray){
                inputColumn = conver2rdsToNumbers(inputColumn);
                inputColumn =convertWordsToNumbers(inputColumn);
                inputColumn = inputColumn.replaceAll("[^0-9]", "");
                int inputLength = inputColumn.length();
                char firstNumeric = inputColumn.charAt(0);
                char lastNumeric = firstNumeric;
                if(inputLength !=1 )
                    lastNumeric = inputColumn.charAt(inputLength - 1);

                inputColumn = firstNumeric+ ""+ lastNumeric;
                total += Integer.parseInt(inputColumn);
            }
            System.out.println("Part 2: Result: "+ total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readFile(String filePath) throws Exception {
        Path path = Paths.get(filePath);
        byte[] bytes = Files.readAllBytes(path);
        return new String(bytes);
    }

    private static String conver2rdsToNumbers(String input) {
        // Define the mapping for "1" to "9", "2" to "8", and so on
        Map<String, String> wordToString = new HashMap<>();
        wordToString.put("oneight", "oneeight");
        wordToString.put("twone", "twoone");
        wordToString.put("threeight", "threeeight");
        wordToString.put("fiveight", "fiveeight");
        wordToString.put("sevenine", "sevennine");
        wordToString.put("eighthree", "eightthree");
        wordToString.put("eightwo", "eighttwo");
        wordToString.put("nineight", "nineeight");
        for (Map.Entry<String, String> entry : wordToString.entrySet()) {
            String word = entry.getKey();
            String numericValue = entry.getValue();
            input = input.replace(word, numericValue);

        }
        return input;
    }
    private static String convertWordsToNumbers(String input) {
        // Define the mapping for words to numbers
        Map<String, String> wordToNumber = new HashMap<>();
        wordToNumber.put("one", "1");
        wordToNumber.put("two", "2");
        wordToNumber.put("three", "3");
        wordToNumber.put("four", "4");
        wordToNumber.put("five", "5");
        wordToNumber.put("six", "6");
        wordToNumber.put("seven", "7");
        wordToNumber.put("eight", "8");
        wordToNumber.put("nine", "9");

        // Replace each word with its numeric equivalent
        for (Map.Entry<String, String> entry : wordToNumber.entrySet()) {
            String word = entry.getKey();
            String numericValue = entry.getValue();
            input = input.replace(word, numericValue);
        }
        return input;
    }
}