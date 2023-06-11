<template>
  <div class="container-fluid" style="margin-top: 50px; padding: 0px 50px">
    <div class="row">
      <div class="col-12">
        <h2>Danh sách người dùng</h2>
      </div>
      <div class="col-9">
      </div>
      <div class="col-3">
        <div class="input-group mb-3">
          <input type="text" class="form-control" style="border-right: 0" v-model="keySearch" @input="getAllPhuongXa" aria-label="Amount (to the nearest dollar)">
          <span class="input-group-text bg-white"><v-icon icon="mdi:mdi-magnify-expand" /></span>
        </div>
      </div>
    </div>
    <table class="table" style="box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.25);border-radius: 5px;">
      <thead>
      <tr>
        <th scope="col">STT</th>
        <th scope="col" v-for="itemHeader in headers" :key="itemHeader.id">{{ itemHeader.name }}</th>
        <th scope="col">Thao tác</th>
      </tr>
      </thead>
      <tbody>
<template v-for="(entry, stt) in resultQuery" :key="entry.id">
      <tr>
        <th scope="row" style="width: 5%">{{ stt + 1 }}</th>
        <td>{{ entry.hoTen }}</td>
        <td>{{ entry.tenDangNhap }}</td>
        <td>
          <el-tag v-if="entry.trangThaiDuyet == true" class="mx-1" effect="dark" type="success">
            Đã duyệt
          </el-tag>
          <el-tag v-else class="mx-1" effect="dark" type="danger">
            Khoá
          </el-tag>
        </td>

        <td>{{ entry.sdt }}</td>
        <td>
          <template v-if="entry.roles[0].tenQuyen === 'admin'">
            Quản trị viên
          </template>
          <template v-else-if="entry.roles[0].tenQuyen === 'chuxe'">
            Chủ xe
          </template>
          <template v-else>
            Người thuê
          </template>


        </td>
        <td>
<!--          v-if="entry.roles[0].tenQuyen == 'chuxe'"-->
          <template v-if="entry.roles[0].tenQuyen == 'chuxe'">
            <el-tag v-if="entry.trangThaiNopPhi == true" @click="chuaNopPhi(entry.tenDangNhap)" class="mx-1 cursor-pointer" effect="dark" type="primary">
              Đã nộp
            </el-tag>
            <el-tag v-else @click="daNopPhi(entry.tenDangNhap)" class="mx-1 cursor-pointer" effect="dark" type="warning">
              Chưa nộp
            </el-tag>
          </template>
        </td>
        <td>
          <template v-if="entry.ngayNopPhi != null && entry.trangThaiNopPhi === true">
            {{ formardate(entry.ngayNopPhi) }}
          </template>
        </td>
        <td >
          <button v-if="entry.trangThaiDuyet == true" :disabled="entry.trangThaiNopPhi === true" @click="khoaTaiKhoan(entry.tenDangNhap)" :title="'Khoá tài khoản'" ><el-tag class="mx-1 cursor-pointer" effect="dark" type="danger">
            Huỷ duyệt
          </el-tag></button>
          <button v-else @click="duyetTaiKhoan(entry.tenDangNhap, entry.trangThaiNopPhi)" :title="'Mở khoá tài khoản'"><el-tag class="mx-1 cursor-pointer" effect="dark" type="success">
            Duyệt tài khoản
          </el-tag></button>
        </td>
      </tr>
</template>
      </tbody>
    </table>
    <div class="row float-right">
      <pagination v-model="page" :records="rows" :per-page="perPage" @paginate="onPageChanged"/>
    </div>

  </div>
</template>

<script>
import swal from 'sweetalert';
import Pagination from 'v-pagination-3'
import AuthenService from "@/services/auth.services"
import moment from 'moment';

