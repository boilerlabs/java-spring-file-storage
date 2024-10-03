package com.boilerlabs.storage.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.boilerlabs.storage.model.File;
import com.boilerlabs.storage.service.FileStorageService;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping
    public ResponseEntity<Page<File>> listFiles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(fileStorageService.listFiles(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<File> getFile(@RequestParam UUID id) {
        return ResponseEntity.ok(fileStorageService.getFile(id));
    }

    @PostMapping(path = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(fileStorageService.uploadFile(file));
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam("id") UUID id) {
        return ResponseEntity.ok(fileStorageService.downloadFile(id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFile(@RequestParam("id") UUID id) {
        return ResponseEntity.ok(fileStorageService.deleteFile(id));
    }

}