package com.cromxt.zenspaceserver.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "medias")
public class MediaObjects {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String url;
    private String type;
    private String mediaId;
    private String size;
}
