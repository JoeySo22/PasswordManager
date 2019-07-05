package org.passwordmanager;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.lang.IllegalArgumentException;

public class PasswordGenerator {

    private static final char[] DIGIT_CHARS_ARRAY = {'0', '1', '2', '3', '4', '5', '6','7', '8', '9'};
    private static final char[] LOWER_CASE_CHARS_ARRAY = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o',
    'p','q','r','s','t','u','v','w','x','y','z'};
    private static final char[] UPPER_CASE_CHARS_ARRAY = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O',
    'P','Q','R','S','T','U','V','W','X','Y','Z'};
    private static final char[] SYMBOLS_CHAR_ARRAY = {'@','!','#','$','%','^','&','*','(',')','_','+','-','=',',','<',
    '.','>',';',':','/','?'};
    private static final char[][] CHAR_ARRAY_ARRAY = {DIGIT_CHARS_ARRAY, LOWER_CASE_CHARS_ARRAY,
    UPPER_CASE_CHARS_ARRAY, SYMBOLS_CHAR_ARRAY};

    private final byte minimumLength;
    private final byte maximumLength;
    private final String invalidCharacters;
    private final byte minimumCapsRequired;
    private final byte minimumLowerRequired;
    private final byte minimumSymbolsRequired;
    private final byte minimumDigitsRequired;

    private StringBuilder passwordBuilder;

    public class PasswordGeneratorBuilder{
        // Required parameters
        public final byte minimumLength;
        public final byte maximumLength;
        public final String invalidCharacters;

        // Optional parameters
        public byte minimumCapsRequired = 0;
        public byte minimumLowerRequired = 0;
        public byte minimumSymbolsRequired = 0;
        public byte minimumDigitsRequired = 0;

        public PasswordGeneratorBuilder(byte minLength, byte maxLength, String invalidChars){
            if (minLength <= 6){
                throw new IllegalArgumentException("Minimum length must be greater than 6");
            }
            if (maxLength < minLength){
                throw new IllegalArgumentException("Maximum length must be greather than or equal to minLength");
            }
            this.minimumLength = minLength;
            this.maximumLength = maxLength;
            this.invalidCharacters = invalidChars;
        }

        public PasswordGeneratorBuilder minimumCaps(byte minCaps){
            if (isLessThanZero(minCaps)){
                throw new IllegalArgumentException("Minimum Caps cannot be less than zero");
            }
            minimumCapsRequired = minCaps;
            return this;
        }
        public PasswordGeneratorBuilder minimumLower(byte minLower){
            if (isLessThanZero(minLower)){
                throw new IllegalArgumentException("Minimum lower cannot be less that zero");
            }
            minimumLowerRequired = minLower;
            return this;
        }
        public PasswordGeneratorBuilder minimumSymbols(byte minSymbols){
            if (isLessThanZero(minSymbols)){
                throw new IllegalArgumentException("Minimum Symbols cannot be less than zero");
            }
            minimumSymbolsRequired = minSymbols;
            return this;
        }
        public PasswordGeneratorBuilder minimumDigits(byte minDigits){
            if (isLessThanZero(minDigits)){
                throw new IllegalArgumentException("Minimum Digits cannot be less than zero");
            }
            minimumDigitsRequired = minDigits;
            return this;
        }
        public PasswordGenerator build(){
            if ((minimumCapsRequired + minimumDigitsRequired + minimumSymbolsRequired) > maximumLength) {
                throw new IllegalArgumentException("Maximum Length exceeded by minimum characters arguements");
            }
            return new PasswordGenerator(this);
        }

        private boolean isLessThanZero(byte n) {return n < 0;}
    }

    private PasswordGenerator(PasswordGeneratorBuilder our_builder){
        this.minimumLength = our_builder.minimumLength;
        this.maximumLength = our_builder.maximumLength;
        this.invalidCharacters = our_builder.invalidCharacters;
        this.minimumCapsRequired = our_builder.minimumCapsRequired;
        this.minimumLowerRequired = our_builder.minimumLowerRequired;
        this.minimumSymbolsRequired = our_builder.minimumSymbolsRequired;
        this.minimumDigitsRequired = our_builder.minimumDigitsRequired;

        buildPassword();
    }

    private void buildPassword(){
        passwordBuilder = new StringBuilder(minimumLength);

        Stack<Character> digitCharStack = new Stack<Character>();
        Stack<Character> lowerCaseCharStack = new Stack<Character>();
        Stack<Character> upperCaseCharStack = new Stack<Character>();
        Stack<Character> symbolCharStack = new Stack<Character>();
        Stack<Character> remainderCharStack = new Stack<Character>();

        Random r = new Random();
        byte wordLength = minimumLength;
        byte minimumRequiredChars = (byte)(this.minimumCapsRequired + this.minimumSymbolsRequired +
                this.minimumDigitsRequired);

        //CONTINUE HERE
        // I can create another array of char's so that we can eliminate some of the ones we want to eliminate. Or
        // Just create a filter when randomly getting these out. 
        
        //Stack-maker for digit characters
        for (byte i = 1; i <= minimumDigitsRequired; i++){
            while (true) {
                char digitChar = DIGIT_CHARS_ARRAY[r.nextInt(DIGIT_CHARS_ARRAY.length)];
                if (!isCharInString(digitChar, this.invalidCharacters)){
                    digitCharStack.push(digitChar);
                    break;
                }
            }
        }

        //Stack-maker for upper-case characters(just like above)
        for (byte i = 1; i <= minimumCapsRequired; i++) {
            while (true){
                char upperCaseChar = UPPER_CASE_CHARS_ARRAY[r.nextInt(UPPER_CASE_CHARS_ARRAY.length)];
                if (!isCharInString(upperCaseChar, this.invalidCharacters)){
                    upperCaseCharStack.push(upperCaseChar);
                    break;
                }
            }
        }

        //Stack-maker for lower-case characters(just like above)
        for (byte i = 1; i <= minimumLowerRequired; i++){
            while (true){
                char lowerCaseChar = LOWER_CASE_CHARS_ARRAY[r.nextInt(LOWER_CASE_CHARS_ARRAY.length)];
                if (!isCharInString(lowerCaseChar, this.invalidCharacters)){
                    lowerCaseCharStack.push(lowerCaseChar);
                    break;
                }
            }
        }

        //Stack-maker for symbol-case characters(just like above)
        for (byte i = 1; i <= minimumSymbolsRequired; i++){
            while (true){
                char symbolChar = SYMBOLS_CHAR_ARRAY[r.nextInt(SYMBOLS_CHAR_ARRAY.length)];
                if (!isCharInString(symbolChar, this.invalidCharacters)){
                    symbolCharStack.push(symbolChar);
                    break;
                }
            }
        }

        //Stack-maker for remaining characters (just like above)
        for (byte i = 1; i <= wordLength - minimumRequiredChars; i++){
            while (true){
                char[] firstArray = CHAR_ARRAY_ARRAY[r.nextInt(CHAR_ARRAY_ARRAY
                .length)];
                char remainingChar = firstArray[r.nextInt(firstArray.length)];
                if (!isCharInString(remainingChar, this.invalidCharacters)){
                    remainderCharStack.push(remainingChar);
                    break;
                }
            }
        }

        //NOW TO RANDOMLY PULL FROM THE STACKS AND POPULATE THE STRINGBUILDER
        List<Stack> stacksList = new LinkedList<Stack>();
        if (!digitCharStack.empty()) {
            stacksList.add(digitCharStack);
        }
        if (!lowerCaseCharStack.empty()) {
            stacksList.add(lowerCaseCharStack);
        }
        if (!upperCaseCharStack.empty()) {
            stacksList.add(upperCaseCharStack);
        }
        if (!symbolCharStack.empty()){
            stacksList.add(symbolCharStack);
        }
        if (!remainderCharStack.empty()){
            stacksList.add(remainderCharStack);
        }

        for (byte i = 0; i <= this.minimumLength; i++){
            while (!stacksList.isEmpty()){
                int upperSizeBound = stacksList.size()+1;
                int index = r.nextInt(upperSizeBound);
                Stack<Character> ourStack = stacksList.get(index);
                if (ourStack.empty()){
                    stacksList.remove(index);
                }
                else {
                    passwordBuilder.append(ourStack.pop());
                }
            }
        }
        



    }

    public String getPassword(){
        return this.passwordBuilder.toString();
    }

    public boolean isCharInString(char c, String s){
        for (int i = 0; i < s.length(); i++){
            if (c == s.charAt(i)) {
                return true;
            }
        }
        return false;
    }
}
