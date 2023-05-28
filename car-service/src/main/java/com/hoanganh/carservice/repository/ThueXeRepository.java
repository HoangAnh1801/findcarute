package com.hoanganh.carservice.repository;

import com.hoanganh.carservice.entity.ThueXe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface ThueXeRepository extends PagingAndSortingRepository<ThueXe, Long> {
    Page<ThueXe> findAllByTrangThaiDuyet(Pageable pageable, Boolean trangThai);

    Page<ThueXe> findAllByXeNguoiDungIdAndTrangThaiHuyIsFalse(Pageable pageable, Long id);

    Page<ThueXe> findAllByNguoiDungId(Pageable pageable, Long id);

    List<ThueXe> findByXeId(Long id);
}
