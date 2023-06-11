package com.hoanganh.carservice.service;

import com.hoanganh.carservice.dto.XeImageDTO;
import com.hoanganh.carservice.entity.Xe;
import com.hoanganh.carservice.entity.XeAnh;
import com.hoanganh.carservice.reponse.Reponse;
import com.hoanganh.carservice.repository.XeImageRepository;
import com.hoanganh.carservice.repository.XeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class XeService {
    @Autowired
    XeRepository xeRepository;

    @Autowired
    XeImageRepository xeImageRepository;

    public Page<Xe> getAllXe(Pageable pageable, String keyword, Boolean trangThaiDuyet, Long quanHuyenId, Long loaixeId, Long hangxeId) {
        if (trangThaiDuyet != null) {
            if (quanHuyenId != null && loaixeId == null && hangxeId == null) {
                if (keyword == null || keyword.length() == 0 || keyword.equals("null")) {
                    return xeRepository.findAllByPhuongXaQuanHuyenIdAndTrangThaiDuyetIsTrue(pageable, quanHuyenId);
                }
                return xeRepository.findAllByPhuongXaQuanHuyenIdAndTenXeContainingAndTrangThaiDuyetIsTrue(pageable, quanHuyenId, keyword);
            }

            if (quanHuyenId != null && loaixeId != null && hangxeId == null) {
                if (keyword == null || keyword.length() == 0 || keyword.equals("null")) {
                    return xeRepository.findAllByLoaiXeIdAndPhuongXaQuanHuyenIdAndTrangThaiDuyetIsTrue(pageable, loaixeId, quanHuyenId);
                }
                return xeRepository.findAllByLoaiXeIdAndPhuongXaQuanHuyenIdAndTrangThaiDuyetIsTrueAndTenXeContaining(pageable, loaixeId, quanHuyenId, keyword);
            }

            if (quanHuyenId != null && loaixeId == null && hangxeId != null) {
                if (keyword == null || keyword.length() == 0 || keyword.equals("null")) {
                    return  xeRepository.findAllByHangXeIdAndPhuongXaQuanHuyenIdAndTrangThaiDuyetIsTrue(pageable, hangxeId, quanHuyenId);
                }
                return  xeRepository.findAllByHangXeIdAndPhuongXaQuanHuyenIdAndTrangThaiDuyetIsTrueAndTenXeContaining(pageable, hangxeId, quanHuyenId, keyword);
            }

            if (quanHuyenId != null && loaixeId == null && hangxeId != null) {
                if (keyword == null || keyword.length() == 0 || keyword.equals("null")) {
                    return xeRepository.findAllByLoaiXeIdAndHangXeIdAndPhuongXaQuanHuyenIdAndTrangThaiDuyetIsTrue(pageable, loaixeId, hangxeId, quanHuyenId);
                }
                return xeRepository.findAllByLoaiXeIdAndHangXeIdAndPhuongXaQuanHuyenIdAndTrangThaiDuyetIsTrueAndTenXeContaining(pageable, loaixeId, hangxeId, quanHuyenId, keyword);
            }

            if (quanHuyenId == null && loaixeId != null && hangxeId == null) {
                if (keyword == null || keyword.length() == 0 || keyword.equals("null")) {
                    return xeRepository.findAllByLoaiXeIdAndTrangThaiDuyetIsTrue(pageable, loaixeId);
                }
                return xeRepository.findAllByLoaiXeIdAndTenXeContainingAndTrangThaiDuyetIsTrue(pageable, loaixeId, keyword);
            }

            if (quanHuyenId == null && loaixeId == null && hangxeId != null) {
                if (keyword == null || keyword.length() == 0 || keyword.equals("null")) {
                    return xeRepository.findAllByHangXeIdAndTrangThaiDuyetIsTrue(pageable, hangxeId);
                }
                return xeRepository.findAllByHangXeIdAndTenXeContainingAndTrangThaiDuyetIsTrue(pageable, hangxeId, keyword);
            }

            if (quanHuyenId == null && loaixeId != null && hangxeId != null) {
                if (keyword == null || keyword.length() == 0 || keyword.equals("null")) {
                    return xeRepository.findAllByHangXeIdAndLoaiXeIdAndTrangThaiDuyetIsTrue(pageable,hangxeId, loaixeId);
                }
                return xeRepository.findAllByHangXeIdAndLoaiXeIdAndTenXeContainingAndTrangThaiDuyetIsTrue(pageable, hangxeId, loaixeId, keyword);
            }

            if (quanHuyenId != null && loaixeId != null && hangxeId != null) {
                if (keyword == null || keyword.length() == 0 || keyword.equals("null")) {
                    return  xeRepository.findAllByLoaiXeIdAndHangXeIdAndPhuongXaQuanHuyenIdAndTrangThaiDuyetIsTrue(pageable, loaixeId, hangxeId, quanHuyenId);
                }
                return  xeRepository.findAllByLoaiXeIdAndHangXeIdAndPhuongXaQuanHuyenIdAndTrangThaiDuyetIsTrueAndTenXeContaining(pageable, loaixeId, hangxeId, quanHuyenId, keyword);
            }

            if (keyword == null || keyword.length() == 0 || keyword.equals("null")) {
                return xeRepository.findAllByTrangThaiDuyet(pageable, trangThaiDuyet);
            } else {
                return xeRepository.findAllByTenXeContaining(pageable, keyword);
            }

        }

        if (keyword == null || keyword.length() == 0 || keyword.equals("null")) {
            return xeRepository.findAll(pageable);
        }

        return xeRepository.findAllByTenXeContaining(pageable, keyword);
    }

    public Optional<Xe> getXeById(Long id) {
        return xeRepository.findById(id);
    }

    public Page<Xe> getXeByNguoiDungId(Pageable pageable, String keyword, Long id) {

        if (keyword == null || keyword.length() == 0 || keyword.equals("null")) {
            return xeRepository.findAllByNguoiDungId(pageable, id);
        }
        return xeRepository.findAllByTenXeContainingAndNguoiDungId(pageable, keyword, id);
    }

    public XeAnh saveImage(XeImageDTO xeImageDTO) {
        XeAnh xeAnh = new XeAnh();

        xeAnh.setTenAnh(xeImageDTO.getTenAnh());
        xeAnh.setUrlAnh(xeImageDTO.getUrlAnh());
        xeAnh.setXe(xeImageDTO.getXe());
        if (xeImageDTO.getId() != null) {
            xeAnh.setId(xeImageDTO.getId());
        }

        return xeImageRepository.save(xeAnh);
    }

    @Transactional
    public Xe saveXe(Xe xe) {
        return xeRepository.save(xe);
    }

    public void duyetXe(Long id) {
        Xe xe = xeRepository.findById(id).get();
        xe.setTrangThaiDuyet(true);
        xeRepository.save(xe);
    }

    public void huyDuyetXe(Long id) {
        Xe xe = xeRepository.findById(id).get();
        xe.setTrangThaiDuyet(false);
        xeRepository.save(xe);
    }

    public Reponse deleteXe(Long id) {
        xeRepository.deleteById(id);
        return new Reponse(HttpStatus.OK, "Xoá thành công!");
    }
}
