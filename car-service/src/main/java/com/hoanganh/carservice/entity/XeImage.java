package com.hoanganh.carservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class XeImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameImage;

    private String urlImage;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "xeId")
    private Xe xe;
}