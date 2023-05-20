package com.hoanganh.carservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ThueXe extends BaseEntity{
    private String diaChiGiaoXe;

    private Date ngayBatDau;

    private Date ngayKetThuc;

    private Boolean trangThaiDuyet = Boolean.FALSE;

    private Boolean trangThaiHoanXe;

    @ManyToOne(optional = false)
    @JoinColumn(name = "nguoiDungId")
    private NguoiDung nguoiDung;

    @ManyToOne(optional = false)
    @JoinColumn(name = "xeId")
    @ToString.Exclude
    private Xe xe;

}
