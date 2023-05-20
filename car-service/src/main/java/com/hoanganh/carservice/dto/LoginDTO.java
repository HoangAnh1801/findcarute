package com.hoanganh.carservice.dto;

import lombok.Data;
import javax.validation.constraints.*;

@Data
public class LoginDTO {
    @NotBlank
    private String tenDangNhap;

    @NotBlank
    private String matKhau;
}
