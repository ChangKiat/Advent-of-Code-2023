import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day2 {
    public static void main(String[] args) {
        String filePath = "C:/Users/chang-kiat.foo/Desktop/TEST/src/main/resources/day2InputValue.txt"; // Replace with the actual file path
        try {
            String input = readFile(filePath);
            String[] inputArray = input.split("\n");

            int totalId =0;
            for(String inputColumn: inputArray){
                int gameId = getGameId(inputColumn);
                int colonIndex = inputColumn.indexOf(':');
                inputColumn = inputColumn.substring(colonIndex + 1).trim();
                String[] game = inputColumn.split(";");
/*
                if(solveGame(game)){
                    totalId+=gameId;

                }*/
                totalId += solveGamePart2(game);
            }
        System.out.println(totalId);
        }catch (Exception ex){

        }
    }
    private static boolean solveGame(String[] input) {
        int redCubes = 0;
        int blueCubes = 0;
        int greenCubes = 0;

        for (String s : input) {

            String[] parts = s.split(",");
            for (String part : parts) {
                if (part.contains("red")) {
                    String item =part.replaceAll("[^0-9]", "");
                    if(redCubes==0 ||redCubes <Integer.parseInt(item)) {
                        redCubes = +Integer.parseInt(item);
                    }

                } else if (part.contains("blue")) {
                    String item =part.replaceAll("[^0-9]", "");
                    if(blueCubes==0 || blueCubes < Integer.parseInt(item)) {
                        blueCubes = +Integer.parseInt(item);
                    }
                }else if (part.contains("green")) {
                    String item =part.replaceAll("[^0-9]", "");
                    if(greenCubes==0 || greenCubes < Integer.parseInt(item)) {
                        greenCubes = +Integer.parseInt(item);
                    }
                }
            }
        }
        return redCubes <=12 && greenCubes <= 13 && blueCubes <=14;
    }
    private static int solveGamePart2(String[] input) {
        int redCubes = 0;
        int blueCubes = 0;
        int greenCubes = 0;

        for (String s : input) {

            String[] parts = s.split(",");
            for (String part : parts) {
                if (part.contains("red")) {
                    String item =part.replaceAll("[^0-9]", "");
                    if(redCubes==0 ||redCubes <Integer.parseInt(item)) {
                        redCubes = +Integer.parseInt(item);
                    }

                } else if (part.contains("blue")) {
                    String item =part.replaceAll("[^0-9]", "");
                    if(blueCubes==0 || blueCubes < Integer.parseInt(item)) {
                        blueCubes = +Integer.parseInt(item);
                    }
                }else if (part.contains("green")) {
                    String item =part.replaceAll("[^0-9]", "");
                    if(greenCubes==0 || greenCubes < Integer.parseInt(item)) {
                        greenCubes = +Integer.parseInt(item);
                    }
                }
            }
        }
        return redCubes *greenCubes* blueCubes;
    }
    private static int getGameId(String input){
        String[] gameString = input.split(":");
        String gameId =gameString[0].replaceAll("[^0-9]", "");
        return Integer.parseInt(gameId);

    }
    private static String readFile(String filePath) throws Exception {
        Path path = Paths.get(filePath);
        byte[] bytes = Files.readAllBytes(path);
        return new String(bytes);
    }
}
