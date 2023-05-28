package com.hoanganh.carservice.service;

import com.hoanganh.carservice.entity.TinhNangXe;
import com.hoanganh.carservice.reponse.Reponse;
import com.hoanganh.carservice.repository.TinhNangRepository;
import com.hoanganh.carservice.repository.XeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TinhNangService {
    @Autowired
    TinhNangRepository tinhNangRepository;

    @Autowired
    XeRepository xeRepository;

    public List<TinhNangXe> getAllTinhNang(String keySearch) {
        if (keySearch == null || keySearch.length() == 0 || keySearch.equals("null")) {
            return tinhNangRepository.findAll();
        }
        return tinhNangRepository.findAllByTenContaining(keySearch);
    }

    public Optional<TinhNangXe> getTinhNangById(Long id) {
        return tinhNangRepository.findById(id);
    }

    public Reponse save(TinhNangXe tinhNang) {
        TinhNangXe exitsTinhNang = tinhNangRepository.findTinhNangXeByTen(tinhNang.getTen());

        if (tinhNang.getId() != null && exitsTinhNang!= null) {
            if (tinhNang.getId() != exitsTinhNang.getId()) {
                return new Reponse(HttpStatus.BAD_REQUEST, "Tính năng đã tồn tại");
            }
            tinhNangRepository.save(tinhNang);
            return new Reponse(HttpStatus.OK, "Thêm mới thành công!");

        }

        if(exitsTinhNang != null) {
            return new Reponse(HttpStatus.BAD_REQUEST, "Tính năng đã tồn tại");
        }
        tinhNangRepository.save(tinhNang);
        return new Reponse(HttpStatus.OK, "Thêm mới thành công!");
    }

    public Reponse deleteTinhNang(Long id) {
        TinhNangXe tinhNang = new TinhNangXe();
        tinhNang.setId(id);

        boolean checkExist = xeRepository.existsAllByTinhNangs(tinhNang);

        if(checkExist) {
            return new Reponse(HttpStatus.BAD_REQUEST, "Tính năng đã được sử dụng. Vui lòng xoá danh mục con trước!");
        }
        tinhNangRepository.deleteById(id);
        return new Reponse(HttpStatus.OK, "Đã xoá tính năng!");
    }
}
