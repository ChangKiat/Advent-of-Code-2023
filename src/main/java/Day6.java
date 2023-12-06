import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6 {
    public static void main(String[] args) {
        String filePath = "C:/Users/chang-kiat.foo/Desktop/TEST/src/main/resources/day6InputValue.txt"; // Replace with the actual file path
        try {
            String input = readFile(filePath);
            String[] inputArray = input.split("\n");
            List<String> timeValues = extractValues(input, "Time:\\s*(\\d+\\s*\\d+\\s*\\d+\\s*\\d+)");
            List<String> distanceValues = extractValues(input, "Distance:\\s*(\\d+\\s*\\d+\\s*\\d+\\s*\\d+)");

            List<String> timeValues2 = extractValues2(timeValues);
            List<String> distanceValues2 = extractValues2(distanceValues);

            System.out.println("Total point Part 1: "+ part1Solution(timeValues,distanceValues));
            System.out.println("Total point Part 2: "+ part1Solution(timeValues2,distanceValues2));

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    private static List<String> extractValues2(List<String>  input) {
        List<String> values = new ArrayList<>();
        StringBuilder concatenated = new StringBuilder();
        for (String value : input) {
            // Removing spaces and concatenating
            concatenated.append(value.replaceAll("\\s", ""));
        }
        values.add( concatenated.toString());

        return values;
    }

    private static List<String> extractValues(String input, String pattern) {
        List<String> values = new ArrayList<>();
        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(input);

        if (matcher.find()) {
            // Extracting the matched group (containing the values)
            String valuesString = matcher.group(1);

            // Splitting the values and adding them to the list
            values.addAll(Arrays.asList(valuesString.split("\\s+")));
        }

        return values;
    }

    private static int part1Solution(List<String> timeValues, List<String> distanceValues){
        int totalCombination = 1;

        for(int time=0 ; time< timeValues.size() ; time ++){
            Long timeLeft =0L;
            Long disctanceMove = Long.parseLong(distanceValues.get(time));
            Long totalTime = Long.parseLong(timeValues.get(time));
            int combination =0 ;
            for(int i = 1; i<totalTime; i++){
                timeLeft = totalTime -i;
                if( i*timeLeft >  disctanceMove){
                    combination++;
                }
            }
            totalCombination = totalCombination*combination;
        }
        return totalCombination;
    }
    
    private static String readFile(String filePath) throws Exception {
        Path path = Paths.get(filePath);
        byte[] bytes = Files.readAllBytes(path);
        return new String(bytes);
    }
}
