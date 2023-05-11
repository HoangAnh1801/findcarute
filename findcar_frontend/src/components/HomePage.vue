<template>
  <div class="container">
    <nav class="navbar navbar-expand-lg navbar-light">
      <div class="container">
        <a class="navbar-brand" href="#">Findcar</a>
        <button
            class="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="#">Trang chủ</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Link</a>
            </li>
            <li class="nav-item dropdown">
              <a
                  class="nav-link dropdown-toggle"
                  href="#"
                  id="navbarDropdown"
                  role="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
              >
                Loại xe
              </a>
              <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item" href="#">Action</a></li>
                <li><a class="dropdown-item" href="#">Another action</a></li>
                <li><hr class="dropdown-divider" /></li>
                <li>
                  <a class="dropdown-item" href="#">Something else here</a>
                </li>
              </ul>
            </li>
            <li class="nav-item dropdown">
              <a
                  class="nav-link dropdown-toggle"
                  href="#"
                  id="navbarDropdown"
                  role="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
              >
                Hãng xe
              </a>
              <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item" href="#">Action</a></li>
                <li><a class="dropdown-item" href="#">Another action</a></li>
                <li><hr class="dropdown-divider" /></li>
                <li>
                  <a class="dropdown-item" href="#">Something else here</a>
                </li>
              </ul>
            </li>
            <li class="nav-item">
              <a
                  class="nav-link disabled"
                  href="#"
                  tabindex="-1"
                  aria-disabled="true"
              >Disabled</a
              >
            </li>
          </ul>

          <span class="navbar-text"> <a class="login" href="#">Log In</a></span><a class="btn btn-light action-button" role="button" href="#">Signup</a>
        </div>
      </div>
    </nav>
  </div>
    <div class="header-blue">
      <div class="container hero">
        <div class="row">
          <div class="col-12 col-lg-6 col-xl-5 offset-xl-1">
            <h1 class="txt-name pr-3" data-text="GIỚI THIỆU FINDCAR">GIỚI THIỆU FINDCAR</h1>
            <p>
              Chúng tôi chuyên cho thuê xe ô tô tại Đà Nẵng, Cho thuê xe các loại 4 chỗ, 7 chỗ. Quý khách lưu ý, xe thuê theo tháng/năm sẽ có giá khác ưu đãi hơn.
            </p>
            <p>Các loại xe đời mới, cao cấp và chất lượng với giá rẻ, quý khách hoàn toàn có thể yên tâm với dịch vụ cho thuê xe du lịch, xe tự lái tại Đà Nẵng.</p>
            <button class="btn btn-light btn-lg action-button" type="button">
              Khám phá ngay<i class="fa fa-long-arrow-right ml-2"></i>
            </button>

          </div>
          <div class="col-md-5 col-lg-5 offset-lg-1 offset-xl-0 d-none d-md-block phone-holder">
            <div class="iphone-mockup">
              <img class="device" src="https://vinfasttayninh3s.com/wp-content/uploads/2020/11/vinfast-presedent.png" />
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
                <template #content> {{ hangXe.name }} </template>
                <img :src="getUrlImage(hangXe.urlImage)" alt="" class="company" />
              </el-tooltip>
            </div>
          </div>
          <div class="items-container roll-RL">
            <div class="item" v-for="hangXe in listHangXe" :key="hangXe.id">
              <el-tooltip placement="top">
                <template #content> {{ hangXe.name }} </template>
                <img :src="getUrlImage(hangXe.urlImage)" alt="" class="company" />
              </el-tooltip>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="container-fluid">
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
              v-for="n in 6"
              :key="n"
              v-slot="{ isSelected, toggle, selectedClass }"
          >
            <v-card
                color="grey-lighten-1"
                :class="['ma-4', selectedClass]"
                height="300"
                width="200"
                @click="toggle"
            >
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

  <div class="container">
    <h3 class="text-center">CUNG CẤP TẤT CẢ CÁC LOẠI XE HIỆN CÓ</h3>
    <div class="row row-cols-1 row-cols-md-3 g-4">
      <div class="col-12 col-lg-3">
        <div class="card h-100">
          <img src="../assets/images/lai_xe_duong_deo_1.jpeg" class="card-img-top" alt="...">
          <div class="card-body">
            <h5 class="card-title text-center">Mazda CX5</h5>
            <div class="card-text">
              <span class=""><v-icon icon="mdi:mdi-check color-primary"/></span>
              <span class="pl-2">Giá cả hợp lý</span>
            </div>
          </div>
          <div class="card-footer bg-white border-0">
            <button class="col-6 btn btn-outline-success">Thuê ngay</button>
          </div>
        </div>
      </div>
      <div class="col-12 col-lg-3">
        <div class="card h-100">
          <img src="../assets/images/lai_xe_duong_deo_1.jpeg" class="card-img-top" alt="...">
          <div class="card-body">
            <h5 class="card-title">Card title</h5>
            <p class="card-text">This is a short card.</p>
          </div>
        </div>
      </div>
      <div class="col-12 col-lg-3">
        <div class="card h-100">
          <img src="../assets/images/lai_xe_duong_deo_1.jpeg" class="card-img-top" alt="...">
          <div class="card-body">
            <h5 class="card-title">Card title</h5>
            <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content.</p>
          </div>
        </div>
      </div>
      <div class="col-12 col-lg-3">
        <div class="card h-100">
          <img src="../assets/images/lai_xe_duong_deo_1.jpeg" class="card-img-top" alt="...">
          <div class="card-body">
            <h5 class="card-title">Card title</h5>
            <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="container">
    <h3 class="text-center">TIN TỨC</h3>
    <div class="row justify-content-center">
      <div style="max-width: 1200px">
        <v-slide-group
            v-model="model"
            class="pa-4"
            selected-class="bg-success"
            show-arrows
        >
          <v-slide-group-item>
            <v-card
                class="mx-auto me-3"
                max-width="300"
            >
              <v-img
                  class="align-end text-white"
                  height="200"
                  src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
                  cover
              >
                <v-card-title>Top 10 Australian beaches Top 10 Australian beaches Top 10 Australian beaches</v-card-title>
              </v-img>

              <v-card-subtitle class="pt-4">
                Number 10
              </v-card-subtitle>

              <v-card-text>
                <div>Whitehaven Beach</div>

                <div>Whitsunday Island, Whitsunday Islands</div>
              </v-card-text>

              <v-card-actions>
                <v-btn color="orange">
                  Xem
                </v-btn>
              </v-card-actions>
            </v-card>
            <v-card
                class="mx-auto me-3"
                max-width="400"
            >
              <v-img
                  class="align-end text-white"
                  height="200"
                  src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
                  cover
              >
                <v-card-title>Top 10 Australian beaches</v-card-title>
              </v-img>

              <v-card-subtitle class="pt-4">
                Number 10
              </v-card-subtitle>

              <v-card-text>
                <div>Whitehaven Beach</div>

                <div>Whitsunday Island, Whitsunday Islands</div>
              </v-card-text>

              <v-card-actions>
                <v-btn color="orange">
                  Share
                </v-btn>
              </v-card-actions>
            </v-card>
            <v-card
                class="mx-auto me-3"
                max-width="400"
            >
              <v-img
                  class="align-end text-white"
                  height="200"
                  src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
                  cover
              >
                <v-card-title>Top 10 Australian beaches</v-card-title>
              </v-img>

              <v-card-subtitle class="pt-4">
                Number 10
              </v-card-subtitle>

              <v-card-text>
                <div>Whitehaven Beach</div>

                <div>Whitsunday Island, Whitsunday Islands</div>
              </v-card-text>

              <v-card-actions>
                <v-btn color="orange">
                  Share
                </v-btn>
              </v-card-actions>
            </v-card>
            <v-card
                class="mx-auto me-3"
                max-width="400"
            >
              <v-img
                  class="align-end text-white"
                  height="200"
                  src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
                  cover
              >
                <v-card-title>Top 10 Australian beaches</v-card-title>
              </v-img>

              <v-card-subtitle class="pt-4">
                Number 10
              </v-card-subtitle>

              <v-card-text>
                <div>Whitehaven Beach</div>

                <div>Whitsunday Island, Whitsunday Islands</div>
              </v-card-text>

              <v-card-actions>
                <v-btn color="orange">
                  Share
                </v-btn>
              </v-card-actions>
            </v-card>
            <v-card
                class="mx-auto me-3"
                max-width="400"
            >
              <v-img
                  class="align-end text-white"
                  height="200"
                  src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
                  cover
              >
                <v-card-title>Top 10 Australian beaches</v-card-title>
              </v-img>

              <v-card-subtitle class="pt-4">
                Number 10
              </v-card-subtitle>

              <v-card-text>
                <div>Whitehaven Beach</div>

                <div>Whitsunday Island, Whitsunday Islands</div>
              </v-card-text>

              <v-card-actions>
                <v-btn color="orange">
                  Share
                </v-btn>
              </v-card-actions>
            </v-card>
            <v-card
                class="mx-auto me-3"
                max-width="400"
            >
              <v-img
                  class="align-end text-white"
                  height="200"
                  src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
                  cover
              >
                <v-card-title>Top 10 Australian beaches</v-card-title>
              </v-img>

              <v-card-subtitle class="pt-4">
                Number 10
              </v-card-subtitle>

              <v-card-text>
                <div>Whitehaven Beach</div>

                <div>Whitsunday Island, Whitsunday Islands</div>
              </v-card-text>

              <v-card-actions>
                <v-btn color="orange">
                  Share
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-slide-group-item>
        </v-slide-group>
      </div>
    </div>
  </div>
  <Footer />
</template>

<script>
import Footer from "@/components/Footer";
import DanhMucService from "@/services/danhmuc.service"
import ImageService from "@/services/image.service"

export default {
  name: "header",
  components: {Footer},
  data() {
    return {
      listHangXe: []
    }
  },
  methods: {
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
  }
};
</script>
<style scoped>
@import "../assets/css/Header.css";

@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap');

/* Reseting */

/*.content{*/
/*    width: 100vw;*/
/*     min-height: 100vh;*/
/*}*/

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