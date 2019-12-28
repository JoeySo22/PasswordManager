package org.passwordmanager;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        PasswordGenerator.PasswordGeneratorBuilder builder = new PasswordGenerator.PasswordGeneratorBuilder(
                6, 12, "");
        builder.setStrategy(new DefaultPasswordStrategy());
        builder.setMinimums(1,1,1,0);
        //builder.forceWord("esther");
        PasswordGenerator generator = builder.build();
        int counter = 1;
        int iterations = 12;
        while (counter <= iterations){
            try {
                TimeUnit.SECONDS.sleep(3);
                String generatedPassword = generator.generatePassword();
                System.out.println(counter + "\t" + generatedPassword.length() + "\t" + generatedPassword);
            } catch (InterruptedException e){
                System.out.println("Did not sleep");
            }
            counter++;
        }
    }
}
