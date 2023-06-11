<template>
    <div class="header-blue" style="box-shadow: 0px 6px 5px rgba(0, 0, 0, 0.25)">
      <div class="container hero">
        <div class="row">
          <div class="col-12 col-lg-6 col-xl-5 offset-xl-1">
            <h1 class="txt-name pr-3 text-black" data-text="GIỚI THIỆU FINDCAR">GIỚI THIỆU FINDCAR</h1>
            <p class="text-black">
              Chúng tôi chuyên cho thuê xe ô tô tại Đà Nẵng, Cho thuê xe các loại 4 chỗ, 7 chỗ. Quý khách lưu ý, xe thuê theo tháng/năm sẽ có giá khác ưu đãi hơn.
            </p>
            <p class="text-black">Các loại xe đời mới, cao cấp và chất lượng với giá rẻ, quý khách hoàn toàn có thể yên tâm với dịch vụ cho thuê xe du lịch, xe tự lái tại Đà Nẵng.</p>
            <button class="btn btn-outline-secondary" type="button">
              Khám phá ngay<i class="fa fa-long-arrow-right ml-2"></i>
            </button>

          </div>
          <div class="col-md-5 col-lg-5 offset-lg-1 offset-xl-0 d-none d-md-block phone-holder">
            <div class="iphone-mockup">
              <img class="device" src="../assets/images/logo_no_background.png" />
            </div>
          </div>
        </div>
      </div>
    </div>

  <div class="container-fluid">
    <div class="row">
      <div class="col-md-6 chonchungtoi">
        <h2 class="mb-3">Vì sao lại chọn chúng tôi?</h2>
        <ul>
          <li class="">
            <span class=""><v-icon icon="mdi:mdi-check color-primary"/></span>
            <span class="pl-2">Đa dạng các mẫu xe đời mới</span>
          </li>
          <li class="">
            <span class=""><v-icon icon="mdi:mdi-check color-primary"/></span>
            <span class="pl-2">Giá cả hợp lý</span>
          </li>
          <li class="">
            <span class=""><v-icon icon="mdi:mdi-check color-primary"/></span>
            <span class="pl-2">Thủ tục đơn giản, tiết kiệm thời gian</span>
          </li>
          <li class="">
            <span class=""><v-icon icon="mdi:mdi-check color-primary"/></span>
            <span class="pl-2">Nhân viên nhiệt tình chu đáo</span>
          </li>
          <li class="">
            <span class=""><v-icon icon="mdi:mdi-check color-primary"/></span>
            <span class="pl-2">Uy tín, an toàn, chất lượng</span>
          </li>
        </ul>
      </div>
      <div class="col-md-6 px-0 position-relative">
        <!-- <img src="../assets/images/lai_xe_duong_deo_1.jpeg" alt="" class="img-fluid">
        <div class="position-absolute bottom-0">
            <p class="pl-3 text-white">FINDCAR - ĐỒNG HÀNH CÙNG MỌI CHUYẾN ĐI CỦA BẠN</p>
        </div> -->
        <div id="carouselExampleSlidesOnly" class="carousel slide" data-bs-ride="carousel">
          <div class="carousel-inner">
            <div class="carousel-item active">
              <img src="../assets/images/lai_xe_duong_deo_1.jpeg" class="d-block w-100 img-fluid" style="max-height: 450px" alt="...">
              <div class="carousel-caption d-none d-md-block">
                <h4 class="text-white">Findcar</h4>
                <p class="text-white">Đồng hành cùng bạn trên mọi cung đường</p>
              </div>
            </div>
            <div class="carousel-item">
              <img src="../assets/images/carousel-1.jpg" class="d-block w-100 img-fluid" style="max-height: 450px" alt="...">
              <div class="carousel-caption d-none d-md-block">
                <h4 class="text-white">Findcar</h4>
                <p class="text-white">Giá rẻ, dịch vụ tốt, tiết kiệm thời gian và chi phí</p>
              </div>
            </div>
            <div class="carousel-item">
              <img src="../assets/images/carousel-2.png" class="d-block w-100 img-fluid" style="max-height: 450px" alt="...">
              <div class="carousel-caption d-none d-md-block">
                <h4 class="text-white">Findcar</h4>
                <p class="text-white">Cam kết xe chất lượng</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="container-fluid content align-items-center justify-content-center mt-5">
    <div class="container rounded">
      <div class="h1 fw-bold text-center mt-2 color-primary">ĐA DẠNG CÁC HÃNG XE</div>
      <div class="h3 text-center pt-2">Chúng tôi có đầy đủ các phân khúc xe từ bình dân đến cao cấp cho khách hàng lựa chọn</div>
      <div class="rollers position-relative overflow-hidden">
        <div class="start-roller"></div>
        <div class="wrapper">
          <div class="items-container roll-LL">
            <div class="item" v-for="hangXe in listHangXe" :key="hangXe.id">
              <el-tooltip placement="top">
                <template #content> {{ hangXe.ten }} </template>
                <img :src="getUrlImage(hangXe.urlAnh)" alt="" class="company" />
              </el-tooltip>
            </div>
          </div>
          <div class="items-container roll-RL">
            <div class="item" v-for="hangXe in listHangXe" :key="hangXe.id">
              <el-tooltip placement="top">
                <template #content> {{ hangXe.ten }} </template>
                <img :src="getUrlImage(hangXe.urlAnh)" alt="" class="company" />
              </el-tooltip>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="container-fluid mt-5">
    <h3 class="text-center">XE TẠI CÁC QUẬN</h3>
    <div class="row justify-content-center">
      <div style="max-width: 1200px">
        <v-slide-group
            v-model="model"
            class="pa-4"
            selected-class="bg-success"
            show-arrows
        >
          <v-slide-group-item
              v-for="n in listQuanHuyen"
              :key="n"
              v-slot="{ isSelected, toggle, selectedClass }"
          >
            <v-card
                :class="['ma-4', selectedClass]"
                height="200"
                width="300"
                @click="toggle"
            >
            <div class="rounded-circle" @click="getListXeByQuanId(n.id)">
              <img :src="getUrlImage(n.urlAnh)" class="img-fluid" style="height: 170px; width: 300px">
              <span class="title-quan text-white d-block pl-2">{{ n.ten }}</span>
            </div>
              <div class="d-flex fill-height align-center justify-center">
                <v-scale-transition>
                  <v-icon
                      v-if="isSelected"
                      color="white"
                      size="48"
                      icon="mdi-close-circle-outline"
                  ></v-icon>
                </v-scale-transition>
              </div>
            </v-card>
          </v-slide-group-item>
        </v-slide-group>
      </div>
    </div>
  </div>

  <div class="container" style="margin-top: 50px">
    <h3 class="text-center" style="margin-bottom: 30px">CUNG CẤP TẤT CẢ CÁC LOẠI XE HIỆN CÓ</h3>
    <div class="row row-cols-1 row-cols-md-3 g-4">
      <div class="col-12 col-lg-3" v-for="xe in listXe" :key="xe.id">
        <div class="card h-100 custom-card">
          <img :src="getUrlImage(xe.anhNen)" class="card-img-top" alt="..." style="height: 190px">
          <div class="card-body">
            <h5 class="card-title">{{ xe.tenXe }}</h5>
            <div class="card-text">
              <span class=""><v-icon icon="mdi:mdi-check color-f9c63f"/></span>
              <span class="pl-2">Số ghế: {{ xe.soGhe }}</span>
              <div>
                <span class=""><v-icon icon="mdi:mdi-check color-f9c63f"/></span>
                <span class="pl-2">Năm sản xuất: {{ xe.namSX }}</span>
              </div>
              <div>
                <span class=""><v-icon icon="mdi:mdi-check color-f9c63f"/></span>
                <span class="pl-2">Nhiên liệu: {{ xe.nhienLieu.ten }}</span>
              </div>
              <div class="mt-3">
                <h5 class="pl-2 text-danger">{{ formatCurrency(xe.giaXe) }}</h5>
              </div>
            </div>
          </div>
          <div class="card-footer bg-white border-0">
            <button class="col-6 btn btn-warning col-12" @click="xemChiTiet(xe.id)">Xem chi tiết</button>
          </div>
        </div>
      </div>
    </div>
    <div class="row justify-content-center" style="margin-top: 30px">
      <RouterLink to="danhsachxe" @click="handleRouterLinkClick" class="text-center">
        <button class="btn btn-outline-warning ">Xem tất cả</button>
      </RouterLink>
    </div>
  </div>

