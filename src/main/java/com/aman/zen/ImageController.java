package com.aman.zen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/images")

public class ImageController {
    
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            Image uploadedImage = imageService.uploadImage(file);
            return ResponseEntity.status(HttpStatus.CREATED).body(uploadedImage);
         } catch (IllegalStateException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    } catch (IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}

    @GetMapping
    public ResponseEntity<List<Image>> getAllImages() {
        return ResponseEntity.ok(imageService.getAllImages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImageById(@PathVariable Long id) {
        Optional<Image> image = Optional.ofNullable(imageService.getImageById(id));
        return image.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        try {
            boolean deleted = imageService.deleteImage(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    
}
