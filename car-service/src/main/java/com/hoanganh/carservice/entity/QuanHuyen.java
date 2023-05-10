package com.hoanganh.carservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
public class QuanHuyen extends BaseEntity {

    @NotBlank(message = "{com.sdt.validate.notblank}")
    private String name;

    private String urlImage;

    @OneToMany(mappedBy = "quanHuyen")
    @JsonIgnore
    private List<PhuongXa> phuongXas;
}