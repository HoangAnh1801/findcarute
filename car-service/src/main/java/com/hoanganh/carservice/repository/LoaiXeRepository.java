package com.hoanganh.carservice.repository;

import com.hoanganh.carservice.entity.LoaiXe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoaiXeRepository extends JpaRepository<LoaiXe, Long> {
    LoaiXe findLoaiXeByTen(String name);

    List<LoaiXe> findAllByTenContaining(String name);
}
