package com.aman.zen;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;
    private String filePath;
    private Long fileSize;
    private LocalDateTime uploadDate;
    private String viewURL;

    public Image() {
    }

    public Image(String fileName, String fileType, String filePath, Long fileSize, LocalDateTime uploadDate, String viewURL) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.uploadDate = uploadDate;
        this.viewURL = viewURL;
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getViewURL() {
        return viewURL;
    }

    public void setViewURL(String viewURL) {
        this.viewURL = viewURL;
    }

    
}