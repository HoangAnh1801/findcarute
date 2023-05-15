package com.hoanganh.carservice.controller;

import com.hoanganh.carservice.entity.LoaiXe;
import com.hoanganh.carservice.reponse.Reponse;
import com.hoanganh.carservice.service.LoaiXeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/car/v1/loaixe")
public class LoaiXeController {
    @Autowired
    LoaiXeService loaiXeService;

    @GetMapping()
    public List<LoaiXe> getAllLoaiXe(@RequestParam(name = "search", required = false) String search) {
        return loaiXeService.getAllLoaiXe(search);
    }

    @GetMapping("/{id}")
    public Optional<LoaiXe> getLoaiXeById(@PathVariable("id") Long id) {
        return loaiXeService.getLoaiXeById(id);
    }

    @PostMapping()
    public Reponse saveLoaiXe(@RequestBody LoaiXe loaiXe) {
        return loaiXeService.saveLoaiXe(loaiXe);
    }

    @DeleteMapping("/id")
    public Reponse deleteLoaiXe(@RequestParam("id") Long id) {
        return loaiXeService.deleteLoaiXe(id);
    }
}
