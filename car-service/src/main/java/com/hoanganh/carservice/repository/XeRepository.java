package com.hoanganh.carservice.repository;

import com.hoanganh.carservice.entity.TinhNangXe;
import com.hoanganh.carservice.entity.Xe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface XeRepository extends JpaRepository<Xe, Long> {
    boolean existsXeByPhuongXaId(Long id);

    boolean existsXeByLoaiXeId(Long id);

    boolean existsXeByNhienLieuId(Long id);

    boolean existsAllByTinhNangs(TinhNangXe tinhNangXe);

}
