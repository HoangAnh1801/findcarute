package com.hoanganh.carservice.repository;

import com.hoanganh.carservice.entity.NhienLieu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NhienLieuRepository extends JpaRepository<NhienLieu, Long> {
    NhienLieu findNhienLieuByTen(String name);

    List<NhienLieu> findAllByTenContaining(String name);
}
