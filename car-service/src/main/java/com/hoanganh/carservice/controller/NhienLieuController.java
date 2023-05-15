package com.hoanganh.carservice.controller;

import com.hoanganh.carservice.entity.NhienLieu;
import com.hoanganh.carservice.reponse.Reponse;
import com.hoanganh.carservice.service.NhienLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/car/v1/nhienlieu")
public class NhienLieuController {

    @Autowired
    NhienLieuService nhienLieuService;

    @GetMapping()
    public List<NhienLieu> getAllNhienLieu(@RequestParam(name = "search", required = false) String search) {
        return nhienLieuService.getAllNhienLieu(search);
    }

    @GetMapping("/{id}")
    public Optional<NhienLieu> getLoaiXeById(@PathVariable("id") Long id) {
        return nhienLieuService.getNhienLieuById(id);
    }

    @PostMapping()
    public Reponse saveNhienLieu(@RequestBody NhienLieu nhienLieu) {
        return nhienLieuService.saveNhienLieu(nhienLieu);
    }

    @DeleteMapping("/id")
    public Reponse deleteNhienLieu(@RequestParam("id") Long id) {
        return nhienLieuService.deleteNhienLieu(id);
    }
}
