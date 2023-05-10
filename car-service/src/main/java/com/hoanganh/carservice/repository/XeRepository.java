package com.hoanganh.carservice.repository;

import com.hoanganh.carservice.entity.Xe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface XeRepository extends JpaRepository<Xe, Long> {
    boolean existsBaiDangByPhuongXaId(Long id);

}
