package com.darkenergy.EncryptIt.Controller;

import com.darkenergy.EncryptIt.EncryptionHandler.EncryptionHandler;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

@RestController
@RequestMapping("/EncryptIt")
@CrossOrigin
public class restController {
    @PostMapping("/encrypt")
    public ResponseEntity<String> startEncryption(@RequestParam MultipartFile files, @RequestParam String key) throws IOException {

        File encryptFile = EncryptionHandler.multipartToFile(files, files.getOriginalFilename());
        String fileExtension = EncryptionHandler.getFileExtension(files.getOriginalFilename(), ".");
        int id = EncryptionHandler.startEncryption(encryptFile, key);
        return new ResponseEntity<>(id + "#" + fileExtension + "@" + files.getContentType(), HttpStatus.OK);
    }


    @GetMapping("/downloadFile")
    public ResponseEntity<ByteArrayResource> downloadService(@RequestParam String fileId) throws IOException {
        String contType = EncryptionHandler.getFileExtension(fileId, "@");
        System.out.println(contType);

        int lastIndex = fileId.lastIndexOf("@");
        System.out.println(fileId.substring(0, lastIndex));

        int hashIndex = fileId.lastIndexOf("#");

        String extension = fileId.substring(hashIndex + 1, lastIndex);
        System.out.println(extension);


        File toBeSent = new File(System.getProperty("java.io.tmpdir") + "/" + fileId.toString().substring(0, hashIndex));
        byte[] bytes = Files.readAllBytes(toBeSent.toPath());
        System.out.println(Path.of(toBeSent.getPath()));
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(contType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "File" + "." + extension + "\"")
                .body(new ByteArrayResource(bytes));
    }

    @PostMapping("/decrypt")
    public ResponseEntity<String> startDecryption(@RequestParam MultipartFile files, @RequestParam String key) throws IOException {
        File encryptFile = EncryptionHandler.multipartToFile(files, files.getOriginalFilename());
        String fileExtension = EncryptionHandler.getFileExtension(files.getOriginalFilename(), ".");
        int id = EncryptionHandler.startDecryption(encryptFile, key);
        return new ResponseEntity<>(id + "#" + fileExtension + "@" + files.getContentType(), HttpStatus.OK);
    }
}
