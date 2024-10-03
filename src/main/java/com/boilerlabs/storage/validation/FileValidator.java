package com.boilerlabs.storage.validation;

import org.springframework.web.multipart.MultipartFile;

public class FileValidator {

    private static final long MAX_FILE_SIZE = 10_000_000; // 10MB
    private static final String[] SUPPORTED_CONTENT_TYPES = {
            "image/jpg", "image/jpeg", "image/png", "image/gif",
            "image/webp", "image/svg+xml", "application/pdf"
    };

    public void validate(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("Cannot upload an empty file");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new RuntimeException("File size exceeds limit of 10MB");
        }

        String fileType = file.getContentType();
        if (!isSupportedContentType(fileType)) {
            throw new RuntimeException("Unsupported file type: " + fileType);
        }
    }

    private boolean isSupportedContentType(String fileType) {
        for (String supportedType : SUPPORTED_CONTENT_TYPES) {
            if (supportedType.equals(fileType)) {
                return true;
            }
        }
        return false;
    }

}