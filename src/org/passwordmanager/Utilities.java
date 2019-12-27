package org.passwordmanager;

import java.util.Stack;

public class Utilities {

    static void processStack(int minimumCharsRequired, char[] charArray, Stack<Character> charStack,
                                     String invalidCharacters) {
        for (int i = 1; i <= minimumCharsRequired; i++){
            while (true){
                char character = charArray[r.nextInt(charArray.length)];
                if (!isCharInString(character, invalidCharacters)){
                    charStack.push(character);
                    break;
                }
            }
        }
    }
    static int generateRemainingCharacterNumber(int minimumCapsRequired, int minimumDigitsRequired,
                                                int minimumSymbolsRequired, int minimumLength, int maximumLength) {
        int requiredCharacterCount = minimumCapsRequired + minimumDigitsRequired + minimumSymbolsRequired;
        if (requiredCharacterCount >= minimumLength) return randomInteger(minimumLength - requiredCharacterCount,
                maximumLength - minimumLength);
        else return randomInteger(maximumLength - requiredCharacterCount,
                minimumLength - requiredCharacterCount - 1);
    }

    static int randomInteger(int upper, int lower){
        return ((((int)(r.nextDouble() * 10)) % (upper - lower)) + (lower));
    }

    static boolean isCharInString(char c, String s){
        for (int i = 0; i < s.length(); i++) if (c == s.charAt(i)) return true;
        return false;
    }
}
