package com.hoanganh.carservice.repository;

import com.hoanganh.carservice.entity.QuanHuyen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuanHuyenRepository extends JpaRepository<QuanHuyen, Long> {
    QuanHuyen findByName(String name);

    List<QuanHuyen> findAllByNameContaining(String name);
}
