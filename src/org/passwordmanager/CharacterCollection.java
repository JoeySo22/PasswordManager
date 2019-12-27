package org.passwordmanager;

public class CharacterCollection {
    static final char[] DIGIT_CHARS_ARRAY = {'0', '1', '2', '3', '4', '5', '6','7', '8', '9'};
    static final char[] LOWER_CASE_CHARS_ARRAY = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o',
            'p','q','r','s','t','u','v','w','x','y','z'};
    static final char[] UPPER_CASE_CHARS_ARRAY = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O',
            'P','Q','R','S','T','U','V','W','X','Y','Z'};
    static final char[] SYMBOLS_CHAR_ARRAY = {'@','!','#','$','%','^','&','*','(',')','_','+','-','=',',','<',
            '.','>',';',':','/','?'};
    static final char[][] CHAR_ARRAY_ARRAY = {DIGIT_CHARS_ARRAY, LOWER_CASE_CHARS_ARRAY,
            UPPER_CASE_CHARS_ARRAY, SYMBOLS_CHAR_ARRAY};
}
