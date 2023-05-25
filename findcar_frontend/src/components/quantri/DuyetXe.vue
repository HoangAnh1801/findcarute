<template>
  <div class="container px-5" style="margin-top: 50px; padding: 0px 50px">
    <div class="col-12">
      <div class="image-car">
        <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
          <div class="carousel-inner">
            <div class="carousel-item active">
              <inner-image-zoom
                  class="img-fluid"
                  style="height: 500px"
                  :src="getUrlImage(xe.anhNen)"
                  :zoomSrc="getUrlImage(xe.anhNen)"
                  moveType="drag"
              />
            </div>
            <div class="carousel-item" v-for="image in xe.xeImages" :key="image.id">
              <inner-image-zoom
                  class="img-fluid"
                  style="height: 500px"
                  :src="getUrlImage(image.urlImage)"
                  :zoomSrc="getUrlImage(image.urlImage)"
                  moveType="drag"
              />
            </div>
          </div>
          <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
          </button>
          <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
          </button>
        </div>
      </div>
      <div>
        <h2>{{ xe.tenXe }}</h2>
      </div>
      <div class="chitietxe">
        <div class="row">
          <div class="col-lg-2">
            ĐẶC ĐIỂM
          </div>
          <div class="col-lg-5">
            <p> Số ghế: 5</p>
            <p> Nhiên liệu: {{ xe.nhienLieu.name }}</p>
          </div>
          <div class="col-lg-5">
            <p>Truyền động: Số tự động</p>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-2">
            MÔ TẢ
          </div>
          <div class="col-lg-10">
            <p>{{ xe.mota }}</p>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-2">
            TÍNH NĂNG
          </div>
          <div class="col-lg-10">
            <div class="">
              <span v-for="tinhNang in xe.tinhNangs" :key="tinhNang.id" class="mr-5 px-3 d-inline-block"><v-icon icon="mdi:mdi-check" /> {{ tinhNang.name }}</span>
            </div>
          </div>
        </div>
      </div>

      <div class="chitietxe mt-3">
        <div class="row">
          <div class="col-lg-2">
            GIẤY TỜ THUÊ XE (BẢN GỐC)
          </div>
          <div class="col-lg-10">
            <p>CMND và GPLX (đối chiếu)</P>
            <p>CCCD gắn chip (đối chiếu)</P>
          </div>
        </div>

        <div class="row">
          <div class="col-lg-2">
            <p>ĐIỀU KHOẢN</p>
          </div>
          <div class="col-lg-10">
            <p>Quy định khác:</P>
            <ul>
              <li><p>Sử dụng xe đúng mục đích.</p></li>
              <li><p>Không sử dụng xe thuê vào mục đích phi pháp, trái pháp luật.</p></li>
              <li><p>Không sử dụng xe thuê để cầm cố, thế chấp.</p></li>
              <li><p>Không hút thuốc, nhả kẹo cao su, xả rác trong xe.</p></li>
              <li><p>Không chở hàng quốc cấm dễ cháy nổ.</p></li>
              <li><p>Không chở hoa quả, thực phẩm nặng mùi trong xe.</p></li>
              <li><p>Khi trả xe, nếu xe bẩn hoặc có mùi trong xe, khách hàng vui lòng vệ sinh xe sạch sẽ hoặc gửi phụ thu phí vệ sinh xe.</p></li>
            </ul>
            <p>Trân trọng cảm ơn, chúc quý khách hàng có những chuyến đi tuyệt vời !
            </p>
          </div>
        </div>
      </div>

      <div class="chitietxe mt-3">
        <div class="row justify-content-center">
          <div class="col-12 rounded-circle d-flex justify-content-center">
            <img src="@/assets/images/vf_1.jpg" style="max-height: 100px; max-width: 100px" >
          </div>
          <p class="text-center">Chủ xe</p>
          <span class="text-center">{{ xe.nguoiDung.hoTen }}</span>
          <p class="text-center">100 chuyến</p>
        </div>
      </div>

      <div class="my-3">
        <div class="row justify-content-center">
          <button @click="duyetXe" class="btn btn-outline-success col-1 mr-2">Duyệt</button>
          <button class="btn btn-outline-danger col-1" @click="back">Quay lại</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import XeService from "@/services/xe.service"
import ImageService from "@/services/image.service"
import InnerImageZoom from 'vue-inner-image-zoom';
import swal from 'sweetalert';

export default {
  components: {
    InnerImageZoom
  },
  data() {
    return {
      xe: '',
      id: '',
      date: ''
    }
  },
  methods: {
    back() {
      this.$router.go(-1)
    },
    duyetXe() {
      XeService.duyetXe(this.id);
      swal('Duyệt thành công', "", "success")
    },
    formatCurrency(money) {
      money = money.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
      return money
    },
    getXeById(id) {
      XeService.findByID(id).then(response => {
        this.xe = response.data
      })
    },
    getUrlImage(url) {
      return ImageService.getImage(url);
    },
  },
  created() {
    var id = this.$route.params.id;
    if (id) {
      this.id = id;
    }
    this.getXeById(id)
  }
}
</script>

<style scoped>
.chitietxe {
  background: #f6f9f9;
  padding: 20px;
}
</style>
<style src="vue-inner-image-zoom/lib/vue-inner-image-zoom.css">

</style>