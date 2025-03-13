package com.springboot.bookingcare.Api;

import com.springboot.bookingcare.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/public")
public class uploadFileAPI {
    @Autowired
    ImageService imageService;
    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> upload(@RequestParam("file") MultipartFile mutiMultipartFile) throws FileNotFoundException {
        String applicationPath = System.getProperty("user.dir");
        Map<String, String> response = new HashMap<>();
        response.put("message", "Upload thành công");
        response.put("fileName", imageService.upload(mutiMultipartFile, applicationPath));
        return ResponseEntity.ok(response);
    }
}
