package com.darkenergy.EncryptIt.KeyManager;

public class keyManager {

    public static String keyPadded(String key) {
        String modifiedKey = key.trim();

        System.out.println("Actual Size : " + modifiedKey.length());

        int length = key.length();

        if (length != 16) {
            int temp = length % 16;
            temp = 16 - temp;
            String tempString = "_".repeat(temp);
            modifiedKey = modifiedKey.concat(tempString);
            System.out.println("Modified Size : " + modifiedKey.length());
        }
        return modifiedKey;

    }

}