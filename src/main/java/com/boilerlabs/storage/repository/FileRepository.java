package com.boilerlabs.storage.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boilerlabs.storage.model.File;

public interface FileRepository extends JpaRepository<File, UUID> {
}