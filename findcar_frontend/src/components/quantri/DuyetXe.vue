<template>
  <div class="container px-5" style="margin-top: 50px; padding: 0px 50px">
    <div class="col-12">
      <div class="image-car" style="height: 500px; margin: 0px 70px">
        <el-carousel :interval="5000" arrow="always" >
          <el-carousel-item
              style="height: 500px"
              v-for="image in xe.xeAnhs"
              :key="image.id">
            <inner-image-zoom
                class="img-fluid"
                :src="getUrlImage(image.urlAnh)"
                :zoomSrc="getUrlImage(image.urlAnh)"
                moveType="drag"
            />
          </el-carousel-item>
        </el-carousel>
      </div>
      <div style="margin: 0px 70px">
        <h2>{{ xe.tenXe }}</h2>
      </div>
      <div class="chitietxe">
        <div class="row">
          <div class="col-lg-2">
            ĐẶC ĐIỂM
          </div>
          <div class="col-lg-5">
            <p> Số ghế: 5</p>
            <p> Nhiên liệu: {{ xe.nhienLieu.ten }}</p>
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
              <span v-for="tinhNang in xe.tinhNangs" :key="tinhNang.id" class="mr-5 px-3 d-inline-block"><v-icon icon="mdi:mdi-check" /> {{ tinhNang.ten }}</span>
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
            <img src="@/assets/images/avt.jpg" class="rounded-circle" style="max-height: 100px; max-width: 100px" >
          </div>
          <p class="text-center">Chủ xe</p>
          <span class="text-center">{{ xe.nguoiDung.hoTen }}</span>
          <p class="text-center">100 chuyến</p>
        </div>
      </div>

      <div class="my-3">
        <div class="row justify-content-center">
          <button @click="duyetXe" class="btn btn-outline-success col-1 mr-2">Ẩn</button>
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
      XeService.huyDuyetXe(this.id);
      swal('Đã ẩn bài đăng', "", "success")
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
  margin: 0px 70px;
  padding: 20px;
}
.v-card.v-card--link.v-theme--light.v-card--density-default.v-card--variant-elevated.ma-4 {
  width: 1000px !important;
}
.v-slide-group__container {
  height: 800px !important;
}
</style>
<style src="vue-inner-image-zoom/lib/vue-inner-image-zoom.css">

</style>