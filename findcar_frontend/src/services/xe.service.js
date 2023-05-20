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
        return http.get("/car/v1/xe/duyetxe?id=" + id);
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

    duyetThueXe(id) {
        return http.get("/car/v1/thuexe/duyet?id=" + id);
    }
}

export default new Xe();