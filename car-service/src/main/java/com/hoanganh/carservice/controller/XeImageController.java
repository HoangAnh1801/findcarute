package com.hoanganh.carservice.controller;

import com.hoanganh.carservice.dto.XeImageDTO;
import com.hoanganh.carservice.entity.XeAnh;
import com.hoanganh.carservice.service.XeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/car/v1/xeimage")
public class XeImageController {
    @Autowired
    private XeService xeService;

    @PostMapping()
    public XeAnh save(@RequestBody XeImageDTO xeImageDTO) {
        return xeService.saveImage(xeImageDTO);
    }
}
