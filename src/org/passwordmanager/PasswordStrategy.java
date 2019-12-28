/*
    Author: Jose Eduardo Soto
    Description:    The responsibility of this class is to encapsulate the algorithm of the password-building
    strategy. It needs have all of the fields we need to generate.
 */

package org.passwordmanager;

public abstract class PasswordStrategy {
    CharacterCollection collection;
    PasswordGenerator instanceGenerator;
    int minimumLength;
    int maximumLength;
    StringBuilder password;
    String invalidCharacters;
    int minimumLowerChars;
    int minimumCapitalChars;
    int minimumDigitChars;
    int minimumSymbolChars;
    boolean instantiated = false;

    // The default will be semi NIST-standardized.
    public PasswordStrategy(){
        instanceGenerator = null;
        minimumLength = 8;
        maximumLength = 12;
        password = new StringBuilder(maximumLength);
        invalidCharacters = "";
        minimumLowerChars = 1;
        minimumCapitalChars = 1;
        minimumDigitChars = 1;
        minimumSymbolChars = 1;
    }

    // The purpose of this is that it's the entry point of the algorithm for all of the classes that implement it.
    // The problem with it is that there appears to be a problem sending data between what we generated and
    // what we have. Maybe we can just make a data structure or something.
    abstract String makePassword();

    void initStrategyWith(PasswordGenerator generator){
        if (generator != null){
            instanceGenerator = generator;
            instantiated = true;
        }
    }
}
