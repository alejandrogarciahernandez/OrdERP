package com.orderp.OrdERP.application.service.item;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageUploadService {
    public String saveImage(MultipartFile file) throws IOException;
}
