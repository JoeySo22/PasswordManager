package org.passwordmanager;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import static org.passwordmanager.CharacterCollection.*;
import static org.passwordmanager.Utilities.*;

public class DefaultPasswordStrategy extends PasswordStrategy {

    public DefaultPasswordStrategy(){
        super();
    }

    @Override
    public String makePassword() {
        password = new StringBuilder(maximumLength);
        Stack<Character> digitCharStack = new Stack<>();
        Stack<Character> lowerCaseCharStack = new Stack<>();
        Stack<Character> upperCaseCharStack = new Stack<>();
        Stack<Character> symbolCharStack = new Stack<>();
        Stack<Character> remainderCharStack = new Stack<>();
        int numberOfRemainingCharacters = generateRemainingCharacterNumber(minimumCapitalChars,minimumDigitChars,
                minimumSymbolChars, minimumLength, maximumLength);
        //Stack-maker for digit characters
        processStack(minimumDigitChars, DIGIT_CHARS_ARRAY, digitCharStack, invalidCharacters);
        //Stack-maker for upper-case characters(just like above)
        processStack(minimumCapitalChars, UPPER_CASE_CHARS_ARRAY, upperCaseCharStack, invalidCharacters);
        //Stack-maker for lower-case characters(just like above)
        processStack(minimumCapitalChars, LOWER_CASE_CHARS_ARRAY, lowerCaseCharStack, invalidCharacters);
        //Stack-maker for symbol-case characters(just like above)
        processStack(minimumSymbolChars, SYMBOLS_CHAR_ARRAY, symbolCharStack, invalidCharacters);
        //Stack-maker for remaining characters (just like above)
        // Not completely random. outputs the same length everytime.
        for (int i = 1; i <= numberOfRemainingCharacters; i++){
            while (true){
                char[] firstArray = CHAR_ARRAY_ARRAY[r.nextInt(CHAR_ARRAY_ARRAY.length)];
                char remainingChar = firstArray[r.nextInt(firstArray.length)];
                if (!isCharInString(remainingChar, this.invalidCharacters)){
                    remainderCharStack.push(remainingChar);
                    break;
                }
            }
        }
        //NOW TO RANDOMLY PULL FROM THE STACKS AND POPULATE THE STRINGBUILDER
        List<Stack> stacksList = new LinkedList<>();
        if (!digitCharStack.empty()) stacksList.add(digitCharStack);
        if (!lowerCaseCharStack.empty()) stacksList.add(lowerCaseCharStack);
        if (!upperCaseCharStack.empty()) stacksList.add(upperCaseCharStack);
        if (!symbolCharStack.empty()) stacksList.add(symbolCharStack);
        if (!remainderCharStack.empty()) stacksList.add(remainderCharStack);
        for (int i = 0; i <= this.minimumLength; i++){
            while (!stacksList.isEmpty()){
                int upperSizeBound = stacksList.size()+1;
                int index = r.nextInt(upperSizeBound - 1);
                Stack<Character> ourStack = stacksList.get(index);
                if (ourStack.empty()) stacksList.remove(index);
                else password.append(ourStack.pop());
            }
        }
        return password.toString();
    }


}
