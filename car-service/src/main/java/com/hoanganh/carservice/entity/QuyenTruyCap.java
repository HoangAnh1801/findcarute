package com.hoanganh.carservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "QuyenTruyCap")
public class QuyenTruyCap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maQuyen;

    @Enumerated(EnumType.STRING)
    private ERole tenQuyen;
}
