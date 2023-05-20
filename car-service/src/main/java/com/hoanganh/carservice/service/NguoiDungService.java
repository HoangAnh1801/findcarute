package com.hoanganh.carservice.service;

import com.hoanganh.carservice.entity.ERole;
import com.hoanganh.carservice.entity.NguoiDung;
import com.hoanganh.carservice.entity.QuyenTruyCap;
import com.hoanganh.carservice.repository.NguoiDungRepository;
import com.hoanganh.carservice.repository.QuyenTruyCapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NguoiDungService {
    @Autowired
    NguoiDungRepository nguoiDungRepository;

    @Autowired
    QuyenTruyCapRepository quyenTruyCapRepository;

    public List<NguoiDung> getAllNguoiDung(String keySearch) {
        if (keySearch == null || keySearch.length() == 0 || keySearch.equals("null")) {
            return nguoiDungRepository.findAll();
        }
        return nguoiDungRepository.findAllByHoTenContaining(keySearch);
    }

    public NguoiDung save(NguoiDung nguoiDung) {
        return nguoiDungRepository.save(nguoiDung);
    }

    public NguoiDung findByTenDangNhapAndMatKhau(String tenDN, String matkhau) {
        return nguoiDungRepository.findByTenDangNhapAndAndMatKhau(tenDN, matkhau);
    }

    public Boolean existsByTenDangNhap(String tenDangNhap) {
        return nguoiDungRepository.existsByTenDangNhap(tenDangNhap);
    }

    public QuyenTruyCap getOneByRoleName(ERole rolename) {
        return quyenTruyCapRepository.findByTenQuyen(rolename);
    }

    public NguoiDung findByTenDangNhap(String tenDangNhap) {
        return nguoiDungRepository.findByTenDangNhap(tenDangNhap);
    }
}
