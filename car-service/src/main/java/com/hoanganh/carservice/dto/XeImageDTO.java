package com.hoanganh.carservice.dto;

import com.hoanganh.carservice.entity.Xe;
import lombok.Data;

@Data
public class XeImageDTO {
    private Long id;

    private String tenAnh;

    private String urlAnh;

    private Xe xe;
}
