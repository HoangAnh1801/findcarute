package com.hoanganh.carservice.service;

import com.hoanganh.carservice.entity.LoaiXe;
import com.hoanganh.carservice.reponse.Reponse;
import com.hoanganh.carservice.repository.LoaiXeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiXeService {
    @Autowired
    LoaiXeRepository loaiXeRepository;

    public List<LoaiXe> getAllLoaiXe() {
        return loaiXeRepository.findAll();
    }

    public Optional<LoaiXe> getLoaiXeById(Long id) {
        return loaiXeRepository.findById(id);
    }

    public LoaiXe saveLoaiXe(LoaiXe loaiXe) {
        return loaiXeRepository.save(loaiXe);
    }

    public Reponse deleteLoaiXe(Long id) {
        loaiXeRepository.deleteById(id);
        return new Reponse(HttpStatus.OK, "ok");
    }
}
