package com.hoanganh.carservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class QuanHuyen extends BaseEntity {

    @NotBlank(message = "{com.sdt.validate.notblank}")
    private String ten;

    private String urlAnh;

    @OneToMany(mappedBy = "quanHuyen")
    @JsonIgnore
    private List<PhuongXa> phuongXas;
}