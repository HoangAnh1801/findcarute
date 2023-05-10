package com.hoanganh.carservice.controller;

import com.hoanganh.carservice.entity.HangXe;
import com.hoanganh.carservice.service.HangXeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/car/v1/hangxe")
public class HangXeController {
    @Autowired
    HangXeService hangXeService;

    @GetMapping()
    public List<HangXe> getAll() {
        return hangXeService.getAllHangXe();
    }

    @GetMapping("/{id}")
    public Optional<HangXe> getLoaiXeById(@PathVariable("id") Long id) {
        return hangXeService.getHangXeById(id);
    }

    @PostMapping()
    public HangXe saveLoaiXe(@RequestBody HangXe hangXe) {
        return hangXeService.saveHangXe(hangXe);
    }

//    @DeleteMapping(Long id) {
//
//    }
}
