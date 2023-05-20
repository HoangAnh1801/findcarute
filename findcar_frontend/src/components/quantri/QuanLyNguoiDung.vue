<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col-12">
        <h2>Danh sách thành viên</h2>
      </div>
<!--      <div class="col-9">-->
<!--        <button class="btn btn-success" @click="dialog=true"><v-icon icon="mdi:mdi-plus" /> Thêm mới</button>-->
<!--      </div>-->
      <div class="col-3">
        <div class="input-group mb-3">
          <input type="text" class="form-control" style="border-right: 0" v-model="keySearch" @input="getAllPhuongXa" aria-label="Amount (to the nearest dollar)">
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
      <tr v-for="(entry, stt) in resultQuery" :key="entry.id">
        <th scope="row" style="width: 5%">{{ stt + 1 }}</th>
        <td>{{ entry.hoTen }}</td>
        <td>{{ entry.tenDangNhap }}</td>
        <td>{{ entry.sdt }}</td>
        <td>{{ entry.trangThaiDuyet }}</td>
        <td>
          <button @click="handleEdit(entry.id)"><v-icon icon="mdi:mdi-pencil" /></button>
          <button @click="deleteById(entry.id)" > <v-icon icon="mdi:mdi-trash-can-outline" /></button>
        </td>
      </tr>
      </tbody>
    </table>
    <div class="row float-right">
      <pagination v-model="page" :records="rows" :per-page="perPage" @paginate="onPageChanged"/>
    </div>

  </div>
</template>

<script>
import DanhMucService from "@/services/danhmuc.service"
import swal from 'sweetalert';
import Pagination from 'v-pagination-3'
import AuthenService from "@/services/auth.services"

export default ({
  components: { Pagination },
  data () {
    return {
      page: 1,
      perPage: 5,
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
          name: 'Số điện thoại',
          code: 'sdt',
          type: 'text'
        },
        {
          name: 'Trạng thái',
          code: 'trangThaiDuyet',
          type: 'text'
        }
      ],
      listPhuongXa: [],
      keySearch: ''
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
      AuthenService.getAllNguoiDung(params).then(response => (
          this.listPhuongXa = response.data,
          this.paginate(this.perPage, 0)
      ))
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
              var params = {};
              params['id'] = id;
              DanhMucService.delete('phuongxa', params).then((response) => {
                if (response.data.status === "BAD_REQUEST") {
                  swal(response.data.message, "", "error")
                  return
                }
                this.getAllPhuongXa();
                swal(response.data.message, "", "success")
              })

            }
          })
    },
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