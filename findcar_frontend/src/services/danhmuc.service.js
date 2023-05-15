import http from "@/http-common";

class DanhMuc {
    getAll(entity, params) {
        return http.get("/car/v1/"+`${entity}`, { params: params });
    }

    findByID(entity,id) {
        return http.get("/car/v1/"+`${entity}/` + id);
    }

    add(entity,data) {
        return http.post("/car/v1/"+`${entity}`, data);
    }

    delete(entity, params) {
        return http.delete("/car/v1/"+`${entity}` + "/id", { params: params });
    }

    getPhuongXaByQuanHuyenId(params) {
        return http.get("/car/v1/phuongxa/quan", { params: params });
    }
}

export default new DanhMuc();