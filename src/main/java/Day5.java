import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day5 {
    public static void main(String[] args) {
        String filePath = "C:/Users/chang-kiat.foo/Desktop/TEST/src/main/resources/day5InputValue.txt"; // Replace with the actual file path
        try {
            String input = readFile(filePath);
            String[] inputArray = input.split("[\n\r]+");
            //Map<String,String> seed = splitTheData(inputArray[0]);
            //Map<String,String> seedPart2 = splitTheDataPart2(inputArray[0]);

            LinkedHashMap<String, String[]> number = new LinkedHashMap<>();
            String mapName = "";
            String[] currentArray = null;

            for(int i =1 ; i< inputArray.length;i++){
                if(isAlphabetic(inputArray[i])){
                    mapName = inputArray[i];
                    currentArray = new String[0]; // Initialize an empty array for the current key
                }else{
                    currentArray = addToCurrentArray(currentArray, inputArray[i]);

                    number.put(mapName,currentArray);
                }

            }
            //System.out.println("Total point Part 1: "+ part1Solution(seed,number));
            System.out.println("Total point Part 2: "+  splitTheDataPart2(inputArray[0],number));

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    private static boolean isAlphabetic(String str) {
        return str.matches("[a-zA-Z\\s:-]+");
    }

    private static Long part1Solution(Map<String,String> seeds, Map<String,String[]> itemMap){
        Long lowerLocation =0L;

        for(Map.Entry<String, String> seed : seeds.entrySet()) {
            Long seedNumber = Long.parseLong(seed.getKey());
            for (Map.Entry<String, String[]> entry : itemMap.entrySet()) {
                String[] values = entry.getValue();
                for(String value:values) {
                    seeds.put(seed.getKey(), seed.getValue());
                    String[] numbers = value.split(" ");
                    if (seedNumber >= Long.parseLong(numbers[1]) && seedNumber <= (Long.parseLong(numbers[2]) + Long.parseLong(numbers[1]))) {
                        Long newValue = seedNumber - Long.parseLong(numbers[1]);
                        seedNumber = newValue+Long.parseLong(numbers[0]);
                        seeds.put(seed.getKey(),Long.toString( seedNumber));
                        break;
                    }
                }
            }
        }

        for(Map.Entry<String, String> seed : seeds.entrySet()) {
            if(lowerLocation==0){
                lowerLocation = Long.parseLong(seed.getValue());
            }else if(lowerLocation> Long.parseLong(seed.getValue())){
                lowerLocation = Long.parseLong(seed.getValue());
            }
        }
            return lowerLocation;
    }
    private static String part2Solution(Long seed, Map<String,String[]> itemMap){
        String lowerLocation = null;

            for (Map.Entry<String, String[]> entry : itemMap.entrySet()) {
                String[] values = entry.getValue();
                for(String value:values) {
                    String[] numbers = value.split(" ");
                    if (seed >= Long.parseLong(numbers[1]) && seed <= (Long.parseLong(numbers[2]) + Long.parseLong(numbers[1]))) {
                        Long newValue = seed - Long.parseLong(numbers[1]);
                        seed = newValue+Long.parseLong(numbers[0]);
                        lowerLocation= Long.toString( seed);
                        break;
                    }
                }
            }

        return lowerLocation;
    }

    private static String[] addToCurrentArray(String[] currentArray, String value) {
        // Extend the current array by one element
        String[] newArray = new String[currentArray.length + 1];
        System.arraycopy(currentArray, 0, newArray, 0, currentArray.length);
        newArray[currentArray.length] = value;
        return newArray;
    }

    private static Map<String,String> splitTheData (String inputArray){
        LinkedHashMap<String, String> category = new LinkedHashMap<>();
        for(String input :  inputArray.substring(7).split(" ")){
            category.put(input,"");
        }
        return category;
    }

    private static String splitTheDataPart2(String inputArray,Map<String,String[]> number) {
        String lowestLocation = null;

        String[] inputArrays = inputArray.substring(7).split(" ");
        for (int i = 0; i < inputArrays.length; i += 2) {
            for (Long j = 0L; j < Long.parseLong(inputArrays[i + 1]); j++) {
                    lowestLocation = part2Solution(Long.parseLong(inputArrays[i]) + j, number);
                System.out.println("Number"+ lowestLocation);

            }
        }

        return lowestLocation;
    }
    private static String readFile(String filePath) throws Exception {
        Path path = Paths.get(filePath);
        byte[] bytes = Files.readAllBytes(path);
        return new String(bytes);
    }
}
