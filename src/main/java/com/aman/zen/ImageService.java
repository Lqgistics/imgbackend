package com.aman.zen;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.Files;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final Path uploadDIR = Paths.get("./uploads/");

    public ImageService(ImageRepository imageRepository, UserRepository userRepository) throws IOException {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;

        if (!Files.exists(uploadDIR)) {
            Files.createDirectory(uploadDIR);
        }
    }

    public Image uploadImage(MultipartFile file, String userEmail) throws IOException {

        Path filePath = this.uploadDIR.resolve(file.getOriginalFilename());

        if(file.isEmpty()) {
            throw new IOException("File is empty");
        }

        Optional<Image> existingImage = imageRepository.findByFileName(file.getOriginalFilename());

        if(existingImage.isPresent()) {
            throw new IllegalStateException("A file with the name '" + file.getOriginalFilename() + "' already exists.");        
        }

        // Find the user who is uploading the image
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found: " + userEmail));

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Failed to save the file: " + e.getMessage(), e);
        }

        Image metadata = new Image();
        metadata.setFileName(file.getOriginalFilename());
        metadata.setFileType(file.getContentType());
        metadata.setFilePath(filePath.toString());
        metadata.setFileSize((file.getSize()) / 1000);
        metadata.setUploadDate(LocalDateTime.now());
        metadata.setUser(user); // Associate the image with the user

        String viewURL = "api/images/view/" + file.getOriginalFilename();
        metadata.setViewURL(viewURL);

        return imageRepository.save(metadata);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    public boolean deleteImage(long id) throws IOException {
        Optional<Image> imageMetadata = imageRepository.findById(id);
        if(imageMetadata.isPresent()) {
            Image metadata = imageMetadata.get();

            Path filePath = Paths.get(metadata.getFilePath());
            Files.delete(filePath);
            imageRepository.deleteById(id);
            return true;

        }
        return false;
    }


}
    
