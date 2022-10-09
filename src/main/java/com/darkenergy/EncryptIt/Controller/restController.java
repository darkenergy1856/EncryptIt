package com.darkenergy.EncryptIt.Controller;

import com.darkenergy.EncryptIt.EncryptionHandler.EncryptionHandler;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class restController {
    @GetMapping("/encrypt")
    public ResponseEntity<ByteArrayResource> startEncryption(@RequestParam MultipartFile files, @RequestParam String key) {
        try {
            File encryptFile = EncryptionHandler.multipartToFile(files, files.getOriginalFilename());
            File encryptedFile = EncryptionHandler.startEncryption(encryptFile, key);
            byte[] bytes = Files.readAllBytes(encryptedFile.toPath());
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(files.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "encrypted" + files.getOriginalFilename() + "\"")
                    .body(new ByteArrayResource(bytes));
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/decrypt")
    public ResponseEntity<ByteArrayResource> startDecryption(@RequestParam MultipartFile files, @RequestParam String key) {
        try {
            File decryptFile = EncryptionHandler.multipartToFile(files, files.getOriginalFilename());
            File decryptedFile = EncryptionHandler.startDecryption(decryptFile, key);
            byte[] bytes = Files.readAllBytes(decryptedFile.toPath());
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(files.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "decrypted" + files.getOriginalFilename() + "\"")
                    .body(new ByteArrayResource(bytes));

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
