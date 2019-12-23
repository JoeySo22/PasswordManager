/*
    Author: Jose Eduardo Soto
    Description:    The responsibility of this class is to encapsulate the algorithm of the password-building
    strategy. It needs have all of the fields we need to generate.
 */

package org.passwordmanager;

public abstract class PasswordStrategy {
    int minimumLength;
    int maximumLength;
    String invalidCharacters;
    int minimumLowerChars;
    int minimumCapitalChars;
    int minimumDigitChars;
    int minimumSymbolChars;

    // The purpose of this is that it's the entry point of the algorithm for all of the classes that implement it.
    // The problem with it is that there appears to be a problem sending data between what we generated and
    // what we have. Maybe we can just make a data structure or something.
    abstract String makePassword();
}
