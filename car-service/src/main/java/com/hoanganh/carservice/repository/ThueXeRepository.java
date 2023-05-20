package com.hoanganh.carservice.repository;

import com.hoanganh.carservice.entity.ThueXe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ThueXeRepository extends PagingAndSortingRepository<ThueXe, Long> {
    Page<ThueXe> findAllByTrangThaiDuyet(Pageable pageable, Boolean trangThai);

    Page<ThueXe> findAllByXeNguoiDungId(Pageable pageable, Long id);
}
