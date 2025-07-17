package com.aman.zen;

import java.util.List;

public class DashboardDTOs {

    private long totalImageCount;
    private long totalStorageUsed;
    private List<Image> recentImages;

    public long getTotalImageCount() {
        return totalImageCount;
    }

    public void setTotalImageCount(long totalImageCount) {
        this.totalImageCount = totalImageCount;
    }

    public long getTotalStorageUsed() {
        return totalStorageUsed;
    }

    public void setTotalStorageUsed(long totalStorageUsed) {
        this.totalStorageUsed = totalStorageUsed;
    }

    public List<Image> getRecentImages() {
        return recentImages;
    }

    public void setRecentImages(List<Image> recentImages) {
        this.recentImages = recentImages;
    }
}