<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col-12">
        <h2>Danh sách xe</h2>
      </div>
      <div class="col-9">
        <router-link to="xe">
          <button class="btn btn-success"><v-icon icon="mdi:mdi-plus" /> Thêm mới</button>
        </router-link>

      </div>
      <div class="col-3">
        <div class="input-group mb-3">
          <input type="text" class="form-control" style="border-right: 0" v-model="keySearch" @input="getListXe" aria-label="Amount (to the nearest dollar)">
          <span class="input-group-text bg-white"><v-icon icon="mdi:mdi-magnify-expand" /></span>
        </div>
      </div>
    </div>
    <table class="table table-striped">
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
        <td>{{ entry.id }}</td>
        <td>{{ entry.tenXe }}</td>
        <td>{{ entry.giaXe }}</td>
        <td>{{ entry.diaChi }}</td>
        <td><img :src="getUrlImage(entry.anhNen)" style="max-height: 100px; max-width: 230px"/></td>
        <td>
          <el-tag v-if="entry.trangThaiDuyet == true" class="mx-1" effect="dark" type="success">
            Đã duyệt
          </el-tag>
          <el-tag v-else class="mx-1" effect="dark" type="danger">
            Chưa duyệt
          </el-tag>

        </td>
        <td v-if="isQuanTri">
          <router-link :to="{ name: 'duyetxe', params: { id: entry.id } }">
            <button><v-icon icon="mdi:mdi-eye color-248C92"/></button>
          </router-link>
        </td>
        <td v-else>
          <router-link :to="{ name: 'update-xe', params: { id: entry.id } }">
            <button><v-icon icon="mdi:mdi-pencil color-248C92"/></button>
          </router-link>
          <button @click="deleteById(entry.id)" > <v-icon icon="mdi:mdi-trash-can-outline color-DD4238"/></button>
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
<!--      <pagination v-model="page" :total-visible="5" :length="totalPages" :per-page="perPage" @update:modelValue="getListXe" />-->
    </div>

  </div>
</template>

<script>
import ImageService from "@/services/image.service"
import XeService from "@/services/xe.service"
import swal from 'sweetalert';
import AuthenService from "@/services/auth.services"

export default ({
  name: "QuanLyXe",
  data () {
    return {
      headers: [
        {
          name: 'ID',
          code: 'id',
          type: 'text'
        },
        {
          name: 'Tên xe',
          code: 'tenXe',
          type: 'text'
        },
        {
          name: 'Giá tiền',
          code: 'giaTien',
          type: 'number'
        },
        {
          name: 'Địa chỉ',
          code: 'diaChi',
          type: 'text'
        },
        {
          name: 'Ảnh',
          code: 'anhNen',
          type: 'image'
        },
        {
          name: 'Trạng thái duyệt',
          code: 'trangThaiDuyet',
          type: 'text'
        },
      ],
      listXe: [],
      listAllXe: [],
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
  isQuanTri() {
    let user = JSON.parse(window.localStorage.getItem('user'))
    user = user ? user : ''
    let permisson = user.permissions ? user.permissions : []
    let isQuanTri = permisson.includes('admin') ? true : false

    return isQuanTri
  },
  methods: {
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
      XeService.findByNguoiDungID(params).then(response => (
          this.listXe = response.data.content,
          this.page_size = response.data.size,
          this.totalPages = response.data.totalPages
      ))
    },
    getAllXe() {
      let params = {}
      params['page'] = this.page
      params['limit'] = this.page_size
      params['search'] = this.keySearch
      XeService.getAll(params).then(response => {
        this.listXe = response.data.content,
        this.page_size = response.data.size,
        this.totalPages = response.data.totalPages
      })
    },
    getUrlImage(name) {
      return ImageService.getImage(name);
    },
    async getUser(tenDn) {
      await AuthenService.findByTenDangNhap(tenDn).then(response => {
        this.nguoiDungId = response.data.id
        console.log(this.nguoiDungId);
      })
    },
    changeListXe() {
      if (this.isQuanTri) {
        this.getAllXe();
      } else {
        this.getListXe();
      }
    }
  },
  created() {
    var user = JSON.parse(localStorage.getItem('user'));
    this.tenDangNhap = user.tenDangNhap;
    this.nguoiDungId = user.id
    if (this.isQuanTri) {
      this.getAllXe();
    } else {
      this.getListXe();
    }
    // this.getListXe();
  },
  // watch: {
  //   nguoiDungId() {
  //
  //
  //   }
  // }
})
</script>

<style>
@import "@/assets/css/custom.css";
.v-input__prepend {
  align-self: center;
}
</style>