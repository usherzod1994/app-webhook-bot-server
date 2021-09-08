package com.example.appwebhookbotserver.service;

import com.example.appwebhookbotserver.entity.FileStore;
import com.example.appwebhookbotserver.repository.FileStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileStoreService {

    @Autowired
    private FileStoreRepository fileStoreRepository;


    public boolean saveFile(String fileId, String caption){
        if (fileId.trim().isEmpty() || caption.trim().isEmpty()){
            return false;
        }
        fileStoreRepository.save(new FileStore(fileId, caption));
        return true;
    }


    public List<FileStore> getAllFiles(){
        return fileStoreRepository.findAll();
    }
}
