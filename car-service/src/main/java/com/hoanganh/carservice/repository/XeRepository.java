package com.hoanganh.carservice.repository;

import com.hoanganh.carservice.entity.TinhNangXe;
import com.hoanganh.carservice.entity.Xe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface XeRepository extends PagingAndSortingRepository<Xe, Long> {
//    Page<Xe> findAllByTenXeContainingOrHOrHangXeContaining(Pageable pageable, String keyword);
    Page<Xe> findAllByLoaiXeIdAndPhuongXaQuanHuyenIdAndTrangThaiDuyetIsTrue(Pageable pageable, Long loaixeid, Long quanid);

    Page<Xe> findAllByLoaiXeIdAndPhuongXaQuanHuyenIdAndTrangThaiDuyetIsTrueAndTenXeContaining(Pageable pageable, Long loaixeid, Long quanid, String keySearch);

    Page<Xe> findAllByHangXeIdAndPhuongXaQuanHuyenIdAndTrangThaiDuyetIsTrue(Pageable pageable, Long hangxexeid, Long quanid);

    Page<Xe> findAllByHangXeIdAndPhuongXaQuanHuyenIdAndTrangThaiDuyetIsTrueAndTenXeContaining(Pageable pageable, Long hangxexeid, Long quanid, String keySearch);

    Page<Xe> findAllByLoaiXeIdAndHangXeIdAndPhuongXaQuanHuyenIdAndTrangThaiDuyetIsTrue(Pageable pageable, Long loaixeid, Long hangxeid, Long quanid);

    Page<Xe> findAllByLoaiXeIdAndHangXeIdAndPhuongXaQuanHuyenIdAndTrangThaiDuyetIsTrueAndTenXeContaining(Pageable pageable, Long loaixeid, Long hangxeid, Long quanid, String keySearch);

    Page<Xe> findAllByPhuongXaQuanHuyenIdAndTrangThaiDuyetIsTrue(Pageable pageable, Long quanHuyenId);

    Page<Xe> findAllByPhuongXaQuanHuyenIdAndTenXeContainingAndTrangThaiDuyetIsTrue(Pageable pageable, Long quanHuyenId, String keySearch);

    Page<Xe> findAllByLoaiXeIdAndTrangThaiDuyetIsTrue(Pageable pageable, Long id);

    Page<Xe> findAllByLoaiXeIdAndTenXeContainingAndTrangThaiDuyetIsTrue(Pageable pageable, Long id, String keySearch);

    Page<Xe> findAllByHangXeIdAndTrangThaiDuyetIsTrue(Pageable pageable, Long id);

    Page<Xe> findAllByHangXeIdAndTenXeContainingAndTrangThaiDuyetIsTrue(Pageable pageable, Long id, String keySearch);

    Page<Xe> findAllByHangXeIdAndLoaiXeIdAndTrangThaiDuyetIsTrue(Pageable pageable, Long hangxeid, Long loaixeid);

    Page<Xe> findAllByHangXeIdAndLoaiXeIdAndTenXeContainingAndTrangThaiDuyetIsTrue(Pageable pageable, Long hangxeid, Long loaixeid, String keySearch);

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

//    Page<Xe> findAllByPhuongXaQuanHuyenIdAAndTenXeContaining(Pageable pageable, String keyword, Long id);
}
