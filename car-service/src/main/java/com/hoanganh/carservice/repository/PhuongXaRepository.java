package com.hoanganh.carservice.repository;

import com.hoanganh.carservice.entity.PhuongXa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhuongXaRepository extends JpaRepository<PhuongXa, Long> {
    boolean existsAllByQuanHuyenId(Long id);

    PhuongXa findAllByTen(String name);

    List<PhuongXa> findAllByQuanHuyenId(Long id);

    List<PhuongXa> findAllByTenContaining(String name);
}
