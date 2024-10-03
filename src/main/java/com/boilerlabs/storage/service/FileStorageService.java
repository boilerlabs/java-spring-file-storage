package com.boilerlabs.storage.service;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.boilerlabs.storage.model.File;
import com.boilerlabs.storage.repository.FileRepository;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    private FileRepository fileRepository;

    public FileStorageService() {
        this.fileStorageLocation = Paths.get("./uploads").toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", e);
        }
    }

    public String storeFile(MultipartFile file) {
        try {
            String fileName = normalizeFileName(file.getOriginalFilename());
            File fileEntity = new File(fileName);
            File savedFile = fileRepository.save(fileEntity);

            Path targetLocation = this.fileStorageLocation.resolve(savedFile.getId().toString());
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return getFilePath(savedFile.getId()).toString();
        } catch (IOException e) {
            throw new RuntimeException(
                    "Could not store file " + file.getOriginalFilename() + ". Please try again!",
                    e);
        }
    }

    public Path getFilePath(UUID id) {
        return this.fileStorageLocation.resolve(id.toString()).normalize();
    }

    private String normalizeFileName(String fileName) {
        return fileName.replaceAll("[^a-zA-Z0-9.-]", "_").toLowerCase();
    }
}