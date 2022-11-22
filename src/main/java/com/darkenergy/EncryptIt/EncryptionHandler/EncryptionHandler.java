package com.darkenergy.EncryptIt.EncryptionHandler;

import com.darkenergy.EncryptIt.Crypto.CryptoException;
import com.darkenergy.EncryptIt.Crypto.CryptoUtils;
import com.darkenergy.EncryptIt.KeyManager.keyManager;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class EncryptionHandler {
    static int count = 1;

    public static String getFileExtension(String fileName, String symbol) {
        int lastIndexOf = fileName.lastIndexOf(symbol);
        if (lastIndexOf == -1) {
            return "";
        }
        return fileName.substring(lastIndexOf + 1);
    }

    public static File multipartToFile(MultipartFile multipart, String fileName) throws IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
        multipart.transferTo(convFile);
        return convFile;
    }

    public static int startEncryption(File file, String key) {

        key = keyManager.keyPadded(key);
        try {
            File encryptedFile = new File(System.getProperty("java.io.tmpdir") + "/" + count);
            count++;

            try {
                CryptoUtils.encrypt(key, file, encryptedFile);
                return (count - 1);
            } catch (CryptoException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (Exception exe) {
            exe.printStackTrace();
        }
        return 0;
    }

    public static int startDecryption(File file, String key) {

        key = keyManager.keyPadded(key);

        File decryptedFile = new File(System.getProperty("java.io.tmpdir") + "/" + count);
        count++;

        try {
            CryptoUtils.decrypt(key, file, decryptedFile);
            return (count - 1);
        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }


}


