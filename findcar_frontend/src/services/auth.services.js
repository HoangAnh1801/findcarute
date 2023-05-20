import http from "@/http-common";
import TokenService from "@/services/permission.service"

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

}

export default new AuthServices();