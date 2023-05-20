package com.hoanganh.carservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PhuongXa extends BaseEntity {

    @NotBlank(message = "{com.sdt.validate.notblank}")
    private String name;

    @ManyToOne
    @JoinColumn(name = "quanHuyenId")
    @ToString.Exclude
    private QuanHuyen quanHuyen;

    @OneToMany(mappedBy = "phuongXa")
    @JsonIgnore
    @ToString.Exclude
    private List<Xe> xes;
}