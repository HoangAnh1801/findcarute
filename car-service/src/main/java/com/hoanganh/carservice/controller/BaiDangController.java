package com.hoanganh.carservice.controller;

import com.hoanganh.carservice.entity.Xe;
import com.hoanganh.carservice.reponse.Reponse;
import com.hoanganh.carservice.service.XeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/car/v1/xe")
public class BaiDangController {
    @Autowired
    XeService xeService;

    @GetMapping()
    public List<Xe> getAllXe() {
        return xeService.getAllXe();
    }

    @GetMapping("/{id}")
    public Optional<Xe> getXeById(@PathVariable("id") Long id) {
        return xeService.getXeById(id);
    }

    @PostMapping()
    public Xe saveXe(@RequestBody Xe xe) {
        return xeService.saveXe(xe);
    }

    @DeleteMapping("/id")
    public Reponse delete(@RequestParam("id") Long id) {
        return xeService.deleteXe(id);
    }
}
