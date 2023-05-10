package com.hoanganh.carservice.service;

import com.hoanganh.carservice.entity.HangXe;
import com.hoanganh.carservice.entity.LoaiXe;
import com.hoanganh.carservice.reponse.Reponse;
import com.hoanganh.carservice.repository.HangXeRepository;
import com.hoanganh.carservice.repository.LoaiXeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HangXeService {
    @Autowired
    HangXeRepository hangXeRepository;

    public List<HangXe> getAllHangXe() {
        return hangXeRepository.findAll();
    }

    public Optional<HangXe> getHangXeById(Long id) {
        return hangXeRepository.findById(id);
    }

    public HangXe saveHangXe(HangXe hangXe) {
        return hangXeRepository.save(hangXe);
    }

    public Reponse deleteHangXe(Long id) {
        hangXeRepository.deleteById(id);
        return new Reponse(HttpStatus.OK, "ok");
    }
}
