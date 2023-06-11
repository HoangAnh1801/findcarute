package com.hoanganh.carservice.service;

import com.hoanganh.carservice.entity.ERole;
import com.hoanganh.carservice.entity.NguoiDung;
import com.hoanganh.carservice.entity.QuyenTruyCap;
import com.hoanganh.carservice.reponse.Reponse;
import com.hoanganh.carservice.repository.NguoiDungRepository;
import com.hoanganh.carservice.repository.QuyenTruyCapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        return nguoiDungRepository.findByTenDangNhapAndAndMatKhauAndTrangThaiDuyetIsTrue(tenDN, matkhau);
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

    public Reponse khoaTaiKhoan(String tenDN) {
        NguoiDung nguoiDung = nguoiDungRepository.findByTenDangNhap(tenDN);

        nguoiDung.setTrangThaiDuyet(false);
        nguoiDungRepository.save(nguoiDung);
        return new Reponse(HttpStatus.OK, "Đã khoá tài khoản người dùng!");
    }

    public Reponse duyetTaiKhoan(String tenDN) {
        NguoiDung nguoiDung = nguoiDungRepository.findByTenDangNhap(tenDN);

        nguoiDung.setTrangThaiDuyet(true);
        nguoiDungRepository.save(nguoiDung);
        return new Reponse(HttpStatus.OK, "Đã duyệt tài khoản!");
    }

    public Reponse daNopPhi(NguoiDung nguoiDungBody) {
        NguoiDung nguoiDung = nguoiDungRepository.findByTenDangNhap(nguoiDungBody.getTenDangNhap());

        nguoiDung.setTrangThaiNopPhi(true);
        nguoiDung.setNgayNopPhi(nguoiDungBody.getNgayNopPhi());
        nguoiDungRepository.save(nguoiDung);
        return new Reponse(HttpStatus.OK, "Xác nhận đã nộp phí!");
    }

    public Reponse chuaNopPhi(String tenDN) {
        NguoiDung nguoiDung = nguoiDungRepository.findByTenDangNhap(tenDN);

        nguoiDung.setTrangThaiNopPhi(false);
        nguoiDungRepository.save(nguoiDung);
        return new Reponse(HttpStatus.OK, "Xác nhận chưa nộp phí!");
    }
}
