/**
 * Simple test file for MorseCode Converter
 * 
 * @author Luke.Kelly
 */
public class TestFile {
    public static void main(String[] args) {

        String toBeConverted;

        System.out.println("From text to Morse\n");
        toBeConverted = "Hello my name is Luke Kelly";
        outputToConsole(toBeConverted, false);
        toBeConverted = "The quick brown fox jumped over the lazy dog";
        outputToConsole(toBeConverted, false);

        System.out.println("\nNow from morse to text\n");
        // The quick brown fox jumped over the lazy dog
        toBeConverted = "- .... . / --.- ..- .. -.-. -.- / -... .-. --- .-- -. / ..-. --- -..- / .--- ..- -- .--. . -.. / --- ...- . .-. / - .... . / .-.. .- --.. -.-- / -.. --- --.";
        outputToConsole(toBeConverted, true);
        // Hello my name is Luke Kelly
        toBeConverted = ".... . .-.. .-.. --- / -- -.-- / -. .- -- . / .. ... / .-.. ..- -.- . / -.- . .-.. .-.. -.--";
        outputToConsole(toBeConverted, true);
    }

    public static void outputToConsole(String toBeConverted, boolean isMorse) {
        String textAfter;
        if (isMorse) {
            textAfter = MorseCode.convertMorseToText(toBeConverted);
        } else {
            textAfter = MorseCode.convertTextToMorse(toBeConverted);
        }
        String output = String.format("String before: %s\n" + 
                                      "String after:  %s",
                                      toBeConverted, textAfter);
        System.out.println(output);

    }
}