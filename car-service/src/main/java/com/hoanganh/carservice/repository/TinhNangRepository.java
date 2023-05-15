package com.hoanganh.carservice.repository;

import com.hoanganh.carservice.entity.TinhNangXe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TinhNangRepository extends JpaRepository<TinhNangXe, Long> {
    TinhNangXe findTinhNangXeByName(String name);

    List<TinhNangXe> findAllByNameContaining(String name);
}
