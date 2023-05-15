package com.hoanganh.carservice.controller;

import com.hoanganh.carservice.entity.QuanHuyen;
import com.hoanganh.carservice.entity.TinhNangXe;
import com.hoanganh.carservice.reponse.Reponse;
import com.hoanganh.carservice.service.QuanHuyenService;
import com.hoanganh.carservice.service.TinhNangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/car/v1/tinhnang")
public class TinhNangController {
    @Autowired
    TinhNangService tinhNangService;

    @GetMapping()
    public List<TinhNangXe> getAllTinhNang(@RequestParam(name = "search", required = false) String search) {
        return tinhNangService.getAllTinhNang(search);
    }

    @GetMapping("/{id}")
    public Optional<TinhNangXe> getTinhNangById(@PathVariable("id") Long id) {
        return tinhNangService.getTinhNangById(id);
    }

    @PostMapping()
    public Reponse saveTinhNang(@RequestBody TinhNangXe tinhNang) {
        return tinhNangService.save(tinhNang);
    }

    @DeleteMapping("/id")
    public Reponse delete(@RequestParam("id") Long id) {
        return tinhNangService.deleteTinhNang(id);
    }
}
