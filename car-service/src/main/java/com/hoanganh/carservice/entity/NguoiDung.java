package com.hoanganh.carservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tenDangNhap;

    private String matKhau;

    private String hoTen;

    private String sdt;

    private Boolean trangThaiDuyet = Boolean.TRUE;

    @OneToMany(mappedBy = "nguoiDung", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<Xe> xes = new ArrayList<>();

    @OneToMany(mappedBy = "nguoiDung", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<ThueXe> thueXes = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "nguoiDungQuyen",
            joinColumns = @JoinColumn(name = "idNguoiDung"),
            inverseJoinColumns = @JoinColumn(name = "maQuyen"))
    private Set<QuyenTruyCap> roles = new HashSet<>();

    public NguoiDung(String tenDangNhap, String matKhau, String hoTen, String sdt) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.sdt = sdt;
    }
}
