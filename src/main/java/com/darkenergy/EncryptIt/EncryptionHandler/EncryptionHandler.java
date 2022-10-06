package com.darkenergy.EncryptIt.EncryptionHandler;

import com.darkenergy.EncryptIt.Crypto.CryptoException;
import com.darkenergy.EncryptIt.Crypto.CryptoUtils;
import com.darkenergy.EncryptIt.KeyManager.keyManager;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class EncryptionHandler {
    public static File multipartToFile(MultipartFile multipart, String fileName) throws IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
        multipart.transferTo(convFile);
        return convFile;
    }

    public static File startEncryption(File file, String key){
        if (key.equals("")){
            return null;
        }else {
            key = keyManager.keyPadded(key);
        }
        try {
            File encryptedFile = new File(System.getProperty("java.io.tmpdir") + "/" + "encrypt");

            try {
                CryptoUtils.encrypt(key, file, encryptedFile);
                return encryptedFile;
            } catch (CryptoException ex) {
                System.out.println(ex.getMessage());
                return null;
            }
        }catch (Exception exe){
            exe.printStackTrace();
            return null;
        }
    }

    public static File startDecryption(File file, String key) {

        if (key.equals("")){
            return null;
        }else {
            key = keyManager.keyPadded(key);
        }

        File decryptedFile = new File(System.getProperty("java.io.tmpdir") + "/" + "decrypt");

        try {
            CryptoUtils.decrypt(key, file, decryptedFile);
            return decryptedFile;
        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }
}


