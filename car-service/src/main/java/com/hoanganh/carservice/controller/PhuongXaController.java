package com.hoanganh.carservice.controller;

import com.hoanganh.carservice.entity.PhuongXa;
import com.hoanganh.carservice.entity.QuanHuyen;
import com.hoanganh.carservice.reponse.Reponse;
import com.hoanganh.carservice.service.PhuongXaService;
import com.hoanganh.carservice.service.QuanHuyenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/car/v1/phuongxa")
public class PhuongXaController {
    @Autowired
    PhuongXaService phuongXaService;

    @GetMapping()
    public List<PhuongXa> getAllPhuongXa() {
        return phuongXaService.getAllPhuongXa();
    }

    @GetMapping("/{id}")
    public Optional<PhuongXa> getPhuongXaById(@PathVariable("id") Long id) {
        return phuongXaService.getPhuongXaById(id);
    }

    @GetMapping("/quan")
    public List<PhuongXa> getPhuongXaByQuanHuyenId(@RequestParam("id") Long id) {
        return phuongXaService.findByQuanHuyenId(id);
    }

    @PostMapping()
    public Reponse savePhuongXa(@RequestBody PhuongXa phuongXa) {
        return phuongXaService.savePhuongXa(phuongXa);
    }

    @DeleteMapping("/id")
    public Reponse deletePhuongXa(@RequestParam("id") Long id) {
        return phuongXaService.deletePhuongXa(id);
    }
}
