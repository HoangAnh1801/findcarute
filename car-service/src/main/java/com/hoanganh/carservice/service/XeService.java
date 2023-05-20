package com.hoanganh.carservice.service;

import com.hoanganh.carservice.dto.XeImageDTO;
import com.hoanganh.carservice.entity.Xe;
import com.hoanganh.carservice.entity.XeImage;
import com.hoanganh.carservice.reponse.Reponse;
import com.hoanganh.carservice.repository.XeImageRepository;
import com.hoanganh.carservice.repository.XeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class XeService {
    @Autowired
    XeRepository xeRepository;

    @Autowired
    XeImageRepository xeImageRepository;

    public Page<Xe> getAllXe(Pageable pageable, String keyword, Boolean trangThaiDuyet) {
        if (trangThaiDuyet != null) {
            return xeRepository.findAllByTrangThaiDuyet(pageable, trangThaiDuyet);
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

    public XeImage saveImage(XeImageDTO xeImageDTO) {
        XeImage xeImage = new XeImage();

        xeImage.setNameImage(xeImageDTO.getNameImage());
        xeImage.setUrlImage(xeImageDTO.getUrlImage());
        xeImage.setXe(xeImageDTO.getXe());
        if (xeImageDTO.getId() != null) {
            xeImage.setId(xeImageDTO.getId());
        }

        return xeImageRepository.save(xeImage);
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

    public Reponse deleteXe(Long id) {
        xeRepository.deleteById(id);
        return new Reponse(HttpStatus.OK, "Xoá thành công!");
    }
}
