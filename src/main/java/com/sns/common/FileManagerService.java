package com.sns.common;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileManagerService {
    public final static String FILE_UPLOAD_PATH = "/Users/kimyong/Documents/developer/spring_project/sns_img/";

    public String uploadFile(MultipartFile file, String userLoginId){

        String directioryName = userLoginId + "_" + System.currentTimeMillis();
        String filePath = FILE_UPLOAD_PATH + directioryName + "/";

        File directory = new File(filePath);
        directory.mkdir();

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(filePath + file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "/images/" + directioryName + "/" + file.getOriginalFilename();
    }
}
