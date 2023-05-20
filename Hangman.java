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
                        "/|\\  |\n" + //if you were wondering, the only way to print '\' is with a trailing escape character, which also happens to be '\'
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

                char[] placeHolders = new char[randomWord.length()]; //създаваме чар масив и като нейна стойност слагаме дъльината на избраната от функцията шано дума
                for (int i = 0; i < placeHolders.length; i++) {      //правим цикъл който да обхожда масива и да задава по дължината на думата стойност на знаците '_'
                        placeHolders[i] = '_';                       //реално масива разделя стринга от думи на отделни знаци, за да могат да бъдат манипулирани
                }//есенчъли разделя думата на букви

                int misses = 0;// начална стойност на сгрешените букви

                char[] missedGuess = new char[6]; // масив от грешките

                while(misses < 6){
                        System.out.println(gallows[misses]);

                        System.out.println("Word: " );
                        printPlaceHolders(placeHolders);//това принтира избраната дума скрита
                        System.out.println("\n");

                        System.out.println("Misses: ");
                        printMisses(missedGuess);//това принтира възможните опити за грешки
                        System.out.println("\n\n");

                        System.out.println("Guess: ");
                        char guess = scanner.nextLine().charAt(0);// това приема и позвилява на играча да въвежда знаци
                        System.out.println("\n");

                        if(checkGuess(randomWord, guess)){
                                updatePlaceHolders(randomWord, placeHolders, guess);
                        } else {
                                missedGuess[misses] = guess;
                                misses++;
                        }
                        if(Arrays.equals(placeHolders, randomWord.toCharArray())){
                                System.out.println(gallows[misses]);//тази проверка чеква дали скритата дума е равна на знаците който си въвел и принтира колко грешки си направил докато
                                System.out.println("\nWord:  ");//познаеш и принтира и съответно отговарящото на броя грешки бесило и принтира думата
                                printPlaceHolders(placeHolders);
                                System.out.println("Good Work!");
                                break;
                        }
                }
                if (misses == 6){
                        System.out.println(gallows[6]); //тази проверка проверява дали грешките са 6 и ако са 6 принтира финалното бесило, РИП и разкрива думата
                        System.out.println("\nRIP!");
                        System.out.println("\nThe word was: '" + randomWord + "'");
                }
                scanner.close();
        }
        public static String randomWord(String[] words){
                int numWords = words.length;  //пррооменлива която е равна на дължината на на думата
                double randomDouble = Math.random();// общо взето тази функция избира дума на шано
                int randomIndex = (int) (randomDouble * numWords);
                return words[randomIndex]; 
        }
        public static boolean checkGuess(String word, char guess){

                for (int i = 0; i < word.length(); i++) {// цикъл който обхожда думата и сравнява със зададената от играча стойност и връща true или false
                        if(word.charAt(i) == guess){
                                return true;
                        }
                }


                return false;
        }
        public static void updatePlaceHolders(String word, char[] placeHolders, char guess){
                for (int i = 0; i < word.length(); i++) {// тази функция обновява долните черти
                        if(word.charAt(i) == guess){
                                placeHolders[i] = guess;
                        }
                }
        }
        public static void printPlaceHolders(char[] placeHolders){
                for (int i = 0; i < placeHolders.length; i++) {
                        System.out.print(" " + placeHolders[i]);//тази функция принтира верните предположения на правилните места
                }
                System.out.println("\n");
        }
        public static void printMisses(char[] misses){
                for (int i = 0; i < misses.length; i++) {
                        System.out.print(misses[i]);//тази функция принтира грешките
                }

        }
}
