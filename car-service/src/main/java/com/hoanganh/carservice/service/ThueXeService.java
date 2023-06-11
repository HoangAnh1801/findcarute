package com.hoanganh.carservice.service;

import com.hoanganh.carservice.entity.ThueXe;
import com.hoanganh.carservice.reponse.Reponse;
import com.hoanganh.carservice.repository.ThueXeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


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
        return thueXeRepository.findAllByXeNguoiDungIdAndTrangThaiHuyIsFalse(pageable, id);
    }

    public Page<ThueXe> findAllByNguoiDungId(Pageable pageable, Long id) {
        return thueXeRepository.findAllByNguoiDungId(pageable, id);
    }

    public Reponse duyetThueXe(Long id) {
        ThueXe thueXe = thueXeRepository.findById(id).get();
        thueXe.setTrangThaiDuyet(true);
        thueXe.setTrangThaiHoanXe(false);
        thueXe.setTrangThaiNhanXe(false);
        thueXeRepository.save(thueXe);
        return new Reponse(HttpStatus.OK, "Duyệt thành công!");
    }

    public Reponse huyDuyetThueXe(Long id) {
        ThueXe thueXe = thueXeRepository.findById(id).get();
        thueXe.setTrangThaiDuyet(false);
        thueXeRepository.save(thueXe);
        return new Reponse(HttpStatus.OK, "Huỷ duyệt yêu cầu thuê xe!");
    }

    public Reponse xacNhanHoanXe(Long id) {
        ThueXe thueXe = thueXeRepository.findById(id).get();
        thueXe.setTrangThaiHoanXe(true);
        thueXeRepository.save(thueXe);
        return new Reponse(HttpStatus.OK, "Đã xác nhận hoàn xe!");
    }

    public List<ThueXe> findThueXeByXeId(long id) {
        return thueXeRepository.findByXeId(id);
    }

    public Reponse huyThueXe(Long id) {
        ThueXe thueXe = thueXeRepository.findById(id).get();
        thueXe.setTrangThaiHuy(true);
        thueXeRepository.save(thueXe);
        return new Reponse(HttpStatus.OK, "Đã huỷ thuê xe!");
    }

    public Reponse deleteYeuCauThueXe(Long id) {
        thueXeRepository.deleteById(id);
        return new Reponse(HttpStatus.OK, "Đã xoá yêu cầu thuê xe!");
    }

    public Reponse daNhanXe(Long id) {
        ThueXe thueXe = thueXeRepository.findById(id).get();
        thueXe.setTrangThaiNhanXe(Boolean.TRUE);
        thueXeRepository.save(thueXe);
        return new Reponse(HttpStatus.OK, "Xác nhận đã nhận xe!");
    }
}
