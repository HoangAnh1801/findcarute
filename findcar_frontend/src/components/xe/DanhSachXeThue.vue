<template>
  <div class="container-fluid" style="margin-top: 50px; padding: 0px 50px">
    <div class="row">
      <div class="col-12">

      </div>
      <div class="col-9">
        <h2>Danh sách xe đã thuê</h2>
      </div>
      <div class="col-3">
        <div class="input-group mb-3">
          <input type="text" class="form-control" style="border-right: 0" v-model="keySearch" @input="getListXe" aria-label="Amount (to the nearest dollar)">
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
      <tr v-for="(entry, stt) in listXe" :key="entry.id" style="vertical-align: middle;">
        <th scope="row" style="width: 5%">{{ stt + 1 }}</th>
        <!--        <td>{{ entry.id }}</td>-->
        <td>{{ entry.xe.tenXe }}</td>
        <td>
          <template v-if="entry.trangThaiDuyet == true">
            {{ entry.nguoiDung.sdt }}
          </template>

        </td>
        <td>{{ entry.xe.hangXe.ten }}</td>
        <td>{{ formatDateTime(entry.ngayBatDau) }}</td>
        <td>{{ formatDateTime(entry.ngayKetThuc) }}</td>
        <td>
          <el-tag v-if="entry.trangThaiDuyet == true" class="mx-1 cursor-pointer" effect="dark" type="success">
            Đã duyệt
          </el-tag>
            <el-tag v-else class="mx-1 cursor-pointer" effect="dark" type="danger">
              Chưa duyệt
            </el-tag>
        </td>
        <td>
          <el-tag v-if="entry.trangThaiHuy == true" class="mx-1 cursor-pointer" effect="dark" type="warning">
            Đã huỷ
          </el-tag>
        </td>
        <td>
          <template v-if="entry.trangThaiNhanXe === false">
            <el-tag @click="daNhanXe(entry.id, entry.trangThaiDuyet)" class="mx-1 cursor-pointer" effect="dark" type="warning">
              Chưa nhận
            </el-tag>
          </template>
          <el-tag v-else class="mx-1 cursor-pointer" effect="dark" type="success">
            Đã nhận
          </el-tag>
        </td>
        <td>
          <button @click="deleteById(entry.id, entry.trangThaiDuyet)" :disabled="entry.trangThaiHuy == true" > <v-icon icon="mdi:mdi-close-outline color-DD4238"/></button>
        </td>
      </tr>
      </tbody>
    </table>
    <div class="row float-right">
      <v-pagination
          v-if="totalPages > 1"
          variant="flat"
          active-color="#198754"
          color="#ffffff"
          v-model="page"
          class="my-4"
          :length="totalPages"
          :total-visible="5"
          @update:modelValue="changeListXe"
      ></v-pagination>
    </div>

  </div>
</template>

<script>
import XeService from "@/services/xe.service"
import swal from 'sweetalert';

export default ({
  name: "DanhSachXeThue",
  data () {
    return {
      headers: [
        {
          name: 'Tên xe',
          code: 'xe.tenXe',
          type: 'text'
        },
        {
          name: 'Số điện thoại chủ xe',
          code: 'nguoiDung.sdt',
          type: 'text'
        },
        {
          name: 'Hãng xe',
          code: 'xe.hangXe',
          type: 'text'
        },
        {
          name: 'Ngày bắt đầu',
          code: 'ngayBatDau',
          type: 'number'
        },
        {
          name: 'Ngày kết thúc',
          code: 'ngayKetThuc',
          type: 'text'
        },
        {
          name: 'Trạng thái duyệt',
          code: 'trangThaiDuyet',
          type: 'text'
        },
        {
          name: 'Trạng thái huỷ',
          code: 'trangThaiHuy',
          type: 'text'
        },
        {
          name: 'Đã nhận xe',
          code: 'trangThaiNhanXe',
          type: 'text'
        }
      ],
      listXe: [],
      nguoiDungId: '',
      keySearch: '',
      page: 1,
      page_size: 5,
      totalPages: '',
      tenDangNhap: ''
    }
  },
  computed: {
    isQuanTri() {
      let user = JSON.parse(window.localStorage.getItem('user'))
      let permisson = user.permissions ? user.permissions : ''
      let isQuanTri = permisson.includes('admin') ? true : false

      return isQuanTri
    },

  },
  methods: {
    clickDuyet(id) {
      swal({
        title: "Duyệt yêu cầu thuê xe?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      }).then((willDelete) => {
        if (willDelete) {
          XeService.duyetThueXe(id).then(response => {
            swal(response.data.message,'', 'success')
            window.load()
          })
        }
      })
    },
    daNhanXe(id, trangThaiDuyet) {
      console.log('trangThaiDuyet', trangThaiDuyet)
      swal({
        title: "Xác nhận đã nhận xe?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      }).then((willDelete) => {
        if (willDelete) {
          if (trangThaiDuyet == false) {
            swal('Yêu cầu thuê xe chưa được duyệt!','', 'error')
            return
          }
          XeService.daNhanXe(id).then(response => {
            swal(response.data.message,'', 'success')
            window.load()
          })
        }
      })
    },
    formatDateTime(date1) {
      const dateString = date1;

// Chuyển chuỗi thành đối tượng ngày
      const date = new Date(dateString);

// Lấy thông tin ngày, tháng, năm từ đối tượng ngày
      const day = date.getDate();
      const month = date.getMonth() + 1;
      const year = date.getFullYear();

// Định dạng lại thành chuỗi ngày tháng
      const formattedDate = `${day}-${month}-${year}`;
      return formattedDate

    },
    deleteById(id, trangThai) {
      swal({
        title: "Bạn có chắc chắn muốn huỷ thuê xe?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      })
          .then((willDelete) => {
            if (willDelete) {
              if (trangThai == true) {
                swal('Không thể huỷ thuê xe. Yêu cầu thuê xe đã được duyệt!', '', 'error')
              } else {
                XeService.huyThueXe(id).then(response => {
                  swal(response.data.message, "", "success")
                  this.getListXe();
                })
              }
            }
          })
    },
    getListXe() {
      let params = {}
      params['page'] = this.page
      params['limit'] = this.page_size
      params['id'] = this.nguoiDungId
      params['search'] = this.keySearch
      XeService.findThueXeByNguoiDung(params).then(response => (
              this.listXe = response.data.content,
              this.page_size = response.data.size,
              this.totalPages = response.data.totalPages
      ))
    },
    changeListXe() {
      this.getListXe();

    }
  },
  created() {
    var user = JSON.parse(localStorage.getItem('user'));
    this.tenDangNhap = user.tenDangNhap;
    this.nguoiDungId = user.id

    this.getListXe();
  }
})
</script>

<style>
@import "@/assets/css/custom.css";
.v-input__prepend {
  align-self: center;
}
</style>