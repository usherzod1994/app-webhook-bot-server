package com.example.appwebhookbotserver.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FileStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @JsonProperty(value = "file_id")
    private String fileId;

    private String caption;

    public FileStore(String fileId, String caption) {
        this.fileId = fileId;
        this.caption = caption;
    }
}
