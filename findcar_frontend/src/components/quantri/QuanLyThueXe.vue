<template>
  <div class="container-fluid" style="margin-top: 50px; padding:0px 50px">
    <div class="row">
      <div class="col-12">

      </div>
      <div class="col-9">
        <h2>Quản lý thuê xe</h2>
<!--        <router-link to="xe">-->
<!--          <button class="btn btn-success"><v-icon icon="mdi:mdi-plus" /> Thêm mới</button>-->
<!--        </router-link>-->

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
        <td>{{ entry.nguoiDung.hoTen }}</td>
        <td>{{ entry.nguoiDung.sdt }}</td>
        <td>{{ formatDateTime(entry.ngayBatDau) }}</td>
        <td>{{ formatDateTime(entry.ngayKetThuc) }}</td>
        <td v-if="entry.xe.nguoiDung.id == nguoiDungId">
          <el-tag v-if="entry.trangThaiDuyet == true" class="mx-1 cursor-pointer" effect="dark" type="success">
            Đã duyệt
          </el-tag>
          <template v-else>
            <el-tag class="mx-1 cursor-pointer" @click="clickDuyet(entry.id)" effect="dark" type="danger">
              Chưa duyệt
            </el-tag>
          </template>
        </td>
        <td>
          <el-tag v-if="entry.trangThaiHoanXe == false" @click="xacNhan(entry.id)" class="mx-1 cursor-pointer" effect="dark" type="dangers">
            Chưa hoàn xe
          </el-tag>
          <el-tag v-else class="mx-1 cursor-pointer" :class="entry.trangThaiHoanXe == null ? 'd-none' : ''" effect="dark" type="success">
            Đã hoàn xe
          </el-tag>
        </td>
        <td v-if="isQuanTri">
          <router-link :to="{ name: 'duyetxe', params: { id: entry.id } }">
            <button><v-icon icon="mdi:mdi-eye color-248C92"/></button>
          </router-link>
        </td>
        <td v-else>
          <button @click="deleteById(entry.id)" > <v-icon icon="mdi:mdi-trash-can-outline color-DD4238"/></button>
        </td>
      </tr>
      </tbody>
    </table>
    <div class="row float-right">
      <v-pagination
          v-if="totalPages > 1"
          variant="flat"
          active-color="#9dc5e7"
          color="#ffffff"
          v-model="page"
          class="my-4"
          :length="totalPages"
          :total-visible="5"
          @update:modelValue="changeListXe"
      ></v-pagination>
      <!--      <pagination v-model="page" :total-visible="5" :length="totalPages" :per-page="perPage" @update:modelValue="getListXe" />-->
    </div>

  </div>
</template>

<script>
import XeService from "@/services/xe.service"
import swal from 'sweetalert';
// import AuthenService from "@/services/auth.services"

export default ({
  name: "QuanLyThueXe",
  data () {
    return {
      headers: [
        // {
        //   name: 'ID',
        //   code: 'id',
        //   type: 'text'
        // },
        {
          name: 'Họ và tên',
          code: 'nguoiDung.hoTen',
          type: 'text'
        },
        {
          name: 'Số điện thoại',
          code: 'nguoiDung.sdt',
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
          name: 'Xác nhận hoàn xe',
          code: 'trangThaiHoanXe',
          type: 'text'
        },
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
    xacNhan(id) {
      swal({
        title: "Xác nhận đã hoàn xe!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      }).then((willDelete) => {
        if (willDelete) {
          XeService.xacNhanHoanXe(id).then(response => {
            swal(response.data.message,'', 'success')
            window.load()
          })
        }
      })
    },
    clickDuyet(id) {
      swal({
        title: "Duyệt yêu cầu thuê xe",
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
    deleteById(id) {
      swal({
        title: "Bạn có chắc chắn muốn xoá?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      })
          .then((willDelete) => {
            if (willDelete) {
              XeService.delete(id).then((response) => {
                swal(response.data.message, "", "success")
                this.getListXe(this.nguoiDungId);
              })
            }
          })
    },
    getListXe() {
      let params = {}
      params['page'] = this.page
      params['limit'] = this.page_size
      params['id'] = this.nguoiDungId
      params['search'] = this.keySearch
      console.log('param', params)
      XeService.findThueXeByChuXe(params).then(response => (
          console.log('datcontent', response.data.content),
          this.listXe = response.data.content,
          this.page_size = response.data.size,
          this.totalPages = response.data.totalPages
      ))
    },
    // async getUser(tenDn) {
    //   await AuthenService.findByTenDangNhap(tenDn).then(response => {
    //     this.nguoiDungId = response.data.id
    //     console.log(this.nguoiDungId);
    //   })
    // },
    changeListXe() {
        this.getListXe();

    }
  },
  created() {
    var user = JSON.parse(localStorage.getItem('user'));
    this.tenDangNhap = user.tenDangNhap;
    this.nguoiDungId = user.id
    console.log('ủeid', this.nguoiDungId)

      this.getListXe();

    // this.getListXe();
  }
})
</script>

<style>
@import "@/assets/css/custom.css";
.v-input__prepend {
  align-self: center;
}
</style>