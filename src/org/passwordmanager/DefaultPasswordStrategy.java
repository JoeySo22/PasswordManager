package org.passwordmanager;

public class DefaultPasswordStrategy extends PasswordStrategy {

    public DefaultPasswordStrategy(){
        this.minimumLength = 6;
        this.maximumLength = 12;
        this.minimumLowerChars = 0;
        this.minimumCapitalChars = 0;
        this.minimumDigitChars = 0;
        this.minimumSymbolChars = 0;
        this.invalidCharacters = "";
    }

    @Override
    public String makePassword() {
        return null;
    }
}
