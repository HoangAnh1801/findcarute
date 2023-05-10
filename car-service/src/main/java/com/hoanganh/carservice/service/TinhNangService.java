package com.hoanganh.carservice.service;

import com.hoanganh.carservice.entity.QuanHuyen;
import com.hoanganh.carservice.entity.TinhNangXe;
import com.hoanganh.carservice.reponse.Reponse;
import com.hoanganh.carservice.repository.PhuongXaRepository;
import com.hoanganh.carservice.repository.QuanHuyenRepository;
import com.hoanganh.carservice.repository.TinhNangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TinhNangService {
    @Autowired
    TinhNangRepository tinhNangRepository;

    public List<TinhNangXe> getAllTinhNang() {
        return tinhNangRepository.findAll();
    }

    public Optional<TinhNangXe> getTinhNangById(Long id) {
        return tinhNangRepository.findById(id);
    }

    public TinhNangXe save(TinhNangXe tinhNang) {

//        if (quanHuyen.getId() != null && exitsQuanHuyen!= null) {
//            if (quanHuyen.getId() != exitsQuanHuyen.getId()) {
//                new Reponse(HttpStatus.BAD_REQUEST, "Tên quận huyện đã tồn tại");
//            }
//
//            return quanHuyenRepository.save(quanHuyen);
//        }
//
//        if(exitsQuanHuyen != null) {
//            new Reponse(HttpStatus.BAD_REQUEST, "Tên quận huyện đã tồn tại");
//        }

        return tinhNangRepository.save(tinhNang);
    }

    public Reponse deleteTinhNang(Long id) {
//        boolean checkExist = tinhNangRepository.existsAllByQuanHuyenId(id);

//        if(checkExist) {
//            return new Reponse(HttpStatus.BAD_REQUEST, "Quận huyện đã tồn tại. Vui lòng xoá danh mục con trước!");
//        }
        tinhNangRepository.deleteById(id);
        return new Reponse(HttpStatus.OK, "Đã xoá quận huyện");
    }
}
