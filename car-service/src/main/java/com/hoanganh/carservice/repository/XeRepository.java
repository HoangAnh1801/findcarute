package com.hoanganh.carservice.repository;

import com.hoanganh.carservice.entity.TinhNangXe;
import com.hoanganh.carservice.entity.Xe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface XeRepository extends PagingAndSortingRepository<Xe, Long> {
//    Page<Xe> findAllByTenXeContainingOrHOrHangXeNameOrGiaXe(Pageable pageable, String keyword);
    Page<Xe> findAllByTenXeContaining(Pageable pageable, String keyword);

    Page<Xe> findAllByTrangThaiDuyet(Pageable pageable, Boolean trangThai);

    Page<Xe> findAll(Pageable pageable);

    boolean existsXeByPhuongXaId(Long id);

    boolean existsXeByLoaiXeId(Long id);

    boolean existsXeByNhienLieuId(Long id);

    boolean existsAllByTinhNangs(TinhNangXe tinhNangXe);

    boolean existsXeByHangXeId(Long id);

    Page<Xe> findAllByNguoiDungId(Pageable pageable, Long id);

    Page<Xe> findAllByTenXeContainingAndNguoiDungId(Pageable pageable, String Keyword, Long id);
}
