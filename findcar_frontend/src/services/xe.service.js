import http from "@/http-common";

class Xe {
    getAll() {
        return http.get("/car/v1/xe");
    }

    findByID(id) {
        return http.get("/car/v1/xe/"+ id);
    }

    add(data) {
        return http.post("/car/v1/xe", data);
    }

    addImage(data) {
        return http.post("/car/v1/xeimage", data);
    }

    // delete(entity, params) {
    //     return http.delete("/car/v1/"+`${entity}` + "/id", { params: params });
    // }

}

export default new Xe();