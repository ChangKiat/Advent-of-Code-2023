import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Day4 {

    public static void main(String[] args) {
        String filePath = "C:/Users/chang-kiat.foo/Desktop/TEST/src/main/resources/day4InputValue.txt"; // Replace with the actual file path
        try {
            String input = readFile(filePath);
            String[] inputArray = input.split("\n");
            int instances =1;




            System.out.println("Total point Part 1: "+ part1Solution(inputArray));
            System.out.println("Total point Part 2: "+ part2Solution(inputArray));

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static int part2Solution(String[] inputArray){
        int totalinstances = 0;
        int[] newArray = new int[inputArray.length+100];

        for(int k =0 ; k< inputArray.length;k++){
            newArray[k]++;
            int instances =0;

            int colonIndex = inputArray[k].indexOf(':');
            inputArray[k] = inputArray[k].substring(colonIndex + 2);

            String[] gameString =  inputArray[k].split("\\| ");
            int point = 0;

            String[] game1 = gameString[0].trim().split("\\s+");
            String[] game2 = gameString[1].trim().split("\\s+");
            for(int i =0 ; i< game1.length  ;i++){
                for(int j = 0; j<game2.length;j++){
                    if(game1[i].equals(game2[j])) {
                        instances++;
                    }
                }
            }

            for (int i = 0; i < newArray[k]; i++) {
                for (int h = 1; h < instances + 1; h++) {
                    newArray[k + h]++;
                }
            }

        }
        for (int number : newArray) {
            totalinstances += number;
        }


        return totalinstances;
    }

    private static int part1Solution(String[] inputArray){
        int totalAmount =0;

        for(String inputString : inputArray){
            int colonIndex = inputString.indexOf(':');
            inputString = inputString.substring(colonIndex + 2);

            String[] gameString =  inputString.split("\\| ");
            int point = 0;

            String[] game1 = gameString[0].trim().split("\\s+");
            String[] game2 = gameString[1].trim().split("\\s+");
            for(int i =0 ; i< game1.length  ;i++){
                for(int j = 0; j<game2.length;j++){
                    if(game1[i].equals(game2[j])){
                        if(point == 0){
                            point++;
                        }else{
                            point = point*2 ;
                        }
                    }
                }
            }
            totalAmount= totalAmount+point;
        }
        return totalAmount;
    }


    private static String readFile(String filePath) throws Exception {
        Path path = Paths.get(filePath);
        byte[] bytes = Files.readAllBytes(path);
        return new String(bytes);
    }
}
