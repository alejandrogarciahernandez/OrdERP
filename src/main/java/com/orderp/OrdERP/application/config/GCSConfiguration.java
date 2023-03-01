package com.orderp.OrdERP.application.config;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Configuration
public class GCSConfiguration {

    @Bean
    public String bucketId() {
        return new String("image-orderp");
    }
    @Bean
    public Storage getStorage() throws IOException {
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("D:\\OrdERP\\credentials.json"));
        Storage storage = StorageOptions.newBuilder()
                .setCredentials(credentials)
                .build()
                .getService();
        return storage;
    }
}
