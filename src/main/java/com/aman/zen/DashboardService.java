package com.aman.zen;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DashboardService {

    private final ImageRepository imageRepository;
    private final UserRepository userRepository; // Inject UserRepository

    public DashboardService(ImageRepository imageRepository, UserRepository userRepository) {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
    }

    public DashboardDTOs getDashboardMetrics(String userEmail) {
        // Find the user who is making the request
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found: " + userEmail));

        // Call the user-specific repository methods
        long totalImageCount = imageRepository.countByUser(user);
        Long totalStorageUsed = imageRepository.sumFileSizeByUser(user);
        List<Image> recentImages = imageRepository.findRecents(user);

        DashboardDTOs metrics = new DashboardDTOs();
        metrics.setTotalImageCount(totalImageCount);
        metrics.setTotalStorageUsed(totalStorageUsed != null ? totalStorageUsed : 0L);
        metrics.setRecentImages(recentImages);

        return metrics;
    }
}