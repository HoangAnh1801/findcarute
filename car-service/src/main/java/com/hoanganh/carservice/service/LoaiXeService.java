package com.hoanganh.carservice.service;

import com.hoanganh.carservice.entity.LoaiXe;
import com.hoanganh.carservice.reponse.Reponse;
import com.hoanganh.carservice.repository.LoaiXeRepository;
import com.hoanganh.carservice.repository.XeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiXeService {
    @Autowired
    LoaiXeRepository loaiXeRepository;

    @Autowired
    XeRepository xeRepository;

    public List<LoaiXe> getAllLoaiXe(String keySearch) {
        if (keySearch == null || keySearch.length() == 0 || keySearch.equals("null")) {
            return loaiXeRepository.findAll();
        }
        return loaiXeRepository.findAllByTenContaining(keySearch);
    }

    public Optional<LoaiXe> getLoaiXeById(Long id) {
        return loaiXeRepository.findById(id);
    }

    public Reponse saveLoaiXe(LoaiXe loaiXe) {
        LoaiXe existLoaiXe = loaiXeRepository.findLoaiXeByTen(loaiXe.getTen());
        if (loaiXe.getId() != null && existLoaiXe!= null) {
            if (loaiXe.getId() != existLoaiXe.getId()) {
                return new Reponse(HttpStatus.BAD_REQUEST, "Loại xe đã tồn tại");
            }
            loaiXeRepository.save(loaiXe);
            return new Reponse(HttpStatus.OK, "Lưu thành công!");
        }

        if(existLoaiXe != null) {
            return new Reponse(HttpStatus.BAD_REQUEST, "Loại xe đã tồn tại");
        }

        loaiXeRepository.save(loaiXe);
        return new Reponse(HttpStatus.OK, "Lưu thành công!");
    }

    public Reponse deleteLoaiXe(Long id) {
        boolean checkExist = xeRepository.existsXeByLoaiXeId(id);

        if(checkExist) {
            return new Reponse(HttpStatus.BAD_REQUEST, "Loại xe đang được sử dụng. Vui lòng xoá danh mục con trước!");
        }

        loaiXeRepository.deleteById(id);
        return new Reponse(HttpStatus.OK, "Đã xoá loại xe");
    }
}
