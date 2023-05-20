package section_6;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Hangman {
        public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
                "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
                "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
                "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
                "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon",
                "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
                "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
                "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
                "wombat", "zebra"};
        public static String[] gallows = {
                "+---+\n" +
                "|   |\n" +
                "    |\n" +
                "    |\n" +
                "    |\n" +
                "    |\n" +
                "=========\n",

                "+---+\n" +
                        "|   |\n" +
                        "O   |\n" +
                        "    |\n" +
                        "    |\n" +
                        "    |\n" +
                        "=========\n",

                "+---+\n" +
                        "|   |\n" +
                        "O   |\n" +
                        "|   |\n" +
                        "    |\n" +
                        "    |\n" +
                        "=========\n",

                " +---+\n" +
                        " |   |\n" +
                        " O   |\n" +
                        "/|   |\n" +
                        "     |\n" +
                        "     |\n" +
                        " =========\n",

                " +---+\n" +
                        " |   |\n" +
                        " O   |\n" +
                        "/|\\  |\n" + 
                        "     |\n" +
                        "     |\n" +
                        " =========\n",

                " +---+\n" +
                        " |   |\n" +
                        " O   |\n" +
                        "/|\\  |\n" +
                        "/    |\n" +
                        "     |\n" +
                        " =========\n",

                " +---+\n" +
                        " |   |\n" +
                        " O   |\n" +
                        "/|\\  |\n" +
                        "/ \\  |\n" +
                        "     |\n" +
                        " =========\n"};

        public static void main(String[] args) {


                Scanner scanner = new Scanner(System.in);
                String randomWord = randomWord(words);

                char[] placeHolders = new char[randomWord.length()]; 
                for (int i = 0; i < placeHolders.length; i++) {      
                        placeHolders[i] = '_';                       
                }

                int misses = 0;

                char[] missedGuess = new char[6]; 

                while(misses < 6){
                        System.out.println(gallows[misses]);

                        System.out.println("Word: " );
                        printPlaceHolders(placeHolders);
                        System.out.println("\n");

                        System.out.println("Misses: ");
                        printMisses(missedGuess);
                        System.out.println("\n\n");

                        System.out.println("Guess: ");
                        char guess = scanner.nextLine().charAt(0);
                        System.out.println("\n");

                        if(checkGuess(randomWord, guess)){
                                updatePlaceHolders(randomWord, placeHolders, guess);
                        } else {
                                missedGuess[misses] = guess;
                                misses++;
                        }
                        if(Arrays.equals(placeHolders, randomWord.toCharArray())){
                                System.out.println(gallows[misses]);
                                System.out.println("\nWord:  ");
                                printPlaceHolders(placeHolders);
                                System.out.println("Good Work!");
                                break;
                        }
                }
                if (misses == 6){
                        System.out.println(gallows[6]); 
                        System.out.println("\nRIP!");
                        System.out.println("\nThe word was: '" + randomWord + "'");
                }
                scanner.close();
        }
        public static String randomWord(String[] words){
                int numWords = words.length;  
                double randomDouble = Math.random();
                int randomIndex = (int) (randomDouble * numWords);
                return words[randomIndex]; 
        }
        public static boolean checkGuess(String word, char guess){

                for (int i = 0; i < word.length(); i++) {
                        if(word.charAt(i) == guess){
                                return true;
                        }
                }


                return false;
        }
        public static void updatePlaceHolders(String word, char[] placeHolders, char guess){
                for (int i = 0; i < word.length(); i++) {
                        if(word.charAt(i) == guess){
                                placeHolders[i] = guess;
                        }
                }
        }
        public static void printPlaceHolders(char[] placeHolders){
                for (int i = 0; i < placeHolders.length; i++) {
                        System.out.print(" " + placeHolders[i]);
                }
                System.out.println("\n");
        }
        public static void printMisses(char[] misses){
                for (int i = 0; i < misses.length; i++) {
                        System.out.print(misses[i]);
                }

        }
}
