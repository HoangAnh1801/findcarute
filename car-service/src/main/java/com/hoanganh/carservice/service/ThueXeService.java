package com.hoanganh.carservice.service;

import com.hoanganh.carservice.entity.ThueXe;
import com.hoanganh.carservice.reponse.Reponse;
import com.hoanganh.carservice.repository.ThueXeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;



@Service
public class ThueXeService {
    @Autowired
    ThueXeRepository thueXeRepository;

    public Page<ThueXe> getAllListThueXe(Pageable pageable, Boolean trangThaiDuyet) {
        if (trangThaiDuyet != null) {
            return thueXeRepository.findAllByTrangThaiDuyet(pageable, trangThaiDuyet);
        }
        return thueXeRepository.findAll(pageable);
    }

    public ThueXe save(ThueXe thueXe) {
        return thueXeRepository.save(thueXe);
    }

    public Page<ThueXe> findByXeNguoiDungId(Pageable pageable, Long id) {
        return thueXeRepository.findAllByXeNguoiDungId(pageable, id);
    }

    public Reponse duyetThueXe(Long id) {
        ThueXe thueXe = thueXeRepository.findById(id).get();
        System.out.println("thuexe" + thueXe);
        thueXe.setTrangThaiDuyet(true);
        thueXeRepository.save(thueXe);
        return new Reponse(HttpStatus.OK, "Duyệt thành công!");
    }

    public Reponse huyDuyetThueXe(Long id) {
        ThueXe thueXe = thueXeRepository.findById(id).get();
        thueXe.setTrangThaiDuyet(false);
        thueXeRepository.save(thueXe);
        return new Reponse(HttpStatus.OK, "Huỷ duyệt yêu cầu thuê xe!");
    }
}
