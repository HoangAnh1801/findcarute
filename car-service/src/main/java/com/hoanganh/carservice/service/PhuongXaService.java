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
    XeRepository xeRepository;

    public List<PhuongXa> getAllPhuongXa(String keySearch) {
        if (keySearch == null || keySearch.length() == 0 || keySearch.equals("null")) {
            return phuongXaRepository.findAll();
        }
        return phuongXaRepository.findAllByTenContaining(keySearch);
    }

    public Optional<PhuongXa> getPhuongXaById(Long id) {
        return phuongXaRepository.findById(id);
    }

    public Reponse savePhuongXa(PhuongXa phuongXa) {
        PhuongXa exitsPhuongXa = phuongXaRepository.findAllByTen(phuongXa.getTen());

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
        boolean checkExist = xeRepository.existsXeByPhuongXaId(id);
        if(checkExist) {
            return new Reponse(HttpStatus.BAD_REQUEST, "Phường xã đã được sử dụng. Vui lòng xoá danh mục con trước!");
        }
        phuongXaRepository.deleteById(id);
        return new Reponse(HttpStatus.OK, "Đã xoá phường xã");
    }

    public List<PhuongXa> findByQuanHuyenId(Long id) {
        return phuongXaRepository.findAllByQuanHuyenId(id);
    }
}
