package com.hoanganh.carservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@Entity
public class TinhNangXe extends BaseEntity {
    private String name;

    @ManyToMany(mappedBy = "tinhNangs", cascade = {CascadeType.MERGE})
    @JsonIgnore
    private List<Xe> xes;
}