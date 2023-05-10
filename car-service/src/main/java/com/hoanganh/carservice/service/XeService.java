package com.hoanganh.carservice.service;

import com.hoanganh.carservice.dto.XeImageDTO;
import com.hoanganh.carservice.entity.Xe;
import com.hoanganh.carservice.entity.XeImage;
import com.hoanganh.carservice.reponse.Reponse;
import com.hoanganh.carservice.repository.XeImageRepository;
import com.hoanganh.carservice.repository.XeRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Xe> getAllXe() {
        return xeRepository.findAll();
    }

    public Optional<Xe> getXeById(Long id) {
        return xeRepository.findById(id);
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
        System.out.println(xe);
        return xeRepository.save(xe);
    }

    public Reponse deleteXe(Long id) {
        xeRepository.deleteById(id);
        return new Reponse(HttpStatus.OK, "Đã xoá bài đăng");
    }
}
