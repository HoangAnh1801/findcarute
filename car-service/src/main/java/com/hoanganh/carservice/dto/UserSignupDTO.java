package com.hoanganh.carservice.dto;

import lombok.*;

import java.util.Set;
@Data
@AllArgsConstructor
public class UserSignupDTO {
    private String tenDangNhap;
    private String matKhau;
    private String hoTen;
    private String sdt;
    private Set<String> role;
}
