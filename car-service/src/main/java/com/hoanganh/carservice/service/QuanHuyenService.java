package com.hoanganh.carservice.service;

import com.hoanganh.carservice.entity.QuanHuyen;
import com.hoanganh.carservice.reponse.Reponse;
import com.hoanganh.carservice.repository.PhuongXaRepository;
import com.hoanganh.carservice.repository.QuanHuyenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuanHuyenService {
    @Autowired
    QuanHuyenRepository quanHuyenRepository;

    @Autowired
    PhuongXaRepository phuongXaRepository;

    public List<QuanHuyen> getAllQuanHuyen() {
        return quanHuyenRepository.findAll();
    }

    public Optional<QuanHuyen> getQuanHuyenById(Long id) {
        return quanHuyenRepository.findById(id);
    }

    public QuanHuyen saveQuanHuyen(QuanHuyen quanHuyen) {
        QuanHuyen exitsQuanHuyen = quanHuyenRepository.findByName(quanHuyen.getName());
        System.out.println("exitsQuanHuyen" + exitsQuanHuyen);
        if (quanHuyen.getId() != null && exitsQuanHuyen!= null) {
            if (quanHuyen.getId() != exitsQuanHuyen.getId()) {
                new Reponse(HttpStatus.BAD_REQUEST, "Tên quận huyện đã tồn tại");
            }

            return quanHuyenRepository.save(quanHuyen);
        }

        if(exitsQuanHuyen != null) {
            new Reponse(HttpStatus.BAD_REQUEST, "Tên quận huyện đã tồn tại");
        }

        return quanHuyenRepository.save(quanHuyen);
    }

    public Reponse deleteQuanHuyen(Long id) {
        boolean checkExist = phuongXaRepository.existsAllByQuanHuyenId(id);

        if(checkExist) {
            return new Reponse(HttpStatus.BAD_REQUEST, "Quận huyện đã tồn tại. Vui lòng xoá danh mục con trước!");
        }
        quanHuyenRepository.deleteById(id);
        return new Reponse(HttpStatus.OK, "Đã xoá quận huyện");
    }
}
