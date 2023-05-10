package com.hoanganh.carservice.controller;

import com.hoanganh.carservice.dto.ImageDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.Random;

@RestController
@RequestMapping("/api/car/v1/image")
public class ImageController {

    @Value("${image-path}")
    public String PATH_IMAGE;

    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImageWithMediaType(@RequestParam("urlFile") String urlFile) throws IOException {
        String urlPath = PATH_IMAGE + "//" + urlFile;
        Path path = Paths.get(urlPath);
        byte[] image = Files.readAllBytes(path);

        return image;
    }

    @PostMapping
    public ImageDTO uploadFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam("path") String pathFolder) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        int point = fileName.lastIndexOf('.');
        String urlFile = pathFolder + "//";
        String uploadDir = PATH_IMAGE + "//" + pathFolder;
        Random random = new Random();
        int randomWithNextInt = Math.abs(random.nextInt());
        if (fileName != null) {
            if (fileName.length() > 0) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                String extension = fileName.substring(point);
                String name = String.valueOf(randomWithNextInt) + String.valueOf(timestamp.getTime()) + extension;
                urlFile = urlFile.concat(name);
                saveFile(uploadDir, name, multipartFile);
            }
        }
        ImageDTO response = new ImageDTO(fileName, urlFile);
        return response;
    }


    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        InputStream inputStream = multipartFile.getInputStream();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
    }
}