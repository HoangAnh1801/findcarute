package com.hoanganh.carservice.controller;

import com.hoanganh.carservice.entity.HangXe;
import com.hoanganh.carservice.reponse.Reponse;
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
    public List<HangXe> getAll(@RequestParam(name = "search", required = false) String search) {
        return hangXeService.getAllHangXe(search);
    }

    @GetMapping("/{id}")
    public Optional<HangXe> getLoaiXeById(@PathVariable("id") Long id) {
        return hangXeService.getHangXeById(id);
    }

    @PostMapping()
    public HangXe saveLoaiXe(@RequestBody HangXe hangXe) {
        return hangXeService.saveHangXe(hangXe);
    }

    @DeleteMapping("/id")
    public Reponse deleteHangXe(@RequestParam("id") Long id) {
        return hangXeService.deleteHangXe(id);
    }
}
