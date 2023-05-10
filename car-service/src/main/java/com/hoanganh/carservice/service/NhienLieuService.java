package com.hoanganh.carservice.service;

import com.hoanganh.carservice.entity.NhienLieu;
import com.hoanganh.carservice.repository.NhienLieuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhienLieuService {
    @Autowired
    NhienLieuRepository nhienLieuRepository;

    public List<NhienLieu> getAllNhienLieu () {
        return nhienLieuRepository.findAll();
    }

    public Optional<NhienLieu> getNhienLieuById(Long id) {
        return nhienLieuRepository.findById(id);
    }

    public NhienLieu saveNhienLieu(NhienLieu nhienLieu) {
        return nhienLieuRepository.save(nhienLieu);
    }
}
