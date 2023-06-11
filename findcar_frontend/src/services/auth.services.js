import http from "@/http-common";
import TokenService from "@/services/permission.service"
// import authHeader from "@/services/auth-header";

class AuthServices {

    login(user) {
        return http.post("/auth/signin", {
            tenDangNhap: user.tenDangNhap,
            matKhau: user.matKhau,
        })
            .then(response => {
                TokenService.setUser(response.data);
                return response.data;
            })
    }
    getAllNguoiDung(params) {
        return http.get("/auth", params);
    }
    signUp(data) {
        return http.post("/auth/signup", data);
    }
    logout() {
        localStorage.removeItem('user');
        localStorage.removeItem('collapsed');
    }

    findByTenDangNhap(tenDN) {
        return http.get("/auth/tendangnhap?tendangnhap=" + tenDN);
    }

    duyetTaiKhoan(tenDN) {
        return http.get("/auth/duyettaikhoan?tendangnhap=" + tenDN);
    }

    khoaTaiKhoan(tenDN) {
        return http.get("/auth/khoataikhoan?tendangnhap=" + tenDN);
    }

    daNopPhi(data) {
        return http.post("car/v1/nguoidung/danop", data);
    }

    chuaNopPhi(tenDN) {
        return http.get("car/v1/nguoidung/chuanop?tendn=" + tenDN);
    }

    updateNguoiDung(data) {
        return http.post("/car/v1/nguoidung", data);
    }

}

export default new AuthServices();