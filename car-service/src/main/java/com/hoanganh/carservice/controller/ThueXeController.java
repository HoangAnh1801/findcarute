package com.hoanganh.carservice.controller;

import com.hoanganh.carservice.entity.ThueXe;
import com.hoanganh.carservice.entity.Xe;
import com.hoanganh.carservice.reponse.Reponse;
import com.hoanganh.carservice.service.ThueXeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/car/v1/thuexe")
public class ThueXeController {
    @Autowired
    ThueXeService thueXeService;

    @GetMapping()
    public Page<ThueXe> getAllListXe(@RequestParam("page") int page,
                                     @RequestParam(value = "limit") int limit,
                                     @RequestParam(name = "sortName", required = false, defaultValue = "DESC") String sortname,
                                     @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortby,
                                     @RequestParam(name = "trangThai", required = false) Boolean trangThai) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortname), sortby);
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        return thueXeService.getAllListThueXe(pageable, trangThai);
    }

    @GetMapping("/nguoidungid")
    public Page<ThueXe> findByXeNguoiDungId(@RequestParam("page") int page,
                                            @RequestParam(value = "limit") int limit,
                                            @RequestParam(name = "sortName", required = false, defaultValue = "DESC") String sortname,
                                            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortby,
                                            @RequestParam("id") Long id){
        Sort sort = Sort.by(Sort.Direction.fromString(sortname), sortby);
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        return thueXeService.findByXeNguoiDungId(pageable, id);
    }

    @GetMapping("/nguoidung")
    public Page<ThueXe> findByNguoiDungId(@RequestParam("page") int page,
                                            @RequestParam(value = "limit") int limit,
                                            @RequestParam(name = "sortName", required = false, defaultValue = "DESC") String sortname,
                                            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortby,
                                            @RequestParam("id") Long id){
        Sort sort = Sort.by(Sort.Direction.fromString(sortname), sortby);
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        return thueXeService.findAllByNguoiDungId(pageable, id);
    }

    @GetMapping("/duyet")
    public Reponse duyetThueXe(@RequestParam("id") Long id) {
        return thueXeService.duyetThueXe(id);
    }

    @GetMapping("/huyduyet")
    public Reponse huyDuyetThueXe(@RequestParam("id") Long id) {
        return thueXeService.huyDuyetThueXe(id);
    }

    @GetMapping("/hoanxe")
    public Reponse xacNhanHoanXe(@RequestParam("id") Long id) {
        return thueXeService.xacNhanHoanXe(id);
    }

    @PostMapping()
    public ThueXe save(@RequestBody ThueXe thueXe) {
        return thueXeService.save(thueXe);
    }
}
