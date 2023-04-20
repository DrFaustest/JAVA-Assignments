/*
NAME: Scott Faust
CLASS: 22SP_INFO_1531_WW
ASSIGNMENT: 5
DATE: 4/6/2023
RESOURCES: Lecture slides, StackOverflow, and Oracle documentation
Description: This program is a Pig Latin Converter that can convert a single word or sentence, a whole text file, or a quote from the internet.
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;

public class PigLatinConverter {
     public static void main(String[] args) {
        menu();

     }
     public static void menu() {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Welcome to the Pig Latin Converter!");
            while (true) {
                System.out.println("Please enter a number to select an option:");
                System.out.println("1) Convert a single word or sentence");
                System.out.println("2) Convert a whole text file");
                System.out.println("3) Convert a quote from the internet");
                System.out.println("4) Quit");
   
                try {
                    int choice = input.nextInt();
                    input.nextLine(); // consume end-of-line character

                    if (choice == 1) {
                        System.out.println("Please enter a word or sentence to be converted:");
                        String word = input.nextLine();
                        System.out.println("The converted word or sentence is: " + converter(word));
                    } else if (choice == 2) {
                        System.out.println("Please enter the file name:");
                        String fileName = input.nextLine();
                        System.out.println("The converted file is: " + fileHandler(fileName));
                    } else if (choice == 3) {
                        System.out.println("The converted quote is: " + apiQuote());
                    } else if (choice == 4) {
                        System.out.println("Thank you for using the Pig Latin Converter!");
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter a number 1-4.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number 1-4.");
                    input.nextLine(); // consume invalid input
                }
            }
        }
    }
    

     public static String converter(String word) {
        // check to see if the input is null or empty if so return an empty string
        if (word == null || word.trim().isEmpty()) {
            return "";
        }
        // create a new StringBuilder object to hold the converted word or sentence
        StringBuilder sb = new StringBuilder();
        String[] words = word.split("\\s+");
        // for each word in the input call the converter method and append the converted word to the StringBuilder object
        for (int i = 0; i < words.length; i++) {
            String currentWord = words[i];
            String[] subWords = currentWord.split("-");
            boolean capitalize = Character.isUpperCase(currentWord.charAt(0));
            // for each subword in the word call the converter method and append the converted subword to the StringBuilder object
            for (int j = 0; j < subWords.length; j++) {
                String subWord = subWords[j];
                boolean hasPunctuation = false;
                char lastChar = subWord.charAt(subWord.length() - 1);
                // check to see if the subword ends with punctuation if so remove the punctuation and set a flag to add the punctuation back later
                if (!Character.isLetterOrDigit(lastChar)) {
                    hasPunctuation = true;
                    subWord = subWord.substring(0, subWord.length() - 1);
                }
                // find the index of the first vowel in the subword
                String prefix = "";
                int vowelIndex = -1;
                // for each character in the subword check to see if it is a letter and if it is a vowel if so set the vowel index to the current index
                for (int k = 0; k < subWord.length(); k++) {
                    char c = subWord.charAt(k);
                    // if the character is a letter and the vowel index has not been set check to see if the character is a vowel
                    if (Character.isLetter(c) && (vowelIndex == -1)) {
                        if ("aeiouAEIOU".indexOf(c) != -1) {
                            vowelIndex = k;
                        }
                    }
                    // if the character is not a letter add it to the prefix
                    if (!Character.isLetterOrDigit(c)) {
                        prefix += c;
                    }
                }
            
                String convertedSubWord = "";
                // if the vowel index is -1 the subword does not contain any vowels so add "ay" to the end of the subword
                if (vowelIndex == -1) {
                    convertedSubWord = subWord + "ay";
                // if the vowel index is 0 the subword starts with a vowel so add "yay" to the end of the subword
                } else if (vowelIndex == 0) {
                    convertedSubWord = subWord + "yay";
                // if the vowel index is not 0 or -1 the subword starts with a consonant so move the consonants to the end of the subword and add "ay"
                } else {
                    convertedSubWord = subWord.substring(vowelIndex) + prefix + subWord.substring(0, vowelIndex) + "ay";
                }
                // if the subword was capitalized convert the first letter of the converted subword to uppercase
                if (capitalize) {
                    convertedSubWord = Character.toUpperCase(convertedSubWord.charAt(0)) + convertedSubWord.substring(1).toLowerCase();
                }
                // if the subword had punctuation add the punctuation back to the end of the converted subword
                if (hasPunctuation) {
                    convertedSubWord += lastChar;
                }
                // append the converted subword to the StringBuilder object
                sb.append(convertedSubWord).append(j < subWords.length - 1 ? "-" : "");
            }
            // append a space to the StringBuilder object if the word is not the last word in the sentence
            if (i < words.length - 1) {
                sb.append(" ");
            }
        }
        // return the converted word or sentence
        return sb.toString();
    }
    

        public static String fileHandler(String fileName) {
            // check to see if the file exists if not display an error message and return to the main menu
            File file = new File(fileName);
            if (file.exists() == false) {
                System.out.println("File not found.");
                return "";
            }
            // if the file exists create a new file object with the same name as the input file with "piglatin" added to the end of the name
            File outputFile = new File(fileName.substring(0, fileName.length() - 4) + "piglatin.txt");
            // for each line in the input file call the converter method and write the converted line to the output file
            try {
                Scanner input = new Scanner(file);
                PrintWriter output = new PrintWriter(outputFile);
                while (input.hasNextLine()) {
                    String line = input.nextLine();
                    output.println(converter(line));
                }
                input.close();
                output.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
            // return the name of the output file
            return outputFile.getName();
        }

        public static String apiQuote() {
            // create a new URL object with the URL of the API
            String url = "https://api.quotable.io/random";
            String quote = "";
            String author = "";
            // create a new HttpURLConnection object and set the request method to GET
            try {
                URL api = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) api.openConnection();
                connection.setRequestMethod("GET");
                // create a new BufferedReader object to read the response from the API
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                    StringBuilder responseBuilder = new StringBuilder();
                    String line;
                    // read each line of the response and append it to the responseBuilder
                    while ((line = reader.readLine()) != null) {
                        responseBuilder.append(line);
                    }
        
                    String response = responseBuilder.toString();

                    // Extract the value for the "content" key
                    int contentStartIndex = response.indexOf("\"content\":") + "\"content\":".length();
                    int contentEndIndex = response.indexOf(",\"author\":");
                    quote = response.substring(contentStartIndex, contentEndIndex);
                    quote = quote.replaceAll("\"", "").trim();
        
                    // Extract the value for the "author" key
                    int authorStartIndex = contentEndIndex + ",\"author\":".length();
                    int authorEndIndex = response.indexOf(",\"tags\":");
                    author = response.substring(authorStartIndex, authorEndIndex);
                    author = author.replaceAll("\"", "").trim();
                }
                // close the connection
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // return the quotes and author
            return quote + "\n" + converter(quote) +"\n" + author ;
        }
}


/*Response from the API call for reference
 * {
    "_id": "b-zLcfguzkk7",
    "content": "When you are content to be simply yourself and don't compare or compete, everybody will respect you.",
    "author": "Laozi",
    "tags": [
        "wisdom"
    ],
    "authorSlug": "laozi",
    "length": 100,
    "dateAdded": "2020-05-21",
    "dateModified": "2022-07-04"
}
 */