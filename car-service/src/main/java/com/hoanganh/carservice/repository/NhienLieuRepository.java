package com.hoanganh.carservice.repository;

import com.hoanganh.carservice.entity.NhienLieu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NhienLieuRepository extends JpaRepository<NhienLieu, Long> {
    NhienLieu findNhienLieuByName(String name);
}
