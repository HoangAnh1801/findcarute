package com.hoanganh.carservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
public class NhienLieu extends BaseEntity {
    @NotBlank(message = "{com.sdt.validate.notblank}")
    private String name;

    @OneToMany(mappedBy = "nhienLieu")
    @JsonIgnore
    private List<Xe> xes;
}
