package com.hoanganh.carservice.service;

import com.hoanganh.carservice.entity.PhuongXa;
import com.hoanganh.carservice.reponse.Reponse;
import com.hoanganh.carservice.repository.XeRepository;
import com.hoanganh.carservice.repository.PhuongXaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhuongXaService {
    @Autowired
    PhuongXaRepository phuongXaRepository;

    @Autowired
    XeRepository baiDangRepository;

    public List<PhuongXa> getAllPhuongXa() {
        return phuongXaRepository.findAll();
    }

    public Optional<PhuongXa> getPhuongXaById(Long id) {
        return phuongXaRepository.findById(id);
    }

    public Reponse savePhuongXa(PhuongXa phuongXa) {
        PhuongXa exitsPhuongXa = phuongXaRepository.findAllByName(phuongXa.getName());

        if (phuongXa.getId() != null && exitsPhuongXa!= null) {
            if (phuongXa.getId() != exitsPhuongXa.getId()) {
                return new Reponse(HttpStatus.BAD_REQUEST, "Tên phường xã đã tồn tại");

            }
            phuongXaRepository.save(phuongXa);
            return new Reponse(HttpStatus.OK, "Lưu phường xã thành công");
        }

        if(exitsPhuongXa != null) {
            return new Reponse(HttpStatus.BAD_REQUEST, "Tên phường xã đã tồn tại");
        }
        phuongXaRepository.save(phuongXa);

        return new Reponse(HttpStatus.OK, "Lưu phường xã thành công");
    }

    public Reponse deletePhuongXa(Long id) {
        boolean checkExist = baiDangRepository.existsBaiDangByPhuongXaId(id);

        if(checkExist) {
            return new Reponse(HttpStatus.BAD_REQUEST, "Phường xã đã tồn tại. Vui lòng xoá danh mục con trước!");
        }
        phuongXaRepository.deleteById(id);
        return new Reponse(HttpStatus.OK, "Đã xoá phường xã");
    }

    public List<PhuongXa> findByQuanHuyenId(Long id) {
        return phuongXaRepository.findAllByQuanHuyenId(id);
    }
}
