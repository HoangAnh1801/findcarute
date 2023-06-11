package com.hoanganh.carservice.repository;

import com.hoanganh.carservice.entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {
    List<NguoiDung> findAllByHoTenContaining(String name);

    NguoiDung findByTenDangNhapAndAndMatKhauAndTrangThaiDuyetIsTrue(String tenDangNhap,String matKhau);

    Boolean existsByTenDangNhap(String ten);

    NguoiDung findByTenDangNhap(String ten);
}