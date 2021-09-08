package com.example.appwebhookbotserver.repository;

import com.example.appwebhookbotserver.entity.FileStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileStoreRepository extends JpaRepository<FileStore, Integer> {
}
