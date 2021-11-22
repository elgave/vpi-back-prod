package com.VPI.VPI.Services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AWSS3Service implements IAWSS3Service{

    private static final Logger LOGGER = LoggerFactory.getLogger(AWSS3Service.class);
    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucketName;
    @Override
    public String uploadFile(MultipartFile file) {

        File mainFile = new File(file.getOriginalFilename());
        try(FileOutputStream stream = new FileOutputStream(mainFile)) {
            stream.write(file.getBytes());
            String newFileName =  System.currentTimeMillis() + mainFile.getName();
            LOGGER.info("Subiendo archivo con el nombre..." + newFileName);
            PutObjectRequest request = new PutObjectRequest(bucketName,newFileName,mainFile);
            amazonS3.putObject(request);
            return newFileName;
        } catch (IOException e){
            LOGGER.error(e.getMessage(),e);
            return null;
        }


    }

    @Override
    public List<String> getObjectsFromS3() {
        ListObjectsV2Result result = amazonS3.listObjectsV2(bucketName);
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        List<String> list = objects.stream().map(item ->{
            return  item.getKey();
        }).collect(Collectors.toList());
        return  list;
    }
}
