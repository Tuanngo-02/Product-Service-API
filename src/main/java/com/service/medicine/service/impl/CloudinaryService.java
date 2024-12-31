package com.service.medicine.service.impl;

import com.cloudinary.Cloudinary;
import com.service.medicine.dto.response.CloudinaryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class CloudinaryService {
    @Autowired
    private Cloudinary cloudinary;

    @Transactional
    public CloudinaryResponse uploadFile(MultipartFile file, String fileName) throws Exception {
        try {
            final Map result = this.cloudinary.uploader()
                    .upload(file.getBytes(),
                            Map.of("public_id",
                                    "tuannt/product/"
                                            + fileName));
            String url      = (String) result.get("secure_url");
            String publicId = (String) result.get("public_id");
            return CloudinaryResponse.builder()
                    .id(publicId)
                    .url(url)
                    .build();

        } catch (Exception e) {
            throw new Exception("Failed to upload file");
        }
    }

    public void deleteFile (String id) throws Exception {
        try {
            this.cloudinary.uploader().destroy(id, Map.of());
        }catch (Exception e){
            throw new Exception("Failed to delete file");
        }
    }
}
