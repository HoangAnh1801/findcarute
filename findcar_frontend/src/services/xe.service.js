import http from "@/http-common";

class Xe {
    getAll(params) {
        return http.get("/car/v1/xe", {params: params});
    }

    findByID(id) {
        return http.get("/car/v1/xe/"+ id);
    }

    findByNguoiDungID(params) {
        return http.get("/car/v1/xe/nguoidung", { params: params });
    }

    duyetXe(id) {
        return http.get("/car/v1/xe/huyduyet?id=" + id);
    }

    huyDuyetXe(id) {
        return http.get("/car/v1/xe/duyetxe?id=" + id);
    }

    daNhanXe(id) {
        return http.get("/car/v1/thuexe/nhanxe?id=" + id);
    }

    add(data) {
        return http.post("/car/v1/xe", data);
    }

    addImage(data) {
        return http.post("/car/v1/xeimage", data);
    }

    delete(id) {
        return http.delete("/car/v1/xe/id?id=" + id);
    }

    saveThueXe(data) {
        return http.post("/car/v1/thuexe", data);
    }

    findThueXeByChuXe(params) {
        return http.get("/car/v1/thuexe/nguoidungid", { params: params })
    }

    findThueXeByNguoiDung(params) {
        return http.get("/car/v1/thuexe/nguoidung", { params: params })
    }

    duyetThueXe(id) {
        return http.get("/car/v1/thuexe/duyet?id=" + id);
    }

    xacNhanHoanXe(id) {
        return http.get("/car/v1/thuexe/hoanxe?id=" + id);
    }

    findThueXeByXeId(id) {
        return http.get("/car/v1/thuexe/xeid?id=" + id);
    }

    huyThueXe(id) {
        return http.get("/car/v1/thuexe/huythue?id=" + id);
    }

    deleteYeuCau(id) {
        return http.delete("/car/v1/thuexe?id=" + id);
    }
}

export default new Xe();