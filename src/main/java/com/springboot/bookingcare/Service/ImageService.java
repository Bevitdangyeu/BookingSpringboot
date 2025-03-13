package com.springboot.bookingcare.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface ImageService {
    public String upload(MultipartFile multipartFile,String applicationPath) throws FileNotFoundException;
}
