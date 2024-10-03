package com.boilerlabs.storage.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.boilerlabs.storage.model.File;
import com.boilerlabs.storage.repository.FileRepository;
import com.boilerlabs.storage.validation.FileValidator;

@Service
public class FileStorageService {

    private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);

    private final Path fileStorageLocation;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FileValidator fileValidator;

    public FileStorageService() {
        this.fileStorageLocation = Paths.get("./uploads").toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", e);
        }
    }

    public Iterable<File> listFiles() {
        logger.info("Listing all files in repository");
        return fileRepository.findAll();
    }

    public String uploadFile(MultipartFile multipartFile) {
        fileValidator.validate(multipartFile);

        try {
            // Get file metadata
            String description = normalizeFileName(multipartFile.getOriginalFilename());
            String fileType = getFileType(description);
            String fileName = UUID.randomUUID().toString() + "." + fileType;
            Long fileSize = multipartFile.getSize();

            // Save file metadata
            File file = new File(description, fileName, fileType, fileSize);
            File savedFile = fileRepository.save(file);

            // Store the file
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
            }

            logger.info("File uploaded successfully: {}", savedFile.getId());
            return getFilePath(savedFile.getId()).toString();
        } catch (IOException e) {
            logger.error("Error storing file", e);
            throw new RuntimeException(
                    "Could not store file " + multipartFile.getOriginalFilename() + ". Please try again!", e);
        }
    }

    public byte[] downloadFile(UUID id) {
        try {
            Path filePath = getFilePath(id);
            logger.info("Downloading file with id: {}", id);
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            logger.error("Error downloading file", e);
            throw new RuntimeException("Could not download file with id " + id, e);
        }
    }

    public String deleteFile(UUID id) {
        try {
            Path filePath = getFilePath(id);
            Files.delete(filePath);
            fileRepository.deleteById(id);
            logger.info("File with id {} deleted successfully", id);
            return "File with id " + id + " deleted successfully";
        } catch (IOException e) {
            logger.error("Error deleting file", e);
            throw new RuntimeException("Could not delete file with id " + id, e);
        }
    }

    private Path getFilePath(UUID id) {
        File file = fileRepository.findById(id).orElseThrow(() -> new RuntimeException("File not found"));
        return this.fileStorageLocation.resolve(file.getFileName()).normalize();
    }

    private String getFileType(String fileName) {
        String[] parts = fileName.split("\\.");
        return parts[parts.length - 1];
    }

    private String normalizeFileName(String fileName) {
        return fileName.replaceAll("[^a-zA-Z0-9.-]", "_").toLowerCase();
    }

}
