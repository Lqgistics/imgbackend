package com.aman.zen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByFileName(String fileName);

    @Query("SELECT i FROM Image i WHERE i.user = :user ORDER BY i.uploadDate DESC")
    List<Image> findRecents(@Param("user") User user);

    @Query("SELECT SUM(i.fileSize) FROM Image i WHERE i.user = :user")
    Long sumFileSizeByUser(@Param("user") User user);

    long countByUser(User user);

}