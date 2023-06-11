package com.hoanganh.carservice.controller;

import com.hoanganh.carservice.dto.*;
import com.hoanganh.carservice.entity.ERole;
import com.hoanganh.carservice.entity.NguoiDung;
import com.hoanganh.carservice.entity.QuyenTruyCap;
import com.hoanganh.carservice.reponse.Reponse;
import com.hoanganh.carservice.security.JwtUtils;
import com.hoanganh.carservice.service.NguoiDungService;
import com.hoanganh.carservice.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtils jwtUtils;
    @Autowired
    private final NguoiDungService thanhVienService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginRequest) {
        NguoiDung thanhVien = thanhVienService.findByTenDangNhapAndMatKhau(loginRequest.getTenDangNhap(), loginRequest.getMatKhau());
        if (thanhVien != null) {
            Set<String> roles = new HashSet<>();
            Set<QuyenTruyCap> quyens = thanhVien.getRoles();
            for (QuyenTruyCap quyen: quyens){
                roles.add(String.valueOf(quyen.getTenQuyen()));
            }

            String jwt = jwtUtils.generateJwtToken(loginRequest.getTenDangNhap(), roles);
            return ResponseEntity.ok(new UserSigninResponseDTO(jwt, thanhVien.getId(), thanhVien.getTenDangNhap(), thanhVien.getSdt(), roles,thanhVien.getHoTen()));
        }else {
            Boolean personCheckExist = thanhVienService.existsByTenDangNhap(loginRequest.getTenDangNhap());
            return ResponseEntity.ok(personCheckExist? new ResponseDTO<>(0, new ResponseErrorDTO(1, Constants.MESSAGE_RESPONSE.PASSWORD_INCORRECT))
                    : new ResponseDTO<>(0, new ResponseErrorDTO(0, Constants.MESSAGE_RESPONSE.UID_EXIST)));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserSignupDTO signUpRequest) {
        if (thanhVienService.existsByTenDangNhap(signUpRequest.getTenDangNhap()) ) {
            return ResponseEntity.ok("Error: Username is already taken!");
        }

        NguoiDung user = new NguoiDung(
                signUpRequest.getTenDangNhap(),
                signUpRequest.getMatKhau(),
                signUpRequest.getHoTen(),
                signUpRequest.getSdt());

        Set<String> strRoles = signUpRequest.getRole();
        Set<QuyenTruyCap> roles = new HashSet<>();
        if (strRoles == null) {
            QuyenTruyCap userRole = thanhVienService.getOneByRoleName(ERole.user);
            if (userRole == null) throw new RuntimeException("Error: Role is not found.");
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        QuyenTruyCap adminRole = thanhVienService.getOneByRoleName(ERole.admin);
                        if (adminRole == null) throw new RuntimeException("Error: Role is not found.");
                        roles.add(adminRole);
                        break;
                    case "chuxe":
                        QuyenTruyCap chuxeRole = thanhVienService.getOneByRoleName(ERole.chuxe);
                        if (chuxeRole == null) throw new RuntimeException("Error: Role is not found.");
                        roles.add(chuxeRole);
                        break;
                    default:
                        QuyenTruyCap userRole = thanhVienService.getOneByRoleName(ERole.user);
                        if (userRole == null) throw new RuntimeException("Error: Role is not found.");
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        thanhVienService.save(user);
        return ResponseEntity.ok("Đăng ký thành viên thành công!");
    }

    @GetMapping()
    public List<NguoiDung> getAllNguoiDung(@RequestParam(name = "search", required = false) String search) {
        return thanhVienService.getAllNguoiDung(search);
    }

    @GetMapping("/tendangnhap")
    public NguoiDung getByTenDangNhap(@RequestParam("tendangnhap") String tenDangNhap) {
        return thanhVienService.findByTenDangNhap(tenDangNhap);
    }

    @GetMapping("/duyettaikhoan")
    public Reponse duyetTaiKhoan(@RequestParam("tendangnhap") String tenDangNhap) {
        return thanhVienService.duyetTaiKhoan(tenDangNhap);
    }

    @GetMapping("/khoataikhoan")
    public Reponse khoaTaiKhoan(@RequestParam("tendangnhap") String tenDangNhap) {
        return thanhVienService.khoaTaiKhoan(tenDangNhap);
    }
}
