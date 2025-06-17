package com.sns.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
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

    // i: imagePath
    // o: X
    public void deleteFile(String imagePath) {
        // as-is: D:\신보람\6_spring_project\memo_image//images/aaaa_1748518858536/prairie-dog-9569847_1280.jpg
        // to-be: D:\신보람\6_spring_project\memo_image/aaaa_1748518858536/prairie-dog-9569847_1280.jpg
        //   겹치고 있는  /images/  스트링 제거
        Path path = Paths.get(FILE_UPLOAD_PATH + imagePath.replace("/images/", ""));

        // 이미지가 있는가?
        if (Files.exists(path)) {
            // 이미지 삭제
            try {
                Files.delete(path);
            } catch (IOException e) {
                log.info("[### 파일매니저 image 삭제] imagePath:{}", imagePath);
                return;
            }

            // 폴더(디렉토리) 삭제
            path = path.getParent();
            // 폴더가 있는가?
            if (Files.exists(path)) {
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    log.info("[### 파일 매니저 폴더 삭제] path:{}", path);
                }
            }
        }
    }
}
