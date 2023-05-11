package com.hoanganh.carservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tenDangNhap;

    private String hoTen;

    private String sdt;

    @OneToMany(mappedBy = "nguoiDung", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Xe> xes = new ArrayList<>();
}
