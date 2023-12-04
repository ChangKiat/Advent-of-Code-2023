import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day3 {

    public static void main(String[] args) {
        String filePath = "C:/Users/chang-kiat.foo/Desktop/TEST/src/main/resources/day3InputValue.txt"; // Replace with the actual file path
        try {
            String input = readFile(filePath);

            String part1 = input.replaceAll("[^.\n\\r\u0085\u2028\u2029a-zA-Z0-9]+", "+");
            String part2= input.replaceAll("[^.*\n\r\u0085\u2028\u2029a-zA-Z0-9]+", ".");
            System.out.println(input);

            int totalAmount =0;

            //totalAmount =+ part2(part2);
            totalAmount =+part1(part1);
            System.out.println("Total Amount: "+ totalAmount);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static int part2(String part2){
        String[] inputArray = part2.split("\n");
        int totalAmount =0;
        for(int i = 0 ; i < inputArray.length;i ++) {
            for(int j=0 ; j <inputArray[i].length();j++) {
                if (inputArray[i].charAt(i) == '*') {
                    totalAmount =+ checkStarStartAndEnd(j+1, inputArray[i],i == 0? "":inputArray[i-1], i+1 > inputArray.length?"":inputArray[i+1]);

                }
            }
        }

        return totalAmount;
    }

    // NOt idea how to done the part 2
    private static int checkStarStartAndEnd(int index, String currentLine, String previousLine, String nextLine ){
        int multipleValue =0;
        int multipleValueB =1;



        return multipleValue * multipleValueB;
    }

    private static int part1(String part1){
        String[] inputArray = part1.split("\n");

        int totalAmount =0;
        for(int i = 0 ; i < inputArray.length;i ++) {

            String newArray = inputArray[i].replaceAll("\\+", ".").replaceAll("[^0-9.]", "");
            String[] lineNumeric = newArray.split("\\.");

            int start = 0;
            for (String numericNumber : lineNumeric) {

                if (numericNumber.isEmpty()) {
                    continue;
                }
                start = findStartPosition(inputArray[i], numericNumber, start);

                int end = findEndPosition(numericNumber, start);

                if (checkCharPoisition(start, end, inputArray[i])) {
                    System.out.println(numericNumber);
                    totalAmount += Integer.parseInt(numericNumber);
                    continue;

                }

                if (i + 1 < inputArray.length && checkNextRowStringPoisition(start, end, inputArray[i + 1])) {
                    System.out.println(numericNumber);

                    totalAmount += Integer.parseInt(numericNumber);
                    continue;

                }

                if (i != 0 && checkNextRowStringPoisition(start, end, inputArray[i - 1])) {
                    System.out.println(numericNumber);
                    totalAmount += Integer.parseInt(numericNumber);
                    continue;

                }
            }
        }
        return totalAmount;
    }

    private static Boolean checkCharPoisition(int start, int end , String input){
        int inputLength = input.length();
        return (inputLength > end +1 && input.charAt(end+1)=='+') || (start-1 >= 0 && input.charAt(start-1)=='+')  ;
    }



    private static Boolean checkNextRowStringPoisition(int start, int end , String input){
        int inputLength = input.length();

        if(start > 0){
            start = start-1;
        }
        if (end < inputLength){
            end=end+2;
        }

        String substringInput = input.substring(start, end);

        return substringInput.contains("+")  ;
    }

    private static int findStartPosition(String input, String subString, int start){
        int startPosition= input.indexOf(subString,start+1);

        if(input.length()> startPosition+subString.length()  ) {
            char ec = input.charAt(startPosition + subString.length());

            if (ec != '+' && ec != '.' && ec != '*'  && ec != '\r') {
                    startPosition = input.indexOf(subString, startPosition + 1);

            }
            if(startPosition > 0){
                char sc = input.charAt(startPosition -1);
                if (sc != '+' && sc != '.' && sc != '\r'  && sc != '*' ) {
                    if(startPosition != 0 ) {
                        startPosition = input.indexOf(subString, startPosition + 1);
                    }

                }
            }
        }
        return startPosition;
    }

    private static int findEndPosition(String subString, int startIndex){
        return subString.length()+startIndex-1;
    }

    private static String readFile(String filePath) throws Exception {
        Path path = Paths.get(filePath);
        byte[] bytes = Files.readAllBytes(path);
        return new String(bytes);
    }
}