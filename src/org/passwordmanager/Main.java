package org.passwordmanager;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        PasswordGenerator passwordGenerator = new PasswordGenerator.Builder((byte)7, (byte)12, "").build();

        String pass = passwordGenerator.getPassword();

        System.out.println(pass);

        //run the virtual keyboard
        //VirtualKeyboard virtualKeyboard = generateVirtualKeyboard();
        //virtualKeyboard.run();
        //Key key = virtualKeyboard.getKey();

        /*
            Need to create a method and a block that runs the clients input from the virtual keyboard and run the
            SHA-512 and compare the results. If they match then they may access the passwords and generator.

            SHA-512 runs better on x64 computers.
            https://security.stackexchange.com/questions/165559/why-would-i-choose-sha-256-over-sha-512-for-a-ssl-tls-ce
            rtificate

         */
        //if (virtualKeyboard.isPasswordCorrect())
        //{

        //}

    }
    /*
    private VirtualKeyboard generateVirtualKeyboard(){
        return VirtualKeyboard.generateKeyboard();
    }*/
}
