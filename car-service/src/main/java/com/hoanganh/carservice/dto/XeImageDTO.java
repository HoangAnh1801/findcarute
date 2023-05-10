package com.hoanganh.carservice.dto;

import com.hoanganh.carservice.entity.Xe;
import lombok.Data;

@Data
public class XeImageDTO {
    private Long id;

    private String nameImage;

    private String urlImage;

    private Xe xe;
}
