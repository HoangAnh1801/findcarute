package com.hoanganh.carservice.repository;

import com.hoanganh.carservice.entity.HangXe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HangXeRepository extends JpaRepository<HangXe, Long> {
    List<HangXe> findAllByNameContaining(String name);
}
