package com.hoanganh.carservice.controller;

import com.hoanganh.carservice.entity.QuanHuyen;
import com.hoanganh.carservice.reponse.Reponse;
import com.hoanganh.carservice.service.QuanHuyenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/car/v1/quanhuyen")
public class QuanHuyenController {
    @Autowired
    QuanHuyenService quanHuyenService;

    @GetMapping()
    public List<QuanHuyen> getAllQuanHuyen(@RequestParam(name = "search", required = false) String search) {
        return quanHuyenService.getAllQuanHuyen(search);
    }

    @GetMapping("/{id}")
    public Optional<QuanHuyen> getQuanHuyenById(@PathVariable("id") Long id) {
        return quanHuyenService.getQuanHuyenById(id);
    }

    @PostMapping()
    public QuanHuyen saveQuanHuyen(@RequestBody QuanHuyen quanHuyen) {
        return quanHuyenService.saveQuanHuyen(quanHuyen);
    }

    @DeleteMapping("/id")
    public Reponse delete(@RequestParam("id") Long id) {
        return quanHuyenService.deleteQuanHuyen(id);
    }
}
