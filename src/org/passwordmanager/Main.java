package org.passwordmanager;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        PasswordGenerator.Builder builder = new PasswordGenerator.Builder((byte) 6, (byte) 12, "");
        PasswordGenerator generator = builder.build();
        int counter = 0;
        while (counter < 25){
            try {
                System.out.println(generator.generatePassword());
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e){
                System.out.println("Did not sleep");
            }
            counter++;
        }
    }
}
