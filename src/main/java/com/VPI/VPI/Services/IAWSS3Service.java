package com.VPI.VPI.Services;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IAWSS3Service {

    String uploadFile(MultipartFile file);

    List<String> getObjectsFromS3();
}
