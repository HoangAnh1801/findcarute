package com.hoanganh.carservice.controller;

import com.hoanganh.carservice.entity.NguoiDung;
import com.hoanganh.carservice.reponse.Reponse;
import com.hoanganh.carservice.repository.NguoiDungRepository;
import com.hoanganh.carservice.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/car/v1/nguoidung")
public class NguoiDungController {
    @Autowired
    NguoiDungRepository nguoiDungRepository;

    @Autowired
    NguoiDungService nguoiDungService;

    @PostMapping()
    public NguoiDung save(@RequestBody NguoiDung nguoiDung) {
        return nguoiDungRepository.save(nguoiDung);
    }

    @PostMapping("/danop")
    public Reponse daNopPhi(@RequestBody NguoiDung nguoiDung) {
        return nguoiDungService.daNopPhi(nguoiDung);
    }

    @GetMapping("/chuanop")
    public Reponse chuaNopPhi(@RequestParam("tendn") String tenDN) {
        return nguoiDungService.chuaNopPhi(tenDN);
    }

}
