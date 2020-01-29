import java.util.*;

public class MorseCode {
    static String[] MorseLetters = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-",
            ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--",
            "--.." };
    static String[] MorseDigits = { "-----", ".----", "..--", "...--", "....-", ".....", "-....", "--...", "--..",
            "---." };

    // ************************FROM TEXT TO MORSE*******************************/
    /**
     * Converts a single line into morse code.
     * 
     * @param s The string you want converted
     * @throws IllegalArgumentException if there is something in the string it can't
     *                                  convert.(Not Alpha||Not Digit||Not Space.)
     */
    public static String convertTextToMorse(String s) {
        StringBuffer output = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);
            letter = Character.toUpperCase(letter);
            if (canConvertToMorse(letter)) {
                output.append(" " + convertCharToMorse(letter));
            } else {
                throw new IllegalArgumentException("\"" + letter + "\" Can not be converted to morse code");
            }
        }
        return output.toString();
    }

    /**
     * Converts a singular Char to morse
     * 
     * @param c the char you want to convert
     * @return the string value of that char in morse code
     */
    private static String convertCharToMorse(Character c) {
        // Check if it is a letter
        if (Character.isLetter(c)) {
            /*
             * If the char is a letter, subtract 'A' that char's value that int value is the
             * index of that item in the list
             */
            int index = (int) (c - 'A');
            return MorseLetters[index];
        } else if (c == ' ') {
            // Special case for space.
            return "/";
        } else {
            /*
             * We know to get to this point the char is a digit. Subtract '0' from the char
             * to get the index in the list of that char
             */
            int index = (int) (c - '0');
            return MorseDigits[index];
        }
    }

    /**
     * Checks if the char can be converted to Morse with the current data
     * 
     * @param letter the char you want to check
     * @return true if it can false if it can't
     */
    private static boolean canConvertToMorse(Character letter) {
        return (Character.isLetter(letter) || Character.isDigit(letter) || letter == ' ');
    }

    // ************************FROM MORSE TO TEXT*******************************/
    /**
     * Convert from Morse to String.
     * 
     * @param s the String of morse code you want to convert.
     * @return a String of Text in all caps that represents the Morse Code.
     */
    public static String convertMorseToText(String s) {
        StringBuffer output = new StringBuffer();
        Scanner processing = new Scanner(s);
        while (processing.hasNext()) {
            // Grab the next letter
            String letter = processing.next();
            //Holds the char we are going to add to the output string.
            char addToOutput;
            /*
             * Check if it is a space. While this is rare if it is true it saves me a lot of
             * time because then I don't have to call all the other methods
             */
            if (letter.equals("/")) {
                addToOutput = ' ';
            } else {// If not a space
                // Find the index in the array
                /*
                 * My Arrays are setup in ascending order just like they are on the unicode
                 * table. So if the letter is A then I will get back the index that it is in.
                 * Which is 0. The Unicode value for A is 65 65+0 = 65. So if I create a char of
                 * value 65 it will convert to a 'A' when printed or added to a string. If I get
                 * a negative number then I know it is in the Digits Array. I change the sign
                 * again and then add 48 to it because 48 is the Unicode value of 0.
                 * 
                 */
                int index = convertToText(letter);
                if (index < 0) {
                    index *= -1;
                    addToOutput = (char) ('0' + index);
                } else {
                    addToOutput = (char) ('A' + index);
                }
            }
            output.append(addToOutput);
        }
        processing.close();
        return output.toString();
    }

    /**
     * Converts a single letter to morse code.
     * 
     * @param letter The letter you want to convert to Morse
     * @return the index value of that letter in the arrays. The number will be
     *         negative if it is in the MorseDigits array.
     * @throws IllegalArgumentException if the number can not be converted with the
     *                                  current data.
     */
    private static int convertToText(String letter) {
        for (int i = 0; i < MorseLetters.length; i++) {
            /*
             * Look through all of the Strings in MorseLetter array to see if they are the
             * same as the morse letter. If so return that index.
             */
            if (MorseLetters[i].equals(letter)) {
                return i;
            }
            // Check if our index is a possible index for MorseDigits array.
            if (i < MorseDigits.length) {
                /*
                 * If it is look through all of the Strings in MorseDigits array
                 * to see if they are the same as the morse letter. 
                 * If so return that index * -1 to signify that it is a Digit.
                 */
                if (MorseDigits[i].equals(letter)) {
                    return i * -1;
                }
            }
        }
        //The given code does not exist in either array, throw error.
        throw new IllegalArgumentException("\"" + letter + "\" Can not be converted to morse code");
    }

}
