package com.orderp.OrdERP.application.service.item;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class ImageUploadServiceGCS implements ImageUploadService{

    @Autowired
    private Storage storage;

    @Autowired
    @Qualifier("bucketId")
    private String bucketId;

    @Override
    public String saveImage(MultipartFile file) throws IOException {
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketId, file.getOriginalFilename()).build();
        Blob blob = storage.create(blobInfo, file.getBytes());
        return blob.getMediaLink();
    }
}
