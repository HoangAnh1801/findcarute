package com.hoanganh.carservice.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserSigninResponseDTO {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String tenDangNhap;
    private String sdt;

    private String hoVaTen;
    private Set<String> permissions;

    public UserSigninResponseDTO(String accessToken,Long id, String tenDangNhap, String sdt, Set<String> permissions,String hoVaTen) {
        this.token = accessToken;
        this.id = id;
        this.tenDangNhap = tenDangNhap;
        this.sdt = sdt;
        this.permissions = permissions;
        this.hoVaTen = hoVaTen;
    }
}
