<template>
  <div class="bg-f9c63f">
    <div class="container py-3">
      <div class="row justify-content-center">
        <div class="col-lg-3 px-0">
          <el-select v-model="loaiXe" filterable clearable placeholder="Chọn loại xe" class="w-100 h-100">
            <el-option
                v-for="loaixe in listLoaiXe"
                :key="loaixe.id"
                :label="loaixe.ten"
                :value="loaixe.id"
            />
          </el-select>
        </div>
        <div class="col-lg-6">
          <div class="input-group">
            <input type="text" @keypress.enter="getListXe" placeholder="Nhập tên xe..." class="form-control" v-model="keySearch" aria-label="Dollar amount (with dot and two decimal places)">
              <v-icon icon="mdi:mdi-magnify" @click="getListXe" class="align-self-center" style="background: #e9a900; padding: 18px"></v-icon>
          </div>
        </div>
        <div class="col-auto">
<!--          <RouterLink to="/findcar/xe" v-if="user.p">-->
<!--            <button class="btn btn bg-e9a900">Đăng xe</button>-->
<!--          </RouterLink>-->
        </div>
      </div>
    </div>
  </div>
  <div class="container mt-5">
  <div class="container-fluid">
    <div class="row">
      <div class="col-9">
        <section v-if="listXe.length > 0" style="box-shadow: rgba(0, 0, 0, 0.25) 0px 2px 16px; padding: 50px;">
          <div class="container py-5">
            <div class="row justify-content-center mb-3" v-for="xe in listXe" :key="xe.id">
              <div class="col-md-12 col-xl-12">
                <div class="card shadow-0 border rounded-3">
                  <div class="card-body p-2">
                    <div class="row">
                      <div class="col-md-12 col-lg-3 col-xl-3 mb-4 mb-lg-0">
                        <div class="bg-image hover-zoom ripple rounded ripple-surface">
                          <img :src="getUrlImage(xe.anhNen)"
                               class="w-100" style="max-height: 150px" />
                          <a href="#!">
                            <div class="hover-overlay">
                              <div class="mask" style="background-color: rgba(253, 253, 253, 0.15);"></div>
                            </div>
                          </a>
                        </div>
                      </div>
                      <div class="col-md-6 col-lg-6 col-xl-6">
                        <h5 class="text-black">{{ xe.tenXe }}</h5>
                        <div class="mb-2 text-muted small">
                          <span v-for="tinhNang in xe.tinhNangs" :key="tinhNang.id" class="mr-5 px-3 d-inline-block"><v-icon icon="mdi:mdi-check" /> {{ tinhNang.ten }}</span>
                        </div>
                        <p class="text-truncate mb-4 mb-md-0 text-black">
                          {{ xe.mota }}
                        </p>
                      </div>
                      <div class="col-md-6 col-lg-3 col-xl-3 border-sm-start-none border-start">
                        <div class="d-flex flex-row align-items-center mb-1">
                          <h4 class="mb-1 me-1 text-danger">{{ formatCurrency(xe.giaXe) }}</h4>
                        </div>
                        <!--                    <h6 class="text-success">Free shipping</h6>-->
                        <div class="d-flex flex-column mt-4">
                          <button class="btn btn-outline-warning btn-sm" @click="xemChiTiet(xe.id)" type="button">Xem chi tiết</button>
                          <!--                      <button class="btn btn-outline-primary btn-sm mt-2" type="button">-->
                          <!--                        Add to wishlist-->
                          <!--                      </button>-->
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

          </div>
        </section>
        <div v-else class="row justify-content-center">
          <img src="../../assets/images/noresult.png" style="height: 400px; width: 400px;"/>
          <h2 class="text-center">không có kết quả tìm kiếm</h2>
        </div>
        <div class="row justify-content-center">
          <v-pagination
              v-if="listXe.length > 0"
              variant="flat"
              active-color="warning"
              color="#ffffff"
              v-model="page"
              class="my-4"
              :length="totalPages"
              :total-visible="5"
              @update:modelValue="getListXe"
          ></v-pagination>
        </div>
      </div>
      <div class="col-3">
        <div style="box-shadow: rgba(0, 0, 0, 0.25) 0px 2px 16px;">
          <v-card
              class="mx-auto"
              max-width="400"
          >
            <v-toolbar
                flat
                color="#f9c63f"
                dark
            >
              <v-btn icon>
              </v-btn>
              <v-toolbar-title>Tìm kiếm theo</v-toolbar-title>
            </v-toolbar>
            <v-card-text>
              <h2 class="text-h6 mb-2">
                Tìm kiếm tất cả
              </h2>

              <v-chip-group
                  v-model="findAll"
                  column
              >
                <v-chip
                    filter
                    value="true"
                    variant="outlined"
                    selected-class="text-success"
                >
                  Tìm kiếm theo tên xe
                </v-chip>
              </v-chip-group>
            </v-card-text>

            <v-card-text>
              <h2 class="text-h6 mb-2">
                Hãng xe
              </h2>

              <v-chip-group
                  v-model="hangXe"
                  column
              >
                <v-chip
                    filter
                    variant="outlined"
                    selected-class="text-success"
                    :value="hangxe.id"
                    v-for="hangxe in listHangXe"
                    :key="hangxe.id"
                >
                  {{ hangxe.ten }}
                </v-chip>
              </v-chip-group>
            </v-card-text>

            <v-card-text>
              <h2 class="text-h6 mb-2">
                Quận/huyện
              </h2>

              <v-chip-group
                  v-model="quanHuyen"
                  column
              >
                <v-chip
                    filter
                    variant="outlined"
                    :value="quanhuyen.id"
                    v-for="quanhuyen in listQuanHuyen"
                    :key="quanhuyen.id"
                    selected-class="text-success"
                >
                  {{ quanhuyen.ten }}
                </v-chip>
              </v-chip-group>
            </v-card-text>

          </v-card>
        </div>

      </div>
    </div>

  </div>


  </div>
