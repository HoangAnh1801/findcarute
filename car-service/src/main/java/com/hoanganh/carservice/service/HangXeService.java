package com.hoanganh.carservice.service;

import com.hoanganh.carservice.entity.HangXe;
import com.hoanganh.carservice.entity.LoaiXe;
import com.hoanganh.carservice.reponse.Reponse;
import com.hoanganh.carservice.repository.HangXeRepository;
import com.hoanganh.carservice.repository.LoaiXeRepository;
import com.hoanganh.carservice.repository.XeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HangXeService {
    @Autowired
    HangXeRepository hangXeRepository;

    @Autowired
    XeRepository xeRepository;

    public List<HangXe> getAllHangXe(String keySearch) {
        if (keySearch == null || keySearch.length() == 0 || keySearch.equals("null")) {
            return hangXeRepository.findAll();
        }
        return hangXeRepository.findAllByTenContaining(keySearch);
    }

    public Optional<HangXe> getHangXeById(Long id) {
        return hangXeRepository.findById(id);
    }

    public HangXe saveHangXe(HangXe hangXe) {
        return hangXeRepository.save(hangXe);
    }

    public Reponse deleteHangXe(Long id) {
        Boolean existHangXe = xeRepository.existsXeByHangXeId(id);

        if(existHangXe) {
            return new Reponse(HttpStatus.BAD_REQUEST, "Hãng xe đã được sử dụng. Vui lòng xoá danh mục con trước!");
        }

        hangXeRepository.deleteById(id);
        return new Reponse(HttpStatus.OK, "Đã xoá hãng xe!");
    }
}