export default ({
  components: { Pagination },
  data () {
    return {
      page: 1,
      perPage: 10,
      paginatedItems : this.listPhuongXa,
      headers: [
        {
          name: 'Họ và tên',
          code: 'hoTen',
          type: 'text'
        },
        {
          name: 'Tên đăng nhập',
          code: 'tenDangNhap',
          type: 'text'
        },
        {
          name: 'Trạng thái',
          code: 'trangThaiDuyet',
          type: 'text'
        },
        {
          name: 'Số điện thoại',
          code: 'sdt',
          type: 'text'
        },
        {
          name: 'Quyền truy cập',
          code: 'roles[0].tenQuyen',
          type: 'text'
        },
        {
          name: 'Trạng thái nộp phí',
          code: 'trangThaiNopPhi',
          type: 'text'
        },
        {
          name: 'Ngày nộp phí',
          code: 'ngayNopPhi',
          type: 'text'
        }
      ],
      listPhuongXa: [],
      keySearch: '',
      nguoiDung: {
        tenDangNhap: '',
        ngayNopPhi: '',
      }
    }
  },
  computed: {
    rows() {
      return this.listPhuongXa.length
    },
    resultQuery(){
      if(this.keySearch){
        return this.listPhuongXa.filter((item)=>{
          return this.keySearch.toLowerCase().split(' ').every(v => item.hoTen.toLowerCase().includes(v))
        })
      }else{
        return this.paginatedItems;
      }
    }
  },
  methods: {
    formardate(date) {
      return moment(date).format('DD-MM-YYYY');
    },
    paginate(page_size, page_number) {
      let itemsToParse = this.listPhuongXa;
      this.paginatedItems = itemsToParse.slice(
          page_number * page_size,
          (page_number + 1) * page_size
      );
    },
    onPageChanged(page) {
      this.paginate(this.perPage, page - 1);
    },
    getAllPhuongXa() {
      var params = {};
      params['search'] = this.keySearch;
      AuthenService.getAllNguoiDung(params).then(response => {
          console.log('list', response.data)
          this.listPhuongXa = response.data
          this.paginate(this.perPage, 0)
          var result = []
          var datafor = response.data
          datafor.forEach(function (nguoiDung) {
            var item = {}
            item.tenDangNhap = nguoiDung.tenDangNhap;
            item.ngayNopPhi = new Date(moment((nguoiDung).ngayNopPhi).format('YYYY-MM-DD '));
            result.push(item)
        })
      })
    },
    khoaTaiKhoan(tendn) {
      swal({
        title: "Khoá tài khoản người dùng?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      })
          .then((willDelete) => {
            if (willDelete) {
              AuthenService.khoaTaiKhoan(tendn).then((response) => {
                  swal(response.data.message, "", "success")
                this.getAllPhuongXa();
              })

            }
          })
    },
    daNopPhi(tendn) {
      swal({
        title: "Xác nhận đã nộp phí?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      })
          .then((willDelete) => {
            if (willDelete) {
              const currentDate = new Date();
              this.nguoiDung.ngayNopPhi = moment(currentDate).utcOffset(7).format('YYYY-MM-DD');
              this.nguoiDung.tenDangNhap = tendn

              AuthenService.daNopPhi(this.nguoiDung).then((response) => {
                  swal(response.data.message, "", "success")
                this.getAllPhuongXa();
              })

            }
          })
    },
    chuaNopPhi(tendn) {
      swal({
        title: "Xác nhận chưa nộp phí?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      })
          .then((willDelete) => {
            if (willDelete) {
              AuthenService.chuaNopPhi(tendn).then((response) => {
                swal(response.data.message, "", "success")
                this.getAllPhuongXa();
              })
            }
          })
    },
    duyetTaiKhoan(tendn, phi) {
      swal({
        title: "Duyệt tài khoản người dùng?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      })
          .then((willDelete) => {
            if (willDelete) {
              if (phi == false) {
                swal('Tài khoản chưa nộp phí', "", "error")
                return
              }
              AuthenService.duyetTaiKhoan(tendn).then((response) => {
                  swal(response.data.message, "", "success")
                this.getAllPhuongXa();
              })

            }
          })
    },
    test() {
      // Ngày trong quá khứ
      const pastDate = moment('2022-04-01');

// Ngày hiện tại
      const currentDate = moment('2022-05-01');

// Kiểm tra nếu ngày trong quá khứ đến ngày hiện tại đã đủ 3 tháng
      const isThreeMonthsAgo = currentDate.diff(pastDate, 'months') >= 3;

      if (isThreeMonthsAgo) {
        console.log("Đủ 3 tháng");
      } else {
        console.log("Chưa đủ 3 tháng");
      }
    }
  },
  created() {
    this.getAllPhuongXa();
  }
})
</script>

<style>
@import "@/assets/css/custom.css";
.v-input__prepend {
  align-self: center;
}
</style>