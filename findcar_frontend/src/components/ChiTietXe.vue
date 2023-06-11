<template>
  <div class="container mt-5">
    <div class="row">
      <div class="col-lg-8">
        <div class="image-car" style="height: 500px; width: 100%">
          <el-carousel :interval="5000" arrow="always" >
            <el-carousel-item
                style="height: 500px"
                v-for="image in xe.xeAnhs"
                :key="image.id">
              <inner-image-zoom
                  class="img-fluid"
                  style="width: 100%"
                  :src="getUrlImage(image.urlAnh)"
                  :zoomSrc="getUrlImage(image.urlAnh)"
                  moveType="drag"
              />
            </el-carousel-item>
          </el-carousel>
        </div>
        <div>
          <h1>{{ xe.tenXe }}</h1>
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
              <p>Loại xe: {{ xe.loaiXe.ten }}</p>
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
              <img src="../assets/images/avt.jpg" class="rounded-circle" style="max-height: 100px; max-width: 100px" >
            </div>
            <p class="text-center">Chủ xe</p>
            <span class="text-center">{{ xe.nguoiDung.hoTen }}</span>
            <p class="text-center">100 chuyến</p>
          </div>
        </div>
      </div>
      <div class="col-lg-4">
        <div>
          <h3 class="text-center mb-5">Đặt xe</h3>
          <Form @submit="thue" ref="form" lazy-validation>
          <div class="row mt-3">
              <div class="col-lg-6">
                <label class="demonstration col-12">Ngày nhận xe<span class="text-danger">*</span></label>
                <Field name="ngayBatDau" rules="required" v-slot="{field}" :value="thueXes.ngayBatDau">
                  <el-date-picker
                      v-bind="field"
                      v-model="thueXes.ngayBatDau"
                      class="w-100"
                      type="date"
                      placeholder="Chọn ngày nhận xe"
                      format="DD/MM/YYYY"
                      :disabled-date="disdabledate1"
                      :shortcuts="shortcuts"
                      :size="size"
                      :clearable="false"
                  />
                </Field>
                <ErrorMessage name="ngayBatDau" class="text-danger"/>
              </div>
              <div class="col-lg-6">
                <label class="demonstration col-12">Ngày trả xe<span class="text-danger">*</span></label>
                <Field name="ngayKetThuc" rules="required" v-slot="{field}" :value="thueXes.ngayKetThuc">
                  <el-date-picker
                      v-model="thueXes.ngayKetThuc"
                      @change="dateDiff"
                      class="w-100"
                      type="date"
                      placeholder="Chọn ngày trả xe"
                      format="DD/MM/YYYY"
                      :disabled-date="disableDateEnd"
                      :shortcuts="shortcuts"
                      :size="size"
                      v-bind="field"
                      :clearable="false"
                      :disabled="thueXes.ngayBatDau == ''"
                  />
                </Field>
                <ErrorMessage name="ngayKetThuc" class="text-danger"/>
              </div>

          </div>
          <div class="row mx-auto mt-4">
              <label class="form-label pl-0">Địa điểm giao nhận xe<span class="text-danger">*</span></label>
            <Field name="diaChiGiaoXe" rules="required" v-slot="{field}" :value="thueXes.diaChiGiaoXe">
              <textarea type="text" class="form-control" v-bind="field" v-model="thueXes.diaChiGiaoXe" />
            </Field>
            <ErrorMessage name="diaChiGiaoXe" class="text-danger"/>
          </div>

          <div class="row mx-auto mt-5">
              <label class="form-label pl-0">Chi tiết giá</label>
              <div>
                <span>Đơn giá thuê</span>
                <span class="float-right">{{ formatCurrency(xe.giaXe) }} / ngày</span>
              </div>
            <hr class="mt-1" />
            <div>
              <span>Tổng cộng</span>
              <span class="float-right">{{ formatCurrency(tongPhi) }}</span>
            </div>
          </div>

          <button class="btn btn-warning mr-2 font-weight-bold mt-3 col-12" :disabled="user.permissions[0] === 'chuxe'" > Đặt xe </button>
          </Form>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import XeService from "@/services/xe.service"
import ImageService from "@/services/image.service"
import swal from 'sweetalert';
import InnerImageZoom from 'vue-inner-image-zoom';
import {functionMixins} from "@/mixin/functionMixins";
import moment from 'moment';
import {Field, Form, ErrorMessage } from "vee-validate";