</template>

<script>
import DanhMucService from "@/services/danhmuc.service"
import ImageService from "@/services/image.service"
import XeService from "@/services/xe.service"
import {functionMixins} from "@/mixin/functionMixins";

export default {
  name: "header",
  data() {
    return {
      listHangXe: [],
      listQuanHuyen: [],
      page: 1,
      page_size: 8,
      listXe: []
    }
  },
  mixins: [functionMixins],
  methods: {
    handleRouterLinkClick() {
      window.scrollTo(0, 0);
    },
    getListXeByQuanId(id) {
      this.$router.push({ name: 'DanhSachXeQuan', params: { quanid: id } });
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
      params['search'] = this.keySearch
      XeService.getAll(params).then(response => (
          this.listXe = response.data.content
      ))
    },
    getAllQuanHuyen() {
      var params = {};
      params['search'] = '';
      DanhMucService.getAll('quanhuyen', params).then(response => (
          this.listQuanHuyen = response.data
      ))
    },
    getAllHangXe() {
      DanhMucService.getAll('hangxe').then(response => (
          this.listHangXe = response.data
      ))
    },
    getUrlImage(name) {
      return ImageService.getImage(name);
    },
  },
  created() {
    this.getAllHangXe();
    this.getAllQuanHuyen();
    this.getListXe();
  }
};
</script>
<style scoped>
@import "../assets/css/Header.css";