</template>

<script>
import ImageService from "@/services/image.service"
import XeService from "@/services/xe.service"
import DanhMucService from "@/services/danhmuc.service"

export default {
  name: "DanhSachXe",
  data() {
    return {
      neighborhoods: [1],
      listLoaiXe: [],
      loaiXe: '',
      quanid: '',
      listXe: [],
      page: 1,
      page_size: 5,
      totalPages: '',
      keySearch: '',
      listHangXe: [],
      hangXe: '',
      findAll: false,
      listQuanHuyen: [],
      quanHuyen: '',
      nhienLieu: '',
    }
  },
  methods: {
    getAllQuanHuyen() {
      DanhMucService.getAll('quanhuyen').then(response => (
          this.listQuanHuyen = response.data
      ))
    },
    getAllLoaiXe() {
      var params = {};
      params['search'] = this.keySearch;
      DanhMucService.getAll('loaixe', params).then(response => (
          this.listLoaiXe = response.data
      ))
    },
    getAllHangXe() {
      var params = {};
      params['search'] = this.keySearch;
      DanhMucService.getAll('hangxe', params).then(response => (
          this.listHangXe = response.data
      ))
    },
    formatCurrency(money) {
      money = money.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
      return money
    },
    xemChiTiet(id) {
      this.$router.push({ name: 'ChiTietXe', params: { id: id } });
      window.scrollTo(0, 0);
    },
    getListXe() {
      let params = {}
      params['page'] = this.page
      params['limit'] = this.page_size
      params['trangThai'] = true
      params['quanid'] = this.quanid
      params['loaixeid'] = this.loaiXe
      params['hangxeid'] = this.hangXe
      params['search'] = this.keySearch
      console.log('parem', params)
      XeService.getAll(params).then(response => (
          this.listXe = response.data.content,
          this.page_size = response.data.size,
          this.totalPages = response.data.totalPages
      ))
    },
    getUrlImage(name) {
      return ImageService.getImage(name);
    },
  },
  created() {
    var id = this.$route.params.quanid;
    this.quanid = id;
    this.getListXe();
    this.getAllLoaiXe();
    this.getAllHangXe();
    this.getAllQuanHuyen();
  },
  watch: {
    loaiXe() {
      this.getListXe();
    },
    hangXe() {
      this.getListXe();
    },
    findAll() {
      this.$router.push({name: 'DanhSachXe'});
      window.location.href = 'http://localhost:8080/danhsachxe'
    },
    quanHuyen() {
      this.quanid = this.quanHuyen;
      this.getListXe();
    }
  }
}
</script>

<style>
.el-input.el-input--suffix {
  height: 37px;
}
.v-input__details {
  display: none !important;
}
</style>