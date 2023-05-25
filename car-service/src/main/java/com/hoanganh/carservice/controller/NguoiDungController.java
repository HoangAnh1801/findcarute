package com.hoanganh.carservice.controller;

import com.hoanganh.carservice.entity.NguoiDung;
import com.hoanganh.carservice.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/car/v1/nguoidung")
public class NguoiDungController {
    @Autowired
    NguoiDungRepository nguoiDungRepository;

    @PostMapping()
    public NguoiDung save(@RequestBody NguoiDung nguoiDung) {
        return nguoiDungRepository.save(nguoiDung);
    }

}
