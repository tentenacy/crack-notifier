package com.tenutz.cracknotifier.web.client;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AwsS3UploadClient implements UploadClient {

    private final AmazonS3Client amazonS3Client;
    
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public void upload(InputStream inputStream, ObjectMetadata objectMetadata, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
    }

    @Override
    public String getFileUrl(String fileName) {
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    @Override
    public boolean doesFileExists(String fileName) {
        return amazonS3Client.doesObjectExist(this.bucket, fileName);
    }

    @Override
    public void deleteFiles(List<String> fileNames) {
        fileNames.stream().filter(fileName -> doesFileExists(fileName))
                .forEach(fileName -> amazonS3Client.deleteObject(new DeleteObjectRequest(this.bucket, fileName)));
    }

    @Override
    public void delete(String fileName) {
        if(doesFileExists(fileName)) {
            amazonS3Client.deleteObject(new DeleteObjectRequest(this.bucket, fileName));
        }
    }
}
