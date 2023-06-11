package com.hoanganh.carservice.controller;

import com.hoanganh.carservice.entity.Xe;
import com.hoanganh.carservice.reponse.Reponse;
import com.hoanganh.carservice.service.XeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/car/v1/xe")
public class XeController {
    @Autowired
    XeService xeService;

    @GetMapping()
    public Page<Xe> getAllListXe(@RequestParam("page") int page,
                                 @RequestParam(value = "limit") int limit,
                                 @RequestParam(name = "sortName", required = false, defaultValue = "DESC") String sortname,
                                 @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortby,
                                 @RequestParam(name = "trangThai", required = false) Boolean trangThai,
                                 @RequestParam(name = "quanid", required = false) Long quanid,
                                 @RequestParam(name = "loaixeid", required = false) Long loaixeid,
                                 @RequestParam(name = "hangxeid", required = false) Long hangxeid,
                                 @RequestParam(name = "search", required = false) String keyword) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortname), sortby);
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        return xeService.getAllXe(pageable, keyword, trangThai, quanid, loaixeid, hangxeid);
    }

    @GetMapping("/nguoidung")
    public Page<Xe> getAllByNguoiDungId(@RequestParam("page") int page,
                                        @RequestParam(value = "limit") int limit,
                                        @RequestParam(name = "sortName", required = false, defaultValue = "DESC") String sortname,
                                        @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortby,
                                        @RequestParam(name = "search", required = false) String keyword,
                                        @RequestParam("id") Long id) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortname), sortby);
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        return xeService.getXeByNguoiDungId(pageable, keyword, id);
    }

    @GetMapping("/{id}")
    public Optional<Xe> getXeById(@PathVariable("id") Long id) {
        return xeService.getXeById(id);
    }

    @GetMapping("/duyetxe")
    public void duyetXe(@RequestParam("id") Long id) {
        xeService.duyetXe(id);
    }

    @GetMapping("/huyduyet")
    public void huyDuyetXe(@RequestParam("id") Long id) {
        xeService.huyDuyetXe(id);
    }

    @PostMapping()
    public Xe saveXe(@RequestBody Xe xe) {
        return xeService.saveXe(xe);
    }

    @DeleteMapping("/id")
    public Reponse delete(@RequestParam("id") Long id) {
        return xeService.deleteXe(id);
    }
}
