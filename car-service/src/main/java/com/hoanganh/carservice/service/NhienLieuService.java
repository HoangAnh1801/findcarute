package com.hoanganh.carservice.service;

import com.hoanganh.carservice.entity.NhienLieu;
import com.hoanganh.carservice.reponse.Reponse;
import com.hoanganh.carservice.repository.NhienLieuRepository;
import com.hoanganh.carservice.repository.XeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhienLieuService {
    @Autowired
    NhienLieuRepository nhienLieuRepository;

    @Autowired
    XeRepository xeRepository;

    public List<NhienLieu> getAllNhienLieu (String keySearch) {
        if (keySearch == null || keySearch.length() == 0 || keySearch.equals("null")) {
            return nhienLieuRepository.findAll();
        }
        return nhienLieuRepository.findAllByTenContaining(keySearch);
    }

    public Optional<NhienLieu> getNhienLieuById(Long id) {
        return nhienLieuRepository.findById(id);
    }

    public Reponse saveNhienLieu(NhienLieu nhienLieu) {
        NhienLieu exitsNhienLieu = nhienLieuRepository.findNhienLieuByTen(nhienLieu.getTen());

        if (nhienLieu.getId() != null && exitsNhienLieu!= null) {
            if (nhienLieu.getId() != exitsNhienLieu.getId()) {
                return new Reponse(HttpStatus.BAD_REQUEST, "Nhiên liệu đã tồn tại");
            }
            nhienLieuRepository.save(nhienLieu);
            return new Reponse(HttpStatus.OK, "Thêm mới thành công!");
        }

        if(exitsNhienLieu != null) {
            return new Reponse(HttpStatus.BAD_REQUEST, "Nhiên liệu đã tồn tại");
        }
        nhienLieuRepository.save(nhienLieu);
        return new Reponse(HttpStatus.OK, "Thêm mới thành công!");
    }

    public Reponse deleteNhienLieu(Long id) {
        boolean checkExist = xeRepository.existsXeByNhienLieuId(id);
        if(checkExist) {
            return new Reponse(HttpStatus.BAD_REQUEST, "Nhiên liệu đã được sử dụng. Vui lòng xoá danh mục con trước!");
        }
        nhienLieuRepository.deleteById(id);
        return new Reponse(HttpStatus.OK, "Đã xoá nhiên liệu");
    }
}
