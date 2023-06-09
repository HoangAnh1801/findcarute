package com.hoanganh.carservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
public class NhienLieu extends BaseEntity {
    @NotBlank(message = "{com.sdt.validate.notblank}")
    private String ten;

    @OneToMany(mappedBy = "nhienLieu")
    @JsonIgnore
    @ToString.Exclude
    private List<Xe> xes;
}