@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap');

.content .container{
    padding-top: 30px;
    padding-bottom: 20px;
    box-shadow: 2px 15px 20px #1f1f1f10,-2px -8px 20px #1f1f1f1a;
}


.container .rollers{
    flex: 0 0 230px;
}

.container .start-roller{
    left: 0;
    top: 0;
    background-image: linear-gradient(to right, white 30%, #ffffff00);
}

.container .start-roller,
.container .end-roller{
    height: 100%;
    width: 40px;
    position: absolute;
    z-index: 1;
}

.container .end-roller{
    right: 0;
    top: 0;
    background-image: linear-gradient(to left, white 30%, rgba(255, 255, 255, 0));
}

.container .rollers .wrapper{
    position: relative;
    width: 1400px;
    height: 100px;
    margin: 0 0 20px 0;
    flex: 0 0 auto;
}

.container .rollers .wrapper .items-container{
    display: flex;
    align-items: center;
    position: absolute;
    width: 100%;
    height: 100%;
    animation-duration: 30s;
    animation-iteration-count: infinite;
    animation-timing-function: linear;
}


.container .rollers .wrapper .items-container.roll-LL{
    animation-name: roll;
}
.container .rollers .wrapper .items-container.roll-RL{
    animation-name: roll-reverse;
    left: 100%;
}
.container .rollers .wrapper .items-container.reverse-roll-LL{
    animation-name: roll-clockwise;
    left: -100%;
}
.container .rollers .wrapper .items-container.reverse-roll-RL{
    animation-name: roll-reverse-clockwise;
    left: 100%;
}

@keyframes roll {
    0%{
        left: 0%;
    }
    100%{
        left: -100%;
    }
}
@keyframes roll-reverse {
    0%{
        left: 100%;
    }
    100%{
        left: 0%;
    }
}
@keyframes roll-clockwise {
    0%{
        left: -100%;
    }
    100%{
        left: 0%;
    }
}
@keyframes roll-reverse-clockwise {
    0%{
        left: 0%;
    }
    100%{
        left: 100%;
    }
}


.container .rollers .wrapper .items-container .item{
    flex: 1 1 200px;
    min-width: 0;
    margin: 20px;
}

.container .rollers .wrapper .items-container .item .company{
    /*filter: grayscale(100%);*/
    /*opacity: 0.2;*/
    width: 100%;
    object-fit: contain;
}

.container .rollers .wrapper .items-container .item .company:hover{
    filter: grayscale(0%);
    opacity: 1;
    cursor: pointer;
}

.container img{
    vertical-align: middle;
}

@media (max-width: 575.5px) {
    .content{
        padding: 20px;
    }
    .content .container{
        padding: 20px;
    }
    .h3{
        font-size: calc(1.1rem + .6vw);
    }
    .fs-5 {
        font-size: 0.9rem!important;
    }
    .container .rollers .wrapper .items-container .item .company{
        opacity: 0.4;
    }
}




</style>