package com.hoanganh.carservice.entity;


import lombok.Data;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.List;

@Data
@Entity
public class Xe extends BaseEntity {

    private String tenXe;

    private String tieuDe;

    private Double giaXe;

    private Long sdt;

    private Long luotXem;

    private int namSX;

    private int soGhe;

    @Lob
    private String mota;

    private String anhNen;

    private String diaChi;

    private Boolean trangThaiDuyet = Boolean.TRUE;


//    @OneToMany(mappedBy = "post",cascade = CascadeType.REMOVE, orphanRemoval = true)
//    private List<PostImage> images;

    @ManyToOne
    @JoinColumn(name = "phuongXaId")
    private PhuongXa phuongXa;

    @ManyToOne
    @JoinColumn(name = "hangXeId")
    private HangXe hangXe;


    @ManyToOne
    @JoinColumn(name = "loaiXeId")
    private LoaiXe loaiXe;

    @ManyToOne
    @JoinColumn(name = "nhienLieuId")
    private NhienLieu nhienLieu;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = @JoinColumn(name = "xeId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tinhNangId", referencedColumnName = "id")
    )
    private List<TinhNangXe> tinhNangs;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "xe",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<XeImage> xeImages;

//    @ManyToOne
//    @JoinColumn(name = "typeReatlyId")
//    private TypeReatly typeReatly;

}