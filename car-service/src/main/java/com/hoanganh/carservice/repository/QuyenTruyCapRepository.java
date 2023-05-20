package com.hoanganh.carservice.repository;

import com.hoanganh.carservice.entity.ERole;
import com.hoanganh.carservice.entity.QuyenTruyCap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuyenTruyCapRepository extends JpaRepository<QuyenTruyCap, Long> {
    QuyenTruyCap findByTenQuyen(ERole eRole);
}
