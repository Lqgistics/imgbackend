package com.aman.zen;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.Files;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final Path uploadDIR = Paths.get("./uploads/");

    public ImageService(ImageRepository imageRepository) throws IOException {
        this.imageRepository = imageRepository;

        if (!Files.exists(uploadDIR)) {
            Files.createDirectory(uploadDIR);
        }
    }

    public Image uploadImage(MultipartFile file) throws IOException {

        Path filePath = this.uploadDIR.resolve(file.getOriginalFilename());

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        Image metadata = new Image();
        metadata.setFileName(file.getOriginalFilename());
        metadata.setFileType(file.getContentType());
        metadata.setFilePath(filePath.toString());
        metadata.setUploadDate(LocalDateTime.now());

        return imageRepository.save(metadata);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }


}
    