export default {
  name: "ChiTietXe",
  components: {
    InnerImageZoom,
    Form, Field, ErrorMessage
  },
  mixins: [
    functionMixins,
  ],
  data() {
    return {
      selectedDate: null,
      pickerOptions: {
      },

      xe: '',
      thueXes: {
        id: '',
        diaChiGiaoXe: '',
        ngayBatDau: '',
        ngayKetThuc: '',
        nguoiDung: {
          id: ''
        },
        xe: {
          id: ''
        },
        // trangThaiHoanXe: false
      },
      datediff: '',
      date: '',
      logginIn: false,
      tongPhi: '',
      thueXe: [],
      formattedDate: '',
      disabledRangesData: [],
      user: ''
    }
  },
  methods: {
    tongTien() {
        this.tongPhi = this.xe.giaXe*(this.datediff + 1)
    },
    disdabledate1(time) {
      const dataDisable = this.disabledRangesData

      for (const range of dataDisable) {
        if (
            time >= range.ngayBatDau.getTime() &&
            time <= range.ngayKetThuc.getTime()
        ) {
          return true;
        }
      }
      let dateStart = new Date()
      return false || time.getTime() < dateStart
    },
    disableDateEnd(time) {
      const dataDisable = this.disabledRangesData

      for (const range of dataDisable) {
        if (
            time >= range.ngayBatDau.getTime() &&
            time <= range.ngayKetThuc.getTime()
        ) {
          return true;
        }
      }
      let dateStart = new Date()
      if (this.thueXes.ngayBatDau != null) {
        var ngayBD = this.thueXes.ngayBatDau;
      }
      return false || time.getTime() < dateStart || time.getTime() < ngayBD
    },
    disabledDateBeforeDateStart(time) {
      let dateStart = new Date()
      return  time.getTime() < dateStart
    },
    dateDiff() {
      var date1 = this.thueXes.ngayBatDau
      var date2 = this.thueXes.ngayKetThuc

      if (date1 != null && date2 != null) {
        var Difference_In_Time = date2 - date1;
        var Difference_In_Days = Difference_In_Time / (1000 * 3600 * 24)
        this.datediff = Difference_In_Days
      }
      console.log('datediff', this.datediff)
    },
    thue() {
      if (!this.logginIn) {
        swal({title: "Vui lòng đăng nhập để đặt xe!",
          icon: "warning",
          buttons: true,
          dangerMode: true,}).then(
            (willDelete) => {
              if (willDelete) {
                this.$router.push('/fincar/login')
              }
              })
      } else {
        console.log('thuexe', this.thueXes.ngayBatDau)
      // var date = this.thueXes.ngayBatDau.toJSON().slice(0,10).split('-').join('-')
      var date = moment(this.thueXes.ngayBatDau).utcOffset(7).format('YYYY-MM-DD');
      var date2 = moment(this.thueXes.ngayKetThuc).utcOffset(7).format('YYYY-MM-DD');
      console.log('ngaybatdau', date)
      this.thueXes.ngayBatDau = date
      this.thueXes.ngayKetThuc = date2
        console.log('thuexe', this.thueXes)
      XeService.saveThueXe(this.thueXes).then(
          swal('Đã gửi yêu cầu thuê xe', '', 'success').then(
          window.location.href = 'http://localhost:8080/findcar/danhsachxethue')
      )
      }
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
    getUrlImage(name) {
      return ImageService.getImage(name);
    },
    getThueXeByXeId(id) {
      XeService.findThueXeByXeId(id).then(response => {
        var result = []
        var data = response.data
        data.forEach(function (thue) {
          var item = {}
          item.ngayBatDau = new Date(moment((thue).ngayBatDau).format('YYYY-MM-DD '));
          item.ngayKetThuc = new Date(moment((thue).ngayKetThuc).format('YYYY-MM-DD '));
          result.push(item)
        })
          this.disabledRangesData = result
        this.thueXe = response.data[0]
      })
    }
  },
  created() {
    var user = JSON.parse(localStorage.getItem('user'));
    if (user) {
      this.thueXes.nguoiDung.id = user.id;
      this.user = user
      this.logginIn = true
    }

    var id = this.$route.params.id;
    this.thueXes.xe.id = id;
    this.getXeById(id);
    this.getThueXeByXeId(id);
  },
  watch: {
    ngayKetThuc() {
      this.datediff();
    },
    datediff() {
      if (this.datediff >= 0) {
        this.tongTien();
      }
    }
  }
}
</script>

<style scoped>
  .chitietxe {
    background: #f6f9f9;
    padding: 20px;
  }
  img.iiz__img {
    height: 500px !important;
    width: 700px !important;
  }
  .v-card.v-card--link.v-theme--light.v-card--density-default.v-card--variant-elevated.ma-4 {
      width: 700px !important;
  }
  .v-slide-group__container {
      height: 500px !important;
  }



  .el-carousel__item h3 {
    color: #475669;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
    text-align: center;
  }

</style>
<style src="vue-inner-image-zoom/lib/vue-inner-image-zoom.css">

</style>